<template>
    <div class="base-table">
        <div class="action-bar" v-if="haveActionBar">
            <a-space>
                <a-button @click="testButtion">测试按钮</a-button>
            </a-space>
        </div>
        <a-table :columns="columns" :data-source="dataSource" :scroll="{ x: 1355 }" :bordered="haveBordered"
            :loading="isLoading" :pagination="havePage ? searchParam : false"
            :row-selection="isSelect ? { selectedRowKeys: selectedRowKeys, onChange: onSelectChange, } : null"
            :row-key="(record: any) => `${record[rowkey]}`">

            <template v-slot:[item]="scope" v-for="item in renderArr">
                <slot :name="item" :scope="scope" v-bind="scope || {}"></slot>
            </template>
        </a-table>
    </div>
</template>
<script setup lang='ts'>
import { UnwrapRef, reactive, toRefs, useSlots, watch } from "vue";
import { SearchParam } from "../../types/param";

const props = defineProps({
    //数据源
    dataSource: {
        type: Object,
        required: true,
    },
    //表头
    columns: {
        type: Object,
        required: true,
    },
    //唯一值
    rowkey: {
        type: String,
        required: true,
    },
    //是否可选
    isSelect: {
        type: Boolean,
        default: false
    },
    //有边框
    haveBordered: {
        type: Boolean,
        default: false
    },
    //是否在加载
    isLoading: {
        type: Boolean,
    },
    // 有分页
    havePage: {
        type: Boolean,
        default: true,
    },
    //操作栏
    haveActionBar: {
        type: Object,
    }
});
const { dataSource } = toRefs(props);

// 使用 UnwrapRef 来获取 dataSource 内部实际值的类型
type DataSourceType = typeof dataSource[keyof typeof dataSource];
const editableData: UnwrapRef<Record<string, DataSourceType>> = reactive({});

const testButtion = () => {
    console.log(editableData);
}


const searchParam: SearchParam = reactive({
    current: 1,
    pageSize: 10,
    total: 0,
    pageSizeOptions: ['10', , '15', '20', '30', '50'],
})

const emit = defineEmits(["onSelRowKeys", "editableData"])

//获取插槽
const slots = useSlots();
const renderArr = Object.keys(slots);

const state = reactive({
    selectedRowKeys: [] as (string | number)[],
});

const { selectedRowKeys } = toRefs(state);

const onSelectChange = (selectedRowKeys: string[]) => {
    console.log('selectedRowKeys changed: ', selectedRowKeys);
    state.selectedRowKeys = selectedRowKeys;
    emit("onSelRowKeys", selectedRowKeys)
};

watch(editableData, (newData) => {
    emit('editableData', newData);
});

</script>
<style></style>