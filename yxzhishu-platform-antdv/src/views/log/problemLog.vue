<template>
    <div class="account-box">
        <a-space>
            <a-input-search v-model:value="searchParam.search" placeholder="请输入检索信息" style="width: 200px"
                @search="loadData" />
            <a-button type="primary" ghost @click="onSel('')">添加</a-button>
        </a-space>
        <a-table :columns="columns" :data-source="tableData.data" :scroll="{ x: 1155 }" bordered
            :pagination="searchParam" @change="handleTableChange">
            <template #bodyCell="{ column, text, record }">
                <!-- 操作 -->
                <template v-if="column.dataIndex === 'operation'">
                    <a-space>
                        <a @click="onSel(record.problemId)">查看</a>
                        <a-popconfirm title="确定删除?" @confirm="onDelete(record.problemId)">
                            <a>删除</a>
                        </a-popconfirm>
                    </a-space>
                </template>
            </template>
        </a-table>
    </div>
    <div>
        <a-drawer v-model:open="openDrawer" width="720" title="编辑查看" placement="right">
            <a-form :model="tableData.newData" :rules="rules" layout="vertical" ref="formRef">
                <a-form-item label="标题" name="problemTitle">
                    <a-input v-model:value="tableData.newData.problemTitle" placeholder="请输入标题"
                        :disabled="tableData.newData.problemId != ''" />
                </a-form-item>

                <a-form-item label="具体说明" name="problemValue">
                    <a-textarea v-model:value="tableData.newData.problemValue" :rows="4" placeholder="请输入说明"
                        :disabled="tableData.newData.problemId != ''" />
                </a-form-item>
                <a-form-item label="回答" name="answerValue">
                    <a-textarea v-model:value="tableData.newData.answerValue" :rows="4" placeholder="请输入回答" />
                </a-form-item>
            </a-form>

            <template #extra>
                <a-space>
                    <a-button type="primary" @click="onWrite">确认</a-button>
                </a-space>
            </template>
        </a-drawer>
    </div>
</template>

<script setup lang='ts'>

import { reactive, ref } from 'vue';
import { message } from 'ant-design-vue';
import type { Rule } from 'ant-design-vue/es/form';
import type { SearchParam } from '../../types/param.d.ts'
import { apiProblemAnswer, apiProblemDelete, apiProblemPage, apiProblemPropose } from '../../api/system/logApi'

const formRef = ref();
//打开抽屉
const openDrawer = ref<boolean>(false);

interface DataItem {
    problemId: string;
    problemTitle: string;
    createrName: string,
    answerName: string;
    createTime: string;
    problemValue: string;
    answerValue: string;
}


const columns = [
    {
        title: 'ID',
        dataIndex: 'problemId',
        key: 'problemId',
        width: 50,
        ellipsis: true,
    },
    {
        title: '标题',
        dataIndex: 'problemTitle',
        key: 'problemTitle',
        width: 180,
        ellipsis: true,
    },
    {
        title: '发布人',
        dataIndex: 'createrName',
        key: 'createrName',
        width: 40,
        ellipsis: true,
    },
    {
        title: '已回答',
        dataIndex: 'answerName',
        key: 'answerName',
        width: 40,
    },
    {
        title: '发布时间',
        dataIndex: 'createTime',
        key: 'createTime',
        width: 60,
    },
    {
        title: '操作',
        dataIndex: 'operation',
        fixed: 'right',
        width: 50,
    },
]

const searchParam: SearchParam = reactive({
    search: '',
    current: 1,
    pageSize: 10,
    total: 0,
    pageSizeOptions: ['10', '15', '20', '30', '50'],
})
interface TableData {
    data: DataItem[],
    newData: DataItem,
}

const tableData: TableData = reactive({
    data: [],
    newData: {
        problemId: '',
        problemTitle: '',
        createrName: '',
        answerName: '',
        createTime: '',
        problemValue: '',
        answerValue: '',
    },
})

const rules: Record<string, Rule[]> = {
    problemTitle: [{ required: true, message: '请输入标题' }],
    problemValue: [{ required: true, message: '请输入文章' }],
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
    await apiProblemPage(searchParam).then(res => {
        tableData.data = res.data.list;
        searchParam.total = res.data.total;
    })
}


//编写问题
const onWrite = () => {
    if (tableData.newData.problemId == '') {
        //新增
        formRef.value
            .validate()
            .then(() => {
                apiProblemPropose(tableData.newData).then(() => {
                    message.success('添加成功')
                    openDrawer.value = false
                    loadData()
                })
            });
    } else {
        //回答
        if (tableData.newData.answerValue == '') {
            openDrawer.value = false
            return;
        }
        apiProblemAnswer(tableData.newData).then(() => {
            message.success('发送成功')
            loadData()
            openDrawer.value = false
        })

    }

}

const onDelete = (id: string) => {
    apiProblemDelete(id).then(() => {
        message.success('删除成功')
        loadData()
    })
}

const onSel = (id: string) => {
    if (id === '') {
        tableData.newData = {
            problemId: '',
            problemTitle: '',
            createrName: '',
            answerName: '',
            createTime: '',
            problemValue: '',
            answerValue: '',
        };
    } else {
        tableData.newData = tableData.data.filter(item => item.problemId === id)[0];
    }
    openDrawer.value = true;
}

loadData();
</script>

<style></style>