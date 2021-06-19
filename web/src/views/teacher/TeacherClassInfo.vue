<!--教师课程信息-->
<!--包括课程中的学生信息-->
<!--学生的成绩等-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">


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
        name: "TeacherClassInfo",

        setup() {

            const user = computed(() => store.state.user);

            return {

            };
        },
    }
</script>

<style scoped>

</style>