<!--密码重置-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <a-form :model="passwordForm" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-item label="原密码">
                    <a-input v-model:value="passwordForm.origin" :onblur="originBlur" />
                </a-form-item>
                <a-form-item label="新密码">
                    <a-input v-model:value="passwordForm.newPassword" />
                </a-form-item>
                <a-form-item label="确认新密码">
                    <a-input v-model:value="passwordForm.checkPassword" />
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="onSubmit">提交</a-button>
                    <a-button style="margin-left: 10px" @click="clearInput">重置</a-button>
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

    export default {
        name: "ResetPassword",
        setup() {

            /**
             * vuex 中存储的用户登录信息
             */
            const user = computed(() => store.state.user);

            /**
             * 表单提交信息
             */
            const passwordForm = ref({
                origin:"",
                newPassword:"",
                checkPassword:"",
                role:user.value.role,
                id:user.value.id,
                token:user.value.token
            });

            /**
             * 清空输入框
             */
            const clearInput = () => {
                passwordForm.value.origin="";
                passwordForm.value.newPassword="";
                passwordForm.value.checkPassword="";
                passwordForm.value.role="";
                passwordForm.value.id="";
                passwordForm.value.token="";
            }

            /**
             * 点击提交按钮后执行的操作
             * 向后端发送 post 请求，修改密码
             * @returns {boolean}
             */
            const onSubmit =() => {
                if (Tool.isEmpty(user.value.token)) {
                    message.error("错误，请先登录");
                    return false;
                }
                if (Tool.isEmpty(passwordForm.value.origin) ||
                Tool.isEmpty(passwordForm.value.newPassword) ||
                Tool.isEmpty(passwordForm.value.checkPassword)) {
                    message.error("错误，请输入完整信息！");
                    return false;
                }
                if (passwordForm.value.newPassword!=passwordForm.value.checkPassword) {
                    message.error("两次密码输入不一致！");
                    return false;
                }

                axios.post("/user/changePassword",passwordForm.value).then(resp=>{
                    const data = resp.data;
                    if(data.success) {
                        message.success("密码更新成功");
                        clearInput();
                    } else {
                        message.error(data.message);
                    }
                })
            }


            /**
             * 原密码输入框失去焦点
             */
            const originBlur = () => {
                console.log("尝试与redis缓存中的原密码进行对比");
                console.log("当前用户的token信息为:"+user.value.token);
                axios.get("/user/checkPassword",{
                    params: {
                        origin:passwordForm.value.origin,
                        token:user.value.token
                    }
                }).then(resp=> {
                    const data = resp.data;
                    if (data.success) {
                        //原密码与现在的密码一致
                    } else {
                        message.error(data.message);
                    }
                })
            }

            return {
                passwordForm,
                onSubmit,
                clearInput,
                originBlur,
            };
        },
    }
</script>

<style scoped>

</style>