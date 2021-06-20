<!--教师评价-->
<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">

            <a-result
                    status="success"
                    title="您的评价良好"
                    sub-title="FARO_Z提供数据驱动"
            >
                <template #extra>
                    <a-button key="console" type="primary">了解</a-button>
                    <a-button key="buy">申诉</a-button>
                </template>
            </a-result>

        </div>
    </a-layout-content>
</template>

<script>
    export default {
        name: "TeacherComment"
    }
</script>

<style scoped>

</style>