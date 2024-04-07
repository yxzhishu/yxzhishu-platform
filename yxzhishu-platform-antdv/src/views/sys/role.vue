<template>
    <div class="role-box">
        <a-space>
            <a-input-search v-model:value="searchParam.search" placeholder="请输入检索信息" style="width: 200px"
                @search="searchData" />
            <a-button type="primary" ghost @click="openDrawer = true">添加</a-button>
        </a-space>
        <a-table :columns="columns" :data-source="tableData.data" bordered :pagination="searchParam"
            @change="handleTableChange">
            <template #bodyCell="{ column, text, record }">
                <template v-if="column.dataIndex === 'operation'">
                    <span v-if="editableData[record.roleId]">
                        <a-space>
                            <a-typography-link @click="save(record.roleId)">保存</a-typography-link>
                            <a @click="cancel(record.roleId)">取消</a>
                        </a-space>
                    </span>
                    <span v-else>
                        <a-space>
                            <a @click="edit(record.roleId)">编辑</a>
                            <a @click="authorization(record.roleId)">授权</a>
                            <a-popconfirm title="确定删除?" @confirm="onDelete(record.roleId)">
                                <a>删除</a>
                            </a-popconfirm>
                        </a-space>
                    </span>
                </template>

                <template v-else-if="['sortOrder', 'description'].includes(column.dataIndex)">
                    <!-- v-model:value="editableData[record.roleId][column.dataIndex]" -->
                    <a-input v-if="editableData[record.roleId]" style="margin: -5px 0"
                        v-model:value="(editableData[record.roleId] as Record<string, any>)[column.dataIndex]">
                    </a-input>
                    <template v-else>{{ text }}</template>
                </template>

                <template v-else-if="column.dataIndex === 'status'">
                    <span v-if="editableData[record.roleId]">
                        <a-select v-model:value="editableData[record.roleId]['status']">
                            <a-select-option value="0">正常</a-select-option>
                            <a-select-option value="1">禁用</a-select-option>
                        </a-select>
                    </span>
                    <template v-else>
                        {{ getStatusText(text) }}
                    </template>
                </template>

                <template v-else-if="column.dataIndex === 'roleType'">
                    <span v-if="editableData[record.roleId]">
                        <a-select v-model:value="editableData[record.roleId]['roleType']">
                            <a-select-option value="0">管理</a-select-option>
                            <a-select-option value="1">用户</a-select-option>
                        </a-select>
                    </span>
                    <template v-else>
                        {{ getRoleTypeText(text) }}
                    </template>
                </template>
            </template>
        </a-table>
    </div>
    <div>
        <a-drawer v-model:open="openDrawer" width="720" title="创建一个新账户" placement="right">
            <a-form :model="tableData.newData" :rules="rules" layout="vertical" ref="formRef">
                <a-form-item label="角色名称" name="roleName">
                    <a-input v-model:value="tableData.newData.roleName" placeholder="角色名称" />
                </a-form-item>
                <a-form-item label="排序号" name="sortOrder">
                    <a-input-number :min="1" :max="9999" v-model:value="tableData.newData.sortOrder"
                        placeholder="请输入排序号" />
                </a-form-item>
                <a-form-item label="说明" name="description">
                    <a-textarea v-model:value="tableData.newData.description" :rows="4" placeholder="请输入说明描述" />
                </a-form-item>
            </a-form>

            <template #extra>
                <a-space>
                    <a-button type="primary" @click="onAdd">确认</a-button>
                </a-space>
            </template>
        </a-drawer>
    </div>
    <div>
        <a-drawer v-model:open="openDrawerAuthor" width="720" title="角色授权" placement="right">

            <a-tree v-model:checkedKeys="tableData.checkedKeys" checkable :tree-data="tableData.treeData"
                :fieldNames="{ children: 'children', title: 'title', key: 'menuId' }">
            </a-tree>
            <template #extra>
                <a-space>
                    <a-button type="primary" @click="onAuthor">确认</a-button>
                </a-space>
            </template>
        </a-drawer>
    </div>
</template>

<script setup lang='ts'>

import { reactive, ref } from 'vue';
import { apiPage, apiAdd, apiUpdate, apiHandleAuthorization, apiDelete } from '../../api/system/roleApi.ts'
import { apiFindMenuBySerialize, apiFindMenuIdByRoleId } from '../../api/system/menuApi.ts'
import { cloneDeep } from 'lodash-es';

import type { SearchParam, } from '../../types/param.d.ts'

import type { UnwrapRef } from 'vue';
import { TreeProps, message } from 'ant-design-vue';
import { Rule } from 'ant-design-vue/es/form';
import { getStatusText, getRoleTypeText } from '../../utils/convertText'

const formRef = ref();
//打开抽屉
const openDrawer = ref<boolean>(false);
const openDrawerAuthor = ref<boolean>(false);

const selectRoleId = ref('');

interface DataItem {
    roleId: string;
    roleName: string;
    roleKey: string;
    roleType: string;
    deptId: string;
    deptName?: string;
    sortOrder: number,
    status: string;
    description: string;
}

const columns = [
    {
        title: 'ID',
        dataIndex: 'roleId',
        key: 'roleId',
        width: '80'
    },
    {
        title: '角色名称',
        dataIndex: 'roleName',
        key: 'roleName'
    },
    {
        title: '角色标识',
        dataIndex: 'roleKey',
        key: 'roleKey'
    },
    {
        title: '角色类型',
        dataIndex: 'roleType',
        key: 'roleType'
    },
    {
        title: '排序号',
        dataIndex: 'sortOrder',
        key: 'sortOrder'
    },
    {
        title: '状态',
        dataIndex: 'status',
        key: 'status'
    },
    {
        title: '其他',
        dataIndex: 'description',
        key: 'description'
    },
    {
        title: '操作',
        dataIndex: 'operation',
        fixed: 'right',
        width: 150,
    },
]

const searchParam: SearchParam = reactive({
    search: '',
    current: 1,
    pageSize: 10,
    total: 0,
    pageSizeOptions: ['10', , '15', '20', '30', '50'],
})

const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

interface TableData {
    data: DataItem[],
    newData: DataItem,
    treeData: TreeProps[],
    checkedKeys: Array<string | undefined>;
}

const tableData: TableData = reactive({
    //表格数据
    data: [],
    //新增数据
    newData: {
        roleId: '-1',
        roleName: '',
        roleKey: '',
        roleType: '',
        deptId: '',
        sortOrder: 99,
        status: '0',
        description: ''
    },
    //已选择授权节点
    checkedKeys: [],
    //权限树
    treeData: []
})

const rules: Record<string, Rule[]> = {
    sortOrder: [{ type: 'integer' }],
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


const loadData = async () => {
    await apiPage(searchParam).then(res => {
        tableData.data = res.data.list;
        searchParam.total = res.data.total;
    })
}
const loadMenuData = async () => {
    await apiFindMenuBySerialize().then(res => {
        tableData.treeData = res.data;

    })
}
const findCheckedKeys = async (roleId: string) => {
    await apiFindMenuIdByRoleId(roleId).then(res => {
        tableData.checkedKeys = res.data;

    })
}

const cancel = (key: string) => {
    delete editableData[key];
};
const searchData = async () => {
    searchParam.current = 1;
    loadData();
}

//保存修改
const save = (key: string) => {
    apiUpdate(editableData[key]).then(() => {
        message.success('修改成功')
        Object.assign(tableData.data.filter(item => key === item.roleId)[0], editableData[key]);
        delete editableData[key];
    })
};

const edit = (key: string) => {
    editableData[key] = cloneDeep(tableData.data.filter(item => key === item.roleId)[0]);
};

const authorization = (key: string) => {
    selectRoleId.value = key;
    loadMenuData();
    findCheckedKeys(key);
    openDrawerAuthor.value = true;
}

const onAdd = () => {
    formRef.value
        .validate()
        .then(() => {
            apiAdd(tableData.newData).then(() => {
                message.success('添加成功')
                openDrawer.value = false
                loadData()
            })
        });
}

const onAuthor = async () => {
    console.log(selectRoleId.value);
    await apiHandleAuthorization({ "roleId": selectRoleId.value, "menuIdList": tableData.checkedKeys }).then(() => {
        message.success('授权成功')
        openDrawerAuthor.value = false;
    });

}

const onDelete = (id: string) => {
    apiDelete(id).then(() => {
        loadData()
        message.success('删除成功')
    })
}

loadData();
</script>

<style></style>