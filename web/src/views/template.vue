<!--模板页面，不会在主页上显示-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <a-spin :spinning="spinning">



            </a-spin>

        </div>
    </a-layout-content>
</template>

<script lang="ts">
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import axios from 'axios';
    import {useRoute} from "vue-router";
    import router from '../router';

    export default defineComponent({
        name: 'template',
        setup() {
            const route = useRoute();

            const user = computed(() => store.state.user);


            /**
             * 展示图片的基础路径
             */
            const basePicPath=ref("");
            basePicPath.value+=process.env.VUE_APP_SERVER+"/disPic/";


            /**
             * 下面，是 router 的使用方法
             * 可以在 url 中获取传入的参数
             */
            //axios.get("/doc/all/"+route.query.ebookId)

            return {

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