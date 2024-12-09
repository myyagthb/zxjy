

<template>
  <div>
    <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        :openKeys="['main']"
        v-model:selectedKeys="selectedKeys"
        :collapse="isCollapse"
        @open="handleOpen"
        @close="handleClose"
        style="background-color: #eceef5;"
    >
      <el-menu-item index="1" class="el-menu-item" key="/chat">
        <img :src="require('assets/images/cloud-image.png')" style="width: 20px;height: 20px;margin-top: 25%;">
        <router-link to="/chat" style="text-decoration: none">
          <span>海讯云AI</span>
        </router-link>
      </el-menu-item>

      <el-menu-item index="2" class="el-menu-item" key="/chat">
        <img :src="require('assets/images/main/message.png')" style="width: 20px;height: 20px;margin-top: 25%;">
        <router-link to="/chat" style="text-decoration: none">
          <span>聊天</span>
        </router-link>
      </el-menu-item>

      <el-menu-item index="3" class="el-menu-item" key="/present">
        <img :src="require('assets/images/main/present.png')" style="width: 20px;height: 20px;margin-top: 25%;">
        <router-link to="/present" style="text-decoration: none">
          <span>礼物</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="4" class="el-menu-item" key="/history">
        <img :src="require('assets/images/main/file.png')" style="width: 20px;height: 20px;margin-top: 25%;">
        <router-link to="/history" style="text-decoration: none">
          <span>历史记录</span>
        </router-link>
      </el-menu-item>
      <el-menu-item index="5" class="el-menu-item" key="/user">
        <img :src="require('assets/images/main/vip-user.png')" style="width: 20px;height: 20px;margin-top: 25%;">
        <router-link to="/user" style="text-decoration: none">
          <span>会员账户</span>
        </router-link>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script setup>
import {ref, watch} from "vue";
import router from "router";

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
const isCollapse = ref(false)


</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 600px;
}

.el-menu-item{
  width: 100%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

</style>