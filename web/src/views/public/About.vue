<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">


            <a-descriptions title="关于我们" layout="vertical">
                <a-descriptions-item label="UserName">
                    <b>FARO_Z</b>
                </a-descriptions-item>
                <a-descriptions-item label="Telephone">
                    <b>18012224600</b>
                </a-descriptions-item>
                <a-descriptions-item label="Live">
                    <b>Jiangsu,Nanjing</b>
                </a-descriptions-item>
                <a-descriptions-item label="Address" span="2">
                    <b>No. 1, Xixia Road, Nanjing Normal University, Nanjing, Jiangsu, China</b>
                </a-descriptions-item>
                <a-descriptions-item label="Remark">
                    <b>雨选课，自由的选课平台</b>
                </a-descriptions-item>
            </a-descriptions>

            <!--<a-collapse v-model:activeKey="activeKey" accordion>-->
            <!--    <a-collapse-panel key="1" header="我们的宗旨">-->
            <!--        <p>{{ text }}</p>-->
            <!--    </a-collapse-panel>-->
            <!--    <a-collapse-panel key="2" header="我们的目标" :disabled="false">-->
            <!--        <p>{{ text }}</p>-->
            <!--    </a-collapse-panel>-->
            <!--    <a-collapse-panel key="3" header="未来的希望">-->
            <!--        <p>{{ text }}</p>-->
            <!--    </a-collapse-panel>-->
            <!--</a-collapse>-->

            <br/>
            <br/>

            <h4>请为我们的平台打分:</h4>
            <!--<span>-->
            <!--        <a-rate v-model:value="value" :tooltips="desc" />-->
            <!--        <span class="ant-rate-text">{{ desc[value - 1] }}</span>-->
            <!--    </span>-->



        </div>
    </a-layout-content>
</template>

<script>
    export default {
        name: "About"
    }
</script>

<style scoped>

</style>