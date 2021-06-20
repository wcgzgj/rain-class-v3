<!--学生的课程详细页面（选课和退选，是动态更新的）-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">

        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>

        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <a-spin :spinning="spinning">
                <a-typography>
                    <a-typography-title>{{classInfo.classname}}</a-typography-title>
                    <a-typography-paragraph>
                        {{classInfo.desc}}
                    </a-typography-paragraph>

                </a-typography>

                <!--选课和取消选课的按钮-->
                <!--需要动态展示-->

                <!--已经选课了，不展示，还没选，展示-->
                <a-button type="primary" :onclick="selectClass" :style="isChosen?{display:'none'}:{}">
                    选课
                </a-button>

                &nbsp;

                <a-popconfirm
                        title="确认取消课程？"
                        ok-text="是"
                        cancel-text="否"
                        @confirm="cancelClass"
                        @cancel="cancel"
                >

                    <a-button type="primary" :style="isChosen?{}:{display:'none'}">
                        取消选课
                    </a-button>
                </a-popconfirm>

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
            </a-spin>
        </div>


    </a-layout-content>
</template>

<script>
    import {ref} from "vue";
    import {onMounted} from "@vue/runtime-core";
    import {useRoute} from "vue-router";
    import axios from "axios";
    import {message} from "ant-design-vue";
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import router from '../../router';
    export default {
        name: "StudentChosenClass",

        setup() {
            const user = computed(() => store.state.user);

            const route = useRoute();

            const classId = route.query.id;

            const spinning = ref(true);

            /**
             * 标识当前学生有没有选择该课程
             * @type {Ref<UnwrapRef<boolean>>}
             */
            const isChosen = ref(false);

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

            /**
             * 在页面初始打开的情况下
             */
            const searchById = () => {
                console.log("传入的课程id为:"+classId);
                spinning.value=false;
                axios.get("/class/selectById/"+classId).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        classInfo.value = data.content;
                        teacherInfo.value = data.content.teacher;
                        console.log("查询出的教师信息为:"+classInfo.value.teacher.loginname);
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 查询数据库，判断该学生有没有选择该课程
             */
            const searchIsChosen = () => {
                axios.get("/student/isChosen",{
                    params: {
                        studentid: user.value.id,
                        classid: classId,
                    }
                }).then(resp => {
                    const data = resp.data;
                    if (data.success) {
                        console.log("查询学生是否选课信息成功!");
                        isChosen.value=data.content;
                    } else {
                        console.log("查询学生是否选课信息失败!");
                        message.error("查询失败，请先登录");
                        router.push("/");
                    }
                })
            }


            /**
             * 选择课程
             */
            const selectClass = () => {
                console.log("点击了选择课程按钮");
                axios.get("/student/selectClass",{
                    params: {
                        studentid:user.value.id,
                        classid:classId
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        message.success("选课成功");
                        /**
                         * 成功后，需要将页面刷新
                         */
                        spinning.value=true;
                        searchById();
                        searchIsChosen();
                        spinning.value=false;
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 取消选择课程
             */
            const cancelClass = () => {
                console.log("点击了取消按钮");
                axios.get("/student/cancelClass",{
                    params: {
                        studentid:user.value.id,
                        classid:classId
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        message.success("退选成功");
                        /**
                         * 成功后，需要将页面刷新
                         */
                        spinning.value=true;
                        searchById();
                        searchIsChosen();
                        spinning.value=false;
                    } else {
                        message.error(data.message);
                    }
                })

            }

            onMounted(()=>{
                spinning.value=true;
                searchById();
                searchIsChosen();
                spinning.value=false;
            });

            return {
                classInfo,
                searchById,
                teacherInfo,
                spinning,
                isChosen,
                selectClass,
                cancelClass
            };
        },
    }
</script>

<style scoped>

</style>