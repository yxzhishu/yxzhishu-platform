<template>
    <a-form :model="formState" name="validate_other" v-bind="formItemLayout" @finishFailed="onFinishFailed"
        @finish="onFinish">

        <a-form-item name="select-multie" label="Select[multiple]"
            :rules="[{ required: true, message: 'Please select your favourite colors!', type: 'array' }]">
            <a-select v-model:value="formState['select-multie']" mode="multiple"
                placeholder="Please select favourite colors">
                <a-select-option value="red">Red</a-select-option>
                <a-select-option value="green">Green</a-select-option>
                <a-select-option value="blue">Blue</a-select-option>
            </a-select>
        </a-form-item>


        <a-form-item :wrapper-col="{ span: 12, offset: 6 }">
            <a-button type="primary" html-type="submit">Submit</a-button>
        </a-form-item>
    </a-form>
</template>
<script lang="ts" setup>
import { reactive } from 'vue';

const formItemLayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 14 },
};

const data = reactive({
    aaa: []
})

const formState = reactive<Record<string, any>>({
});
const onFinish = (values: any) => {
    console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
    console.log('Failed:', errorInfo);
};
</script>
