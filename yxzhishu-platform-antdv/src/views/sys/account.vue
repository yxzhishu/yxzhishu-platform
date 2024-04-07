<template>
    <div class="account-box">
        <a-space>
            <a-input-search v-model:value="searchParam.search" placeholder="请输入检索信息" style="width: 200px"
                @search="loadData" />
            <a-button type="primary" ghost @click="openDrawer = true">添加</a-button>
        </a-space>
        <a-table :columns="columns" :data-source="tableData.data" :scroll="{ x: 1355 }" bordered
            :pagination="searchParam" @change="handleTableChange">
            <template #bodyCell="{ column, text, record }">
                <!-- 操作 -->
                <template v-if="column.dataIndex === 'operation'">
                    <span v-if="editableData[record.accountId]">
                        <a-space>
                            <a-typography-link @click="save(record.accountId)">保存</a-typography-link>
                            <a @click="cancel(record.accountId)">取消</a>
                        </a-space>
                    </span>
                    <span v-else>
                        <a-space>
                            <a-popconfirm v-if="tableData.data.length" title="确定重置密码?"
                                @confirm="onResetPassword(record.accountId)">
                                <a>重置密码</a>
                            </a-popconfirm>
                            <a @click="edit(record.accountId)">编辑</a>
                        </a-space>
                    </span>
                </template>
                <!-- 手机号 邮箱 描述 昵称-->
                <template v-else-if="['phone', 'email', 'description', 'nickname'].includes(column.dataIndex)">
                    <a-input v-if="editableData[record.accountId]" style="margin: -5px 0"
                        v-model:value="(editableData[record.accountId] as Record<string, any>)[column.dataIndex]">
                    </a-input>
                    <template v-else>{{ text }}</template>
                </template>
                <!-- 状态 -->
                <template v-else-if="column.dataIndex === 'status'">
                    <span v-if="editableData[record.accountId]">
                        <a-select v-model:value="editableData[record.accountId]['status']">
                            <a-select-option value="0">正常</a-select-option>
                            <a-select-option value="1">禁用</a-select-option>
                        </a-select>
                    </span>
                    <template v-else>
                        {{ getStatusText(text) }}
                    </template>
                </template>
                <!-- 角色列表 -->
                <template v-else-if="column.dataIndex === 'roleIdList'">
                    <span v-if="editableData[record.accountId]">
                        <a-select v-model:value="tableData.roleIds" mode="multiple" style="width: 100%"
                            placeholder="请选择">
                            <a-select-option v-for="item in tableData.roleList" :value="item.roleId">
                                {{ item.roleName }}</a-select-option>
                        </a-select>
                    </span>
                    <template v-else>
                        <a-tag v-for="role in record.roleIdList" color="orange">
                            {{ getRoleName(role) }}
                        </a-tag>
                    </template>
                </template>
            </template>
        </a-table>
    </div>
    <div>
        <a-drawer v-model:open="openDrawer" width="720" title="创建一个新账户" placement="right">
            <a-form :model="tableData.newData" :rules="rules" layout="vertical" ref="formRef">
                <a-form-item label="账号" name="accountCode">
                    <a-input v-model:value="tableData.newData.accountCode" placeholder="请输入账号" />
                </a-form-item>
                <a-form-item label="密码" name="password">
                    <a-input-password v-model:value="tableData.newData.password" placeholder="请输入密码,不输入默认123123" />
                </a-form-item>
                <a-form-item label="角色" name="roleIdList">
                    <a-select v-model:value="tableData.newData['roleIdList']" mode="multiple" style="width: 100%"
                        placeholder="请选择">
                        <a-select-option v-for="item in tableData.roleList" :value="item.roleId">
                            {{ item.roleName }}</a-select-option>
                    </a-select>
                </a-form-item>


                <a-form-item label="昵称" name="nickname">
                    <a-input v-model:value="tableData.newData.nickname" placeholder="请输入昵称" />
                </a-form-item>
                <a-form-item label="手机号" name="phone">
                    <a-input v-model:value="tableData.newData.phone" placeholder="请输入手机号" />
                </a-form-item>
                <a-form-item label="邮箱" name="email">
                    <a-input v-model:value="tableData.newData.email" placeholder="请输入邮箱" />
                </a-form-item>
                <a-form-item label="说明" name="description">
                    <a-textarea v-model:value="tableData.newData.description" :rows="4" placeholder="请输入说明描述" />
                </a-form-item>
            </a-form>

            <template #extra>
                <a-space>
                    <a-button type="primary" @click="onRegister">确认</a-button>
                </a-space>
            </template>
        </a-drawer>
    </div>
</template>

<script setup lang='ts'>

import { reactive, ref } from 'vue';
import { cloneDeep } from 'lodash-es';
import type { UnwrapRef } from 'vue';
import { message } from 'ant-design-vue';
import type { Rule } from 'ant-design-vue/es/form';

import type { SearchParam, Role } from '../../types/param.d.ts'
import { apiPage, apiUpdate, apiResetPassword, apiRegister } from '../../api/system/accountApi'
import { apiFindNormalByAccurate } from '../../api/system/roleApi'
import { getStatusText } from '../../utils/convertText'

const formRef = ref();
//打开抽屉
const openDrawer = ref<boolean>(false);

interface DataItem {
    accountId: string;
    accountCode: string;
    password: string,
    nickname: string;
    status: string;
    phone: string;
    email: string;
    description: string;
    roleName: string;
    roleIdList: Array<string | undefined>
}


const columns = [
    {
        title: 'ID',
        dataIndex: 'accountId',
        key: 'accountId',
        width: 70,
        ellipsis: true,
    },
    {
        title: '账号',
        dataIndex: 'accountCode',
        key: 'accountCode',
        width: 100,
        ellipsis: true,
    },
    {
        title: '昵称',
        dataIndex: 'nickname',
        key: 'nickname',
        width: 180,
        ellipsis: true,
    },
    {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        width: 95,
    },
    {
        title: '手机号',
        dataIndex: 'phone',
        key: 'phone',
        width: 130,
    },
    {
        title: '邮箱',
        dataIndex: 'email',
        key: 'email',
        width: 200,
    },
    {
        title: '角色',
        dataIndex: 'roleIdList',
        key: 'roleIdList',
        width: 180,
        ellipsis: true,
    },
    {
        title: '其他',
        dataIndex: 'description',
        key: 'description',
        width: 500,
        ellipsis: true,
    },
    {
        title: '操作',
        dataIndex: 'operation',
        fixed: 'right',
        width: 130,
    },
]

const searchParam: SearchParam = reactive({
    search: '',
    current: 1,
    pageSize: 10,
    total: 0,
    pageSizeOptions: ['10', '15', '20', '30', '50'],
})
//编辑状态数据
const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

interface TableData {
    data: DataItem[],
    newData: DataItem,
    roleList: Role[],
    roleIds: any,
}

const tableData: TableData = reactive({
    data: [],
    newData: {
        accountId: '',
        accountCode: '',
        nickname: '',
        status: '0',
        phone: '',
        email: '',
        description: '',
        password: '',
        roleName: '',
        roleIdList: []
    },
    roleList: [],
    roleIds: []
})
//手机号权限校验
const ruleByPhone = async (_rule: Rule, value: string) => {
    var length = value.length;
    var mobile = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
    if (length == 0 || (length == 11 && mobile.test(value))) {
        return Promise.resolve();

    }
    return Promise.reject('手机号格式不正确');
}

const rules: Record<string, Rule[]> = {
    accountCode: [{ required: true, message: '请输入账号名称' }, { min: 5, max: 20, message: '长度应为5-20', trigger: 'blur' }],
    password: [{ min: 8, max: 30, message: '长度应为8-30', trigger: 'blur' }],
    owner: [{ min: 3, max: 15, message: '长度应为3-15', trigger: 'blur' }],
    email: [{ type: 'email', message: '应为邮箱格式' }],
    phone: [{ validator: ruleByPhone, trigger: 'blur' }],
    roleIdList: [{
        required: true, message: '请选择角色', trigger: 'change', type: 'array'
    }]
};


const handleTableChange = (paginations: { pageSize: number; current: number; }) => {
    if (searchParam.pageSize != paginations.pageSize) {
        searchParam.current = 1;
    } else {
        searchParam.current = paginations.current;
    }
    searchParam.pageSize = paginations.pageSize;
    loadData();
}


//加载数据
const loadData = async () => {
    await apiPage(searchParam).then(res => {
        tableData.data = res.data.list;
        searchParam.total = res.data.total;
    })
}

const laodRoleData = async () => {
    let aa = { current: 1, pageSize: 1 }
    await apiFindNormalByAccurate(aa).then(res => {
        tableData.roleList = res.data;
        console.log(tableData.roleList);
    })
}

//取消编辑
const cancel = (key: string) => {
    delete editableData[key];
    tableData.roleIds = [];
};
//保存修改
const save = async (key: string) => {
    editableData[key].roleIdList = tableData.roleIds
    await apiUpdate(editableData[key]).then(async () => {
        message.success('修改成功')
        // Object.assign(tableData.data.filter(item => key === item.accountId)[0], editableData[key]);
        loadData();
        delete editableData[key];
    })
    tableData.roleIds = [];
};
//重置密码
const onResetPassword = (key: string) => {
    let resetPsdParam = {
        accountId: key,
        updateFlag: 0
    };
    apiResetPassword(resetPsdParam).then(() => {
        message.success('已重置密码')
        loadData()
    })
};
//编辑
const edit = (key: string) => {
    console.log(editableData);
    //清空已存在的编辑数据
    //Object.keys(editableData).forEach(key => delete editableData[key]);
    if (0 < Object.keys(editableData).length) {
        message.info("请先完成上一条编辑");
        return;
    }
    var tmp = tableData.data.filter(item => key === item.accountId)[0];
    editableData[key] = cloneDeep(tmp);
    tableData.roleIds = tmp.roleIdList.map(item => item && item !== null ? item : undefined);
};
//注册账号
const onRegister = () => {
    formRef.value
        .validate()
        .then(() => {
            apiRegister(tableData.newData).then(() => {
                message.success('添加成功')
                openDrawer.value = false
                loadData()
            })
        });
}
//获取角色名称
function getRoleName(roleId: string) {
    var result = tableData.roleList.filter(function (obj) {
        return obj.roleId === roleId;
    });
    return result[0]?.roleName;
}

loadData();
laodRoleData();
</script>

<style></style>