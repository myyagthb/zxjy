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
        <router-link :to="getFullPath('/my-course')" style="text-decoration: none">
          <span>我的课程</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="2" key="/my-information">
        <router-link :to="getFullPath('/my-information')" style="text-decoration: none">
          <span>我的资料</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="3" key="/study-progress">
        <router-link :to="getFullPath('/study-progress')" style="text-decoration: none">
          <span>学习进度</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="4" key="/study-path-plan-one">
        <router-link :to="getFullPath('/study-path-plan-one')" style="text-decoration: none">
          <span>学习路径规划-1</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="5" key="/study-path-plan-two">
        <router-link :to="getFullPath('/study-path-plan-two')" style="text-decoration: none">
          <span>学习路径规划-2</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="6" key="/online-exam-list">
        <router-link :to="getFullPath('/online-exam-list')" style="text-decoration: none">
          <span>在线考试-考试罗列</span>
        </router-link>
      </el-menu-item>
    </el-menu>


  </div>


</template>

<script  setup>

import router from "@/router";
import {ref, watch} from "vue";

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath);
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath);
}


const selectedKeys = ref([]);
watch(() => router.currentRoute.value.path, (newValue) => {
  console.log('watch', newValue);
  selectedKeys.value = [];
  selectedKeys.value.push(newValue);
}, {immediate: true});

// 定义一个方法来动态添加公共路径
const getFullPath = (path) => {
  return `/student${path}`;
};



</script>

<style scoped>

</style>
