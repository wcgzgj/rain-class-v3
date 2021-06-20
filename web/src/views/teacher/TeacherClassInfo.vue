<!--教师课程信息-->
<!--包括课程中的学生信息-->
<!--学生的成绩等-->
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




                <br/>
                <h1>我的学生</h1>
                <!--下面，是选择了该课程的所有学生的信息-->
                <a-table :columns="columns" :data-source="students">
                    <template #name="{ text }">
                        <a>{{ text }}</a>
                    </template>

                    <template #action="{ record }">
                      <span>
                        <a-button type="primary" @click="showChangeScore(record)">修改成绩</a-button>
                      </span>
                    </template>
                </a-table>



            </a-spin>


            <a-modal
                    v-model:visible="changeScoreModal"
                    title="成绩修改"
                    ok-text="确认"
                    cancel-text="取消"
                    @ok="changeScore">
                <a-form :model="studentData" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
                    <a-form-item label="学生姓名">
                        {{studentData.realname}}
                    </a-form-item>
                    <a-form-item label="学生成绩">
                        <a-input v-model:value="studentData.score"/>
                    </a-form-item>
                </a-form>
            </a-modal>

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


    const columns = [
        {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '邮箱',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: '成绩',
            dataIndex: 'score',
            key: 'score',
        },
        {
            title: '操作',
            key: 'action',
            slots: {
                customRender: 'action',
            },
        },
    ];
    // const data =

    export default {
        name: "TeacherClassInfo",

        setup() {
            const user = computed(() => store.state.user);

            const route = useRoute();

            const classId = route.query.id;

            const spinning = ref(true);

            const classInfo=ref({})

            const teacherInfo = ref({});

            const changeScoreModal = ref(false);

            const students = ref([]);
            students.value=[
                {
                    key: '1',
                    name: 'John Brown',
                    email: 'zhang@qq.com',
                    score: 89,
                    id:1
                },
                {
                    key: '1',
                    name: 'John Brown',
                    email: 'zhang@qq.com',
                    score: 89,
                    id:2
                },
                {
                    key: '1',
                    name: 'John Brown',
                    email: 'zhang@qq.com',
                    score: 89,
                    id:3
                },
                {
                    key: '1',
                    name: 'John Brown',
                    email: 'zhang@qq.com',
                    score: 89,
                    id:4
                },

            ];


            /**
             * 在页面初始打开的情况下
             * 搜索课程信息
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
             * 修改学生成绩
             * 点击修改后，会弹出成绩的模态框，在模态框中回填已经存在的数据
             */
            const studentData = ref({
                studentid:"",
                realname:"",
                score:0,
                classid:"",
            });

            //展示学生信息，同时为要上传的信息赋值
            const showChangeScore = (record) => {
                console.log("被修改的学生id为:"+record.id);
                console.log("被修改的学生姓名为:"+record.name);
                changeScoreModal.value=true;
                studentData.value.realname=record.name;
                studentData.value.score=record.score;
                studentData.value.studentid=record.id;
                studentData.value.classid=classId;
            }

            //修改学生成绩
            const changeScore = () => {
                const score = studentData.value.score;
                if(score<0 || score>100) {
                    message.error("成绩输入错误，应在0-100之间");
                    return false;
                }
                axios.post("/teacher/changeScore",studentData.value).then(resp => {
                    const data = resp.data;
                    if (data.success) {
                        changeScoreModal.value=false;
                        //可能需要重新刷新一下页面
                        spinning.value=true;
                        searchById();
                        searchMyStudent();
                        spinning.value=false;
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 搜索当前教师的当前课程的学生信息
             * 其实只要传入课程id就行了
             */
            const searchMyStudent = () => {
                axios.get("/teacher/myStudent",{
                    params: {
                        classid:classId,
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        const stuArr = data.content;
                        console.log("获得的学生数组长度为:"+stuArr.length);
                        students.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }

            onMounted(()=>{
                spinning.value=true;
                searchById();
                searchMyStudent();
                spinning.value=false;
            });


            return {
                user,
                classId,
                spinning,
                classInfo,
                teacherInfo,

                /**
                 * 表格相关的数据
                 */
                students,
                columns,
                showChangeScore,


                /**
                 * 学生成绩修改
                 */
                changeScoreModal,
                studentData,
                changeScore,
            };
        },
    }
</script>

<style scoped>

</style>