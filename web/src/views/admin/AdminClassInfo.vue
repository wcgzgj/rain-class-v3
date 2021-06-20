<!--课程管理界面-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <a-form :model="classData" :label-col="{ span: 4 }" :wrapper-col="{ span: 18 }">
                <a-form-item label="课程封面">
                    <input type="file" v-on:change="uploadImage" id="file-upload-input">
                    <a-image
                            style="margin-top: 20px"
                            :width="200"
                            :src="basePicPath+classData.path"
                    />
                </a-form-item>
                <a-form-item label="课程名称">
                    <a-input v-model:value="classData.classname" />
                </a-form-item>
                <a-form-item label="任课教师">

                    <!--需要遍历教师列表，-->
                    <!--动态的显示所有教师信息-->
                    <a-select
                            v-model:value="classData.teacherid"
                            style="width: 120px"
                            @focus="focus"
                            ref="select"
                            @change="handleChange"
                    >
                        <!--使用 v-for 循环遍历查出的老师信息-->
                        <a-select-option v-for="teacher in teacherList" :value="teacher.id">{{teacher.realname}}</a-select-option>
                    </a-select>

                </a-form-item>
                <a-form-item label="上课时间">
                    <a-input v-model:value="classData.starttime" />
                </a-form-item>
                <a-form-item label="上课地点">
                    <a-input v-model:value="classData.place" />
                </a-form-item>
                <a-form-item label="注意事项">
                    <a-input v-model:value="classData.notice" />
                </a-form-item>
                <a-form-item label="课程类型">
                    <a-select
                            v-model:value="classData.type"
                            style="width: 120px"
                            @focus="focus"
                            ref="select"
                            @change="handleChange"
                    >
                        <a-select-option value="专业主干课">专业主干课</a-select-option>
                        <a-select-option value="选修课">选修课</a-select-option>
                        <a-select-option value="博雅课">博雅课</a-select-option>=
                    </a-select>
                </a-form-item>
                <a-form-item label="学分">
                    <a-input-number id="inputNumber" v-model:value="classData.credit" :min="1" :max="6" />
                </a-form-item>
                <a-form-item label="课程介绍">
                    <a-textarea v-model:value="classData.desc" placeholder="请输入课程介绍" :rows="4" />
                </a-form-item>

                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="save">保存</a-button>
                </a-form-item>

            </a-form>

        </div>
    </a-layout-content>
</template>

<script>
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import axios from 'axios';
    import {useRoute} from "vue-router";
    import router from '../../router';
    import {onMounted} from "@vue/runtime-core";
    export default {
        name: "AdminClassInfo",
        setup() {
            const route = useRoute();

            const user = computed(() => store.state.user);

            const classId = route.query.id;

            const picShowPath = ref("");

            const basePicPath=ref("");
            basePicPath.value+=process.env.VUE_APP_SERVER+"/disPic/";

            // 表单提交的内容
            const classData = ref({
                id:classId,
                classname:"",
                teacherid:"2",
                starttime:"",
                code:"",
                place:"",
                notice:"",
                type:"",
                credit:"",
                desc:"",
                createtime:"",
                path:"",
            });


            /**
             * 下面，是 router 的使用方法
             * 可以在 url 中获取传入的参数
             */
            //axios.get("/doc/all/"+route.query.ebookId)

            /**
             * 根据课程id，查询课程信息
             */
            const getClassInfo = () => {
                axios.get("/class/selectById/"+classId).then(resp => {
                    const data = resp.data;
                    if (data.success) {
                        console.log("课程信息查询成功");
                        classData.value=data.content;
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 图片上传
             */
            const uploadImage = () => {
                let formData = new FormData();
                console.log("当前文件的个数为："+document.querySelector("#file-upload-input").files.length);
                let file = document.querySelector("#file-upload-input").files[0];
                formData.append("file",file)
                console.log("等待上传的文件信息为:"+formData.get("file"));
                axios.post("/class/uploadPic",formData).then(resp=>{
                    const data = resp.data;
                    if (data.success) {
                        message.success("图片上传成功")
                        classData.value.path=data.content.path;
                        picShowPath.value=data.content.showPath,
                        console.log("回显的图片路径为："+picShowPath.value);
                    } else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 获取所有的老师信息
             * 好在修改页面动态显示
             */
            const teacherList = ref([]);

            const searchTeacherList = () => {
                axios.get("/teacher/getAll").then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        teacherList.value=data.content;
                        console.log("获取的教师列表长度为："+teacherList.value.length);
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 保存课程信息
             */
            const save = () => {
                axios.post("/class/save",classData.value).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        console.log("更新成功！");
                        message.success("更新成功");
                        router.push("/adminClassList");
                    } else {
                        message.error(data.message);
                    }
                })
            }


            onMounted(()=>{
                console.log("传入的课程id为:"+classId);
                getClassInfo();
                searchTeacherList();
            });

            return {
                classData,
                teacherList,
                getClassInfo,
                save,
                uploadImage,
                picShowPath,
                searchTeacherList,
                basePicPath
            };
        },
    }
</script>

<style scoped>

</style>