<!--学生待选课表-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <p style="font-size: 30px">全部课程</p>

            <a-input-search
                    v-model:value="searchValue"
                    placeholder="请输入课程名称"
                    enter-button="搜索"
                    size="large"
                    @search="onSearch"
            />

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
                        <a-list-item-meta :description="item.description">
                            <template #title>
                                <a :href="item.href">
                                    <router-link to="/classinfo">
                                        {{ item.title }}
                                    </router-link>
                                </a>
                            </template>
                        </a-list-item-meta>
                        {{ item.content }}
                    </a-list-item>
                </template>
            </a-list>

        </div>
    </a-layout-content>
</template>

<script>
    import { defineComponent,ref } from 'vue';
    import {Tool} from "@/util/Tool";
    import {message} from "ant-design-vue";
    import {AxiosInstance as axios} from "axios";
    import {onMounted} from "@vue/runtime-core";

    const listData = [];

    for (let i = 0; i < 23; i++) {
        listData.push({
            title: `数据结构 ${i}`,
            description:
                '数据结构介绍',
            content:
                '数据结构（英语：data structure）是计算机中存储、组织数据的方式。\n' +
                '\n' +
                '数据结构是一种具有一定逻辑关系，在计算机中应用某种存储结构，并且封装了相应操作的数据元素集合。它包含三方面的内容，逻辑关系、存储关系及操作。',
        });
    }

    export default {
        name: "StudentClassList",
        setup() {

            /**
             * 分页
             */
            const pagination = {
                onChange: page => {
                    onSearch(page);
                },
                pageSize: 3,
                total: 0
            };

            /**
             * 搜索
             * @type {Ref<UnwrapRef<string>>}
             */
            const searchValue = ref("");

            /**
             * 学生选课的页面，需要区别搜索
             * 该学生已经选过的科目，就不要显示了
             */
            const onSearch = (pageNum) => {
                console.log(pageNum);
                console.log(pagination.pageSize);
                // axios.get("/class/list",{
                //   pageNum: pageNum,
                //   pageSize: pagination.pageSize,
                //   name: searchValue.value
                // },{
                //   timeout:5000
                // }).then(resp => {
                //   const data = resp.data;
                //   if (data.success) {
                //     /**
                //      * 为 dataList 赋值
                //      * 同时修改 pagination 的 total 值
                //      */
                //
                //   } else {
                //     message.error(data.message);
                //   }
                // })
            }

            onMounted(()=>{
                onSearch(1);
            });


            return {
                searchValue,
                listData,
                pagination,
                onSearch
            };
        }
    }
</script>

<style scoped>

</style>