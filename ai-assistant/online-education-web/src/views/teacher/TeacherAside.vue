

<template>
  <div>
    <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        v-model:selectedKeys="selectedKeys"
        :openKeys="['teacher']"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-menu-item index="1" key="/my-course">
        <router-link to="/teacher/my-course" style="text-decoration: none">
          <span>我的课程</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="3" key="/upload-course">
        <router-link to="/teacher/upload-course" style="text-decoration: none">
          <span>添加课程</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="3" key="/upload-video">
        <router-link to="/teacher/upload-video" style="text-decoration: none">
          <span>上传课程视频</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="4" key="/">
        <router-link to="/teacher/course-calendar" style="text-decoration: none">
          <span>课程日历</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="5" key="/">
        <router-link to="/teacher/test-management" style="text-decoration: none">
          <span>试题管理</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="6" key="/">
        <router-link to="/teacher/questionBank-management" style="text-decoration: none">
          <span>题库管理</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="7" key="/">
        <router-link to="/teacher/mytest-management" style="text-decoration: none">
          <span>Test</span>
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