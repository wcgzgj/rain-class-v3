<!--学生已选课表-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <p style="font-size: 30px">我的课程</p>

            <!--<a-input-search-->
            <!--        v-model:value="searchForm.classname"-->
            <!--        placeholder="请输入课程名称"-->
            <!--        enter-button="搜索"-->
            <!--        size="large"-->
            <!--        @search="onSearch"-->
            <!--/>-->

            <a-list item-layout="vertical" size="large" :pagination="pagination"  :data-source="listData">
                <template #footer>
                    <div>
                        <p style="text-align: center">课程列表</p>
                    </div>
                </template>
                <template #renderItem="{ item }">
                    <a-list-item key="item.title">
                        <template #extra>
                            <img
                                    width="200"
                                    alt="logo"
                                    src="../../assets/ds.jpg"
                            />
                        </template>
                        <a-list-item-meta :description="'课程介绍'">
                            <template #title>
                                <router-link :to="'/studentChosenClass?id='+item.id">
                                    {{ item.classname }}
                                </router-link>
                            </template>
                        </a-list-item-meta>
                        {{ item.desc }}
                    </a-list-item>
                </template>
            </a-list>

        </div>
    </a-layout-content>
</template>

<script>
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import { message } from 'ant-design-vue';
    import {computed} from "@vue/reactivity";
    import store from "@/store";
    import {onMounted} from "@vue/runtime-core";
    import axios from "axios";


    export default {
        name: "StudentChosenList",
        setup() {

            const user = computed(() => store.state.user);

            /**
             * 分页
             */
            const pagination = {
                onChange: page => {
                    searchForm.value.pageNum=page;
                    onSearch(page);
                },
                pageSize: 3,
                total: 0
            };

            /**
             * 搜索
             * @type {Ref<UnwrapRef<string>>}
             */

            const listData = ref();

            const searchForm = ref({
                pageNum: 1,
                pageSize: 3,
                classname: ""
            });

            const onSearch = (pageNum) => {
                console.log(pageNum);
                console.log(searchForm.value.pageSize);

                /**
                 * 查询出所有  该学生已经选择的课程
                 */
                axios.get("/student/myClass",{
                    params:{
                        pageNum:searchForm.value.pageNum,
                        pageSize:searchForm.value.pageSize,
                        classname:searchForm.value.classname,
                        studentid:user.value.id,
                    }
                }).then(resp => {
                    const data = resp.data;
                    if (data.success) {
                        const pageInfo = data.content;
                        /**
                         * 为 dataList 赋值
                         * 同时修改 pagination 的 total 值
                         */
                        listData.value=pageInfo.list;
                        pagination.pageSize=pageInfo.pageSize;
                        pagination.total=pageInfo.total;

                    } else {
                        message.error(data.message);
                    }
                })
            }

            onMounted(()=>{
                onSearch(1);
            });


            return {
                listData,
                pagination,
                onSearch,
                searchForm,
                user
            };
        }
    }
</script>

<style scoped>

</style>