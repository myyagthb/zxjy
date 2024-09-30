<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <aside-view></aside-view>
      </el-aside>
      <el-main style="height: 98vh;">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>

import AsideView from "@/components/AsideView.vue";


</script>

<style scoped>
.common-layout{
  width: 100%;
  height: 98vh;
  background-color: #f4f4f4;
}

</style>