<template>
  <div class="base-header">
    <div class="menu_list_left">
      <div class="menu_item" >
        <el-button class="menu_item_button" style="color: #e89939;">
          <img :src="require('@/assets/images/base/cloud-icon.png')" alt="海讯云" class="icon-cloud">
          海讯云
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" @click="goHome">
          网站首页
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" @click="goOnlineCourse">
          在线课程
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" @click="goAbout">
          关于我们
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" @click="goMemberPrice">
          会员价格
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button">
          联系我们
        </el-button>
      </div>
    </div>

    <div class="menu_list_right">
      <div class="menu_item">
        <el-button class="menu_item_button">
          我要注册
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" style="background-color: orange;color: #fefff3;">
          立即登录
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>

//去网站首页
import router from "@/router";

const goHome = ()=>  {
  router.push("/base/home")
}

//去关于界面
const goAbout = ()=>  {
  router.push("/base/about")
}

//去课程详情
// const goCourseSpecifics = ()=>  {
//   router.push("/base/course-specifics")
// }

//去会员价格
const goMemberPrice = ()=>  {
  router.push("/base/member-price")
}

//去在线课程
const goOnlineCourse = ()=>  {
  router.push("/base/online-course")
}


</script>

<style scoped>
.base-header {
  height: 10%;
  width: 100%;
  background-color: #f7f7f8;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.icon-cloud{
  width: 50%;
  height: 50%;
  margin-right: 5px;
}

.menu_list_left{
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: left; /* 使按钮之间有间隔 */
}

.menu_list_right{
  width: 20%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: right; /* 使按钮之间有间隔 */
}


.menu_item{
  flex: 1; /*每个菜单项都撑满可用空间 */
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.menu_item_button{
  width: 100%;
  height: 60%;
  border: 0;
  background-color: #f5f5f5;
}
</style>