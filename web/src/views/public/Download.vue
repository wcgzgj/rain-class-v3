<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">



            <a-list :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 3 }" :data-source="data" :pagination="pagination">
                <template #renderItem="{ item }">
                    <a-list-item>
                        <a-card :title="item.title">
                            <router-link to="">
                                Microsoft Word
                            </router-link>
                        </a-card>
                    </a-list-item>
                </template>
            </a-list>


        </div>
    </a-layout-content>
</template>

<script>
    const data = [
        {
            title: 'Title 1',
        },
        {
            title: 'Title 2',
        },
        {
            title: 'Title 3',
        },
        {
            title: 'Title 4',
        },
        {
            title: 'Title 5',
        },
        {
            title: 'Title 6',
        },
    ];
    export default {

        name: "Download",
        setup() {
            /**
             * 分页
             */
            const pagination = {
                onChange: page => {
                    onSearch(page);
                },
                pageSize: 6,
                total: 10
            };

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

            return {
                data,
                pagination,
                onSearch
            }
        }
    }
</script>

<style scoped>

</style>