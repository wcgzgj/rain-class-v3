<template>
  <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
    <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>

    <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

      <p style="font-size: 30px">课程列表</p>

      <a-input-search
              v-model:value="searchForm.classname"
              placeholder="请输入课程名称"
              enter-button="搜索"
              size="large"
              @search="onSearch"
      />

      <a-list item-layout="vertical" size="large" :pagination="pagination"  :data-source="listData" :loading="spinning">
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
                        src="../assets/ds.jpg"
                />
              </template>
              <a-list-item-meta :description="'课程介绍'">
                <template #title>
                  <router-link :to="'/classinfo?id='+item.id">
                    {{ item.classname }}
                  </router-link>
                </template>
              </a-list-item-meta>
              {{ item.desc }}
            </a-list-item>
          </template>
        </a-list >



    </div>


  </a-layout-content>

</template>

<script >
import { defineComponent,ref } from 'vue';
import {Tool} from "@/util/Tool";
import {message} from "ant-design-vue";
import axios from 'axios';
import {onMounted} from "@vue/runtime-core";


export default defineComponent({
  name: 'Home',
  setup() {
    const spinning = ref(true);

    /**
     * 分页
     */
    const pagination = {
      onChange: page => {
        spinning.value=true;
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
      spinning.value=false;

      axios.get("/class/list",{
        params:{
          pageNum:searchForm.value.pageNum,
          pageSize:searchForm.value.pageSize,
          classname:searchForm.value.classname
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
      spinning
    };
  }

});

</script>

<style scoped>
  /*#components-layout-demo-fixed .logo {*/
  /*  width: 120px;*/
  /*  height: 31px;*/
  /*  background: rgba(255, 255, 255, 0.2);*/
  /*  margin: 16px 24px 16px 0;*/
  /*  float: left;*/
  /*}*/
  /*.site-layout .site-layout-background {*/
  /*  background: #fff;*/
  /*}*/

  /*[data-theme='dark'] .site-layout .site-layout-background {*/
  /*  background: #141414;*/
  /*}*/

</style>
