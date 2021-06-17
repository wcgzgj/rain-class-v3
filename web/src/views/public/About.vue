<template>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-breadcrumb :style="{ margin: '16px 0' }"><!--面包屑导航目前做不了，就把空间先留下了--></a-breadcrumb>
        <div :style="{ background: '#fff', padding: '24px', minHeight: '380px' }">
            <h1>关于我们</h1>
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