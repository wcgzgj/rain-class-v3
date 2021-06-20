<!--课程的详细信息页面-->
<template>

    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>

        <a-spin :spinning="spinning">

            <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

                <a-typography>
                    <a-typography-title>{{classInfo.classname}}</a-typography-title>
                    <a-typography-paragraph>
                        {{classInfo.desc}}
                    </a-typography-paragraph>

                </a-typography>

                <!--&lt;!&ndash;选课和取消选课的按钮&ndash;&gt;-->
                <!--&lt;!&ndash;需要动态展示&ndash;&gt;-->
                <!--<a-button type="primary">-->
                <!--    选课-->
                <!--</a-button>-->

                <!--&nbsp;-->

                <!--<a-popconfirm-->
                <!--        title="确认取消课程？"-->
                <!--        ok-text="是"-->
                <!--        cancel-text="否"-->
                <!--        @confirm="confirm"-->
                <!--        @cancel="cancel"-->
                <!--&gt;-->

                <!--    <a-button type="primary">-->
                <!--        取消选课-->
                <!--    </a-button>-->
                <!--</a-popconfirm>-->

                <br/>
                <a-descriptions bordered title="课程信息：" :size="size">

                    <a-descriptions-item label="教师">{{teacherInfo.realname}}</a-descriptions-item>
                    <a-descriptions-item label="上课地点">{{classInfo.place}}</a-descriptions-item>
                    <a-descriptions-item label="上课时间">{{classInfo.starttime}}</a-descriptions-item>
                    <a-descriptions-item label="注意事项">{{classInfo.notice}}</a-descriptions-item>
                    <a-descriptions-item label="学分">{{classInfo.credit}}</a-descriptions-item>
                    <a-descriptions-item label="课程类型">{{classInfo.type}}</a-descriptions-item>
                    <a-descriptions-item label="其他信息">
                        课堂要求：关闭手机，好好听讲
                    </a-descriptions-item>
                </a-descriptions>

            </div>
        </a-spin>

    </a-layout-content>
</template>

<script>
    import {ref} from "vue";
    import {onMounted} from "@vue/runtime-core";
    import axios from 'axios';
    import { message } from 'ant-design-vue';
    import {useRoute} from "vue-router";


    export default {
        name: "ClassInfo",
        setup() {
            const route = useRoute();

            const classId = route.query.id;

            const spinning = ref(true);

            const classInfo=ref({
                // id:"",
                // classname:"",
                // teacherid:"",
                // starttime:"",
                // code:"",
                // place:"",
                // notice:"",
                // type:"",
                // credit:"",
                // desc:""
            })

            const teacherInfo = ref({});

            const searchById = () => {
                console.log("传入的课程id为:"+classId);

                axios.get("/class/selectById/"+classId).then(resp=> {
                    const data = resp.data;
                    spinning.value=false;
                    if (data.success) {
                        classInfo.value = data.content;
                        teacherInfo.value = data.content.teacher;
                        console.log("查询出的教师信息为:"+classInfo.value.teacher.loginname);
                    } else {
                        message.error(data.message);
                    }
                })
            }

            onMounted(()=>{
                searchById();
            });

            return {
                classInfo,
                searchById,
                teacherInfo,
                spinning
            };
        },

    }
</script>

<style scoped>

</style>