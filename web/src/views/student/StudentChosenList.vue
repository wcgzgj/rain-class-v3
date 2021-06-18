<!--学生已选课表-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <h1>这是学生已经选课的页面</h1>
        </div>
    </a-layout-content>
</template>

<script>
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";

    export default {
        name: "StudentChosenList",
        setup() {

            const user = computed(() => store.state.user);

            return {

            };
        },
    }
</script>

<style scoped>

</style>