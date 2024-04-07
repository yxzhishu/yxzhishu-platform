<template>
    <div class="menu-box">
        <a-table :columns="columns" :data-source="tableData.data" :scroll="{ x: 1355 }" bordered :rowKey="'menuId'"
            :pagination=false>
            <template #bodyCell="{ column, text }">
                <!-- 状态 -->
                <template v-if="column.dataIndex === 'status'">
                    {{ getStatusText(text) }}
                </template>
            </template>
        </a-table>
    </div>
</template>

<script setup lang='ts'>

import { reactive } from 'vue';
import { apiFindMenuBySerialize } from '../../api/system/menuApi'
import { getStatusText } from '../../utils/convertText'

interface DataItem {
    menuId: string;
    menuName: string;
    menuType: string,
    menuPath: string;
    component: string;
    title: string;
    icon: string;
    keepAlive: string;
    status: string;
}


const columns = [
    {
        title: 'ID',
        dataIndex: 'menuId',
        key: 'menuId',
        width: 70,
        ellipsis: true,
    },
    {
        title: '菜单名称',
        dataIndex: 'menuName',
        key: 'menuName',
        width: 80,
        ellipsis: true,
    },
    {
        title: '类型',
        dataIndex: 'menuType',
        key: 'menuType',
        width: 50,
        ellipsis: true,
    },
    {
        title: '路径',
        dataIndex: 'menuPath',
        key: 'menuPath',
        width: 100,
    },
    {
        title: '组件',
        dataIndex: 'component',
        key: 'component',
        width: 180,
    },
    {
        title: '标题',
        dataIndex: 'title',
        key: 'title',
        width: 120,
    },
    {
        title: '图标',
        dataIndex: 'icon',
        key: 'icon',
        width: 180,
        ellipsis: true,
    },
    {
        title: '保持路由',
        dataIndex: 'keepAlive',
        key: 'keepAlive',
        width: 90,
        ellipsis: true,
    },
    {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        width: 80,
        ellipsis: true,
    },
]

interface TableData {
    data: DataItem[],
}

const tableData: TableData = reactive({
    data: [],
})

//加载数据
const loadData = async () => {
    await apiFindMenuBySerialize().then(res => {
        tableData.data = res.data;
    })
}

loadData();
</script>

<style></style>