<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script setup>

import { onMounted, onUnmounted} from "vue";
import axios from "axios";
import router from "router";
import {ElMessage} from "element-plus";

onMounted(() => {
  startSendHeart()
})

onUnmounted(() => {
  stopSendHeart()
  logout()
})

//退出登录代码
const logout = () => {
  let userId = sessionStorage.getItem("userId") || 'a'

  axios.get("/Logout",{
    params:{
      userId: userId
    }
  }).then(res => {
    console.log("退出登录执行")
    console.log(res)
  })
}



//心跳区域代码
let timer;

const sendHeart = () => {
  // 根据本地存储的userId区分用户,在生成二维码的时候存储的，也传给后台了
  let userId = sessionStorage.getItem('userId') || 'a'
  axios.get("/judgeUserIsOnline",{
    params:{
      userId: userId
    }
  }).then(res=>{
    console.log(res)
    if(!res.data.content){
      ElMessage({
        message: "登录已过期",
        type: "error"
      })

      //如果登录失效，就跳转到登录页面
      router.push("/login")


    }

  })
}

// const sendHeart = async () => {
//   // 获取本地存储的 userId
//   const userId = sessionStorage.getItem('userId') || 'a';
//
//   try {
//     // 发送初始请求
//     const res = await axios.get("/judgeUserIsOnline", { params: { userId } });
//
//     if (!res.data.content) {
//       // 如果用户不在线，尝试重新检查几次
//       for (let i = 0; i < 3; i++) {
//         const retryRes = await axios.get("/judgeUserIsOnline", { params: { userId } });
//
//         if (retryRes.data.content) {
//           // 如果用户重新上线，继续轮询
//           return;
//         }
//
//         // 显示错误消息
//         ElMessage({
//           message: "登录已过期",
//           type: "error"
//         });
//       }
//
//       //关闭轮询
//       stopSendHeart();
//       // 跳转到登录页面
//       router.push("/login");
//     }
//   } catch (error) {
//     console.error("请求出错:", error);
//     ElMessage({
//       message: "网络请求失败，请稍后再试",
//       type: "error"
//     });
//   }
// };


// 发送心跳
const startSendHeart = () => {
  timer = setInterval(() => {
    // 轮询后台，看用户是否扫码成功
    sendHeart();
  }, 30000); // 每隔2秒调用一次
}

// 停止发送心跳
const stopSendHeart = () => {
  if (timer) {
    clearInterval(timer);
  }
}

</script>