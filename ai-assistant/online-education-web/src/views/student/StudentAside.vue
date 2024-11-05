

<template>
  <div>
    <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        v-model:selectedKeys="selectedKeys"
        :openKeys="['student']"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-menu-item index="1" key="/my-course">
        <router-link to="/student/my-course" style="text-decoration: none">
          <span>我的课程</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="2" key="/my-information">
        <router-link to="/student/my-information" style="text-decoration: none">
          <span>我的资料</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="3" key="/welcome">
        <router-link to="/welcome" style="text-decoration: none">
          <span>课程日历</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="4" key="/welcome">
        <router-link to="/welcome" style="text-decoration: none">
          <span>学习规划</span>
        </router-link>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script setup>

import router from "@/router";
import {ref, watch} from "vue";

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}


const selectedKeys = ref([]);
watch(() => router.currentRoute.value.path, (newValue) => {
  console.log('watch', newValue);
  selectedKeys.value = [];
  selectedKeys.value.push(newValue);
}, {immediate: true});

</script>

<style scoped>

</style>