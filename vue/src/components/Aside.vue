<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        default-active="user"
        :default-openeds="[nav-list]"
        class="el-menu-vertical-demo">
      <el-sub-menu index="nav-list">
        <template #title><span>系统管理</span></template>
          <el-menu-item index="user" :route="{path: '/'}">用户管理</el-menu-item>
          <el-menu-item index="data" :route="{path: '/'}">数据管理</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>