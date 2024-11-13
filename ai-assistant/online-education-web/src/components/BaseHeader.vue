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
        <el-button class="menu_item_button" @click="goContactUs">
          联系我们
        </el-button>
      </div>
      <div class="menu_item">
        <el-button class="menu_item_button" @click="goAIAssistantWeb">
          AI论文批改
        </el-button>
      </div>
    </div>

    <div class="menu_list_right">
      <div class="menu_item" v-if="isLogin && userRole === 0">
        <el-button class="menu_item_button" style="background-color: orange;color: #fefff3;" @click="goStudentView">
          去学生端
        </el-button>
      </div>
      <div class="menu_item" v-if="isLogin && userRole === 1">
        <el-button class="menu_item_button" style="background-color: orange;color: #fefff3;" @click="goTeacherView">
          去教师端
        </el-button>
      </div>
      <div class="menu_item" v-if="isLogin">
        <el-button class="menu_item_button"  @click="logout">
          退出登录
        </el-button>
      </div>
      <div class="menu_item" v-if="!isLogin">
        <el-button class="menu_item_button" @click="goRegister">
          我要注册
        </el-button>
      </div>
      <div class="menu_item" v-if="!isLogin">
        <el-button class="menu_item_button" style="background-color: orange;color: #fefff3;" @click="goLogin">
          立即登录
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import store from "@/store";
import {onMounted, ref, watch} from "vue";
import {isEmptyObject} from "../../public/js/utils";



//表示当前用户是否登录
const isLogin = ref(false)
const userRole = ref(-1)

onMounted(()=>{
  judgeIsLogin()
})

//判断对象是否为空
//const isEmptyObject = (obj) => Object.keys(obj).length === 0;

//判断是否登录
const judgeIsLogin = () => {
  //获取缓存中的用户
  let user = store.state.user
  if(!isEmptyObject(user)){
    isLogin.value = true
    //用户角色
    userRole.value = user.role
  } else {
    isLogin.value = false
  }

  console.log(isLogin.value)
}

// 监听 store.state.user 的变化
watch(() => store.state.user, (newUser) => {
  console.log(newUser)
  judgeIsLogin();
});

//去学生端界面
const goStudentView = ()=>{
  router.push("/student/my-course")
}

const goTeacherView = () => {
  router.push("/teacher/my-course")
}



//去网站首页
const goHome = ()=>  {
  router.push("/base/home")
}

//去关于界面
const goAbout = ()=>  {
  router.push("/base/about")
}

//去课程详情
const goContactUs = ()=>  {
  router.push("/base/contact-us")
}

//去会员价格
const goMemberPrice = ()=>  {
  router.push("/base/member-price")
}

//去在线课程
const goOnlineCourse = ()=>  {
  router.push("/base/online-course")
}
//去登录界面
const goLogin = () => {
  router.push("/base/login")
}
//去注册界面
const goRegister = () => {
  router.push("/base/register")
}
//去论文批改界面
const goAIAssistantWeb = ()=>{
  console.log("去论文批改项目")
}
//退出登录
const logout = () => {
  store.commit('setUser', {})
  //跳转到登录界面
  router.push("/base/login")
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