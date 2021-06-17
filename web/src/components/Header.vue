<template>
    <a-layout-header class="header">
        <div class="logo"/>
        <a-menu
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="/">
                <router-link to="/">
                    雨选课
                </router-link>
            </a-menu-item>



            <a-menu-item key="/download" :style="1==2?{}:{display:'none'}">
                <router-link to="/download">
                    资源下载
                </router-link>
            </a-menu-item>

            <a-menu-item key="/about">
                <router-link to="/about">
                    关于我们
                </router-link>
            </a-menu-item>

            <a-menu-item key="/comment">
                <router-link to="/comment">
                    讨论天地
                </router-link>
            </a-menu-item>



            <!--学生操作下拉框-->
            <a-sub-menu key="stu">
                <template #title>
            <span>
              <span>学生</span>
            </span>
                </template>
                <a-menu-item key="s1">Tom</a-menu-item>
                <a-menu-item key="s2">Bill</a-menu-item>
                <a-menu-item key="s3">Alex</a-menu-item>
            </a-sub-menu>

            <!--老师操作下拉框-->
            <a-sub-menu key="tea">
                <template #title>
            <span>
              <span>老师</span>
            </span>
                </template>
                <a-menu-item key="t1">Tom</a-menu-item>
                <a-menu-item key="t2">Bill</a-menu-item>
                <a-menu-item key="t3">Alex</a-menu-item>
            </a-sub-menu>


            <!--管理员操作下拉框-->
            <a-sub-menu key="admin">
                <template #title>
            <span>
              <span>管理员</span>
            </span>
                </template>
                <a-menu-item key="a1">学生管理</a-menu-item>
                <a-menu-item key="a2">教师管理</a-menu-item>
                <a-menu-item key="a3">课程管理</a-menu-item>
                <a-menu-item key="a4">资源管理</a-menu-item>
                <a-menu-item key="a5">评论管理</a-menu-item>
            </a-sub-menu>


            <!--登录/注册下拉框-->
            <a-sub-menu key="login/register">
                <template #title>
            <span>
              <span>登录/注册</span>
            </span>
                </template>
                <a-menu-item key="l1">登录</a-menu-item>
                <a-menu-item key="l2">注册</a-menu-item>
            </a-sub-menu>


            <!--用户下拉框下拉框-->
            <a-sub-menu key="user">
                <template #title>
            <span>
              <span>欢迎您: FARO_Z</span>
            </span>
                </template>
                <a-menu-item key="u1">注销登录</a-menu-item>
                <a-menu-item key="u2">密码重置</a-menu-item>
                <a-menu-item key="u3">个人信息</a-menu-item>
            </a-sub-menu>



        </a-menu>
    </a-layout-header>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>

</style>