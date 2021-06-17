<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">


        </div>
    </a-layout-content>
</template>

<script lang="ts">
    import { defineComponent,ref } from 'vue';

    export default defineComponent({
        name: 'template',
        setup() {
            return {
                selectedKeys: ref(['2']),
            };
        },

    });
</script>

<style scoped>

    .ant-layout-header {
        background-color: transparent !important;
        padding: 20px !important;
    }

    .ant-layout-footer {
        background-color: transparent !important;
        padding: 20px !important;
    }

    .ant-layout-content {
        background-color: transparent !important;
        padding: 20px !important;
    }

</style>