<template>
  <div class="common-layout">
    <el-container class="login_container">
      <el-main>
        <el-row>
          <el-col :span="12">
            <div class="grid-content ep-bg-purple">
              <img :src="require('assets/images/login-background.png')" class="login_img"
                   style="width: 100%;height: 480px;"/>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content ep-bg-purple-light">
              <el-container>
                <el-header>
                  <div class="login_header">
                    <img :src="require('assets/images/shield.png')" width="15" height="15">
                    微信扫码登录
                    <img :src="require('assets/images/QRCode-image.png')" class="qrCode" @click="loadLoginQRCode">
                  </div>
                </el-header>
                <el-main>
                  <el-row class="el-row">
                    <el-col :span="24">
                      <div class="grid-content ep-bg-purple-dark">
                        <h2>{{ loginType }}</h2>
                      </div>
                    </el-col>
                  </el-row>
                  <div v-if="isWeiXinLogin" v-loading="qrCodeLoading" style="height: auto">
                    <img :src="loginQRCodePic" class="loginQRCodePic">
                    <p>请使用微信扫码登录</p>
                  </div>
                  <el-form :model="loginForm" label-width="auto" style="width: auto;height: auto" v-if="isMobileLogin">
                    <el-row class="el-row">
                      <el-col :span="24">
                        <div class="grid-content ep-bg-purple-dark">
                          <el-form-item>
                            <el-input class="input" placeholder="手机号" v-model="loginForm.mobile"/>
                          </el-form-item>
                        </div>
                      </el-col>
                    </el-row>
                    <el-row class="el-row">
                      <el-col :span="16">
                        <div class="grid-content ep-bg-purple">
                          <el-form-item>
                            <el-input class="input" placeholder="验证码" v-model="loginForm.code"/>
                          </el-form-item>
                        </div>
                      </el-col>
                      <el-col :span="8">
                        <div class="grid-content ep-bg-purple-light">
                          <el-button type="primary" class="button" :disabled="isCountingDown"
                                     @click="onShowPicCode">
                            <!--                            获取验证码-->
                            {{ isCountingDown ? countdown + 's' : '发送验证码' }}
                          </el-button>
                        </div>
                      </el-col>
                    </el-row>
                    <el-row class="el-row">
                      <el-col :span="24">
                        <div class="grid-content ep-bg-purple-dark">
                          <el-form-item>
                            <el-button class="login_button" type="primary" @click="login">登录</el-button>
                          </el-form-item>
                        </div>
                      </el-col>
                    </el-row>
                  </el-form>
                </el-main>
              </el-container>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <Vcode :show="isShow" @success="onSuccess" @close="onClose" />
  </div>
</template>

<script setup>
import {onMounted, onUnmounted, ref} from "vue";
import axios from "axios";

import router from "router";

import SnowflakeId from "snowflake-id";
import {ElMessage} from "element-plus";

//微信登录二维码
const loginQRCodePic = ref("")
//表示微信登录
const isWeiXinLogin = ref(false)
//表示验证码登录
const isMobileLogin = ref(true)
//表示用户选择的登录类型
const loginType = ref("")
onMounted(() => {
  //判断登录方式
  loadLoginType()
})


const snowflake = new SnowflakeId({
  mid: 42,
  offset: (2019 - 1970) * 31536000 * 1000
});

const qrCodeLoading = ref(true)

//获取微信二维码
const loadLoginQRCodePic = () => {
  //loginQRCodePic.value = new URL('assets/images/QRCode.png', import.meta.url).href;
  //这里随机生成本用户Id
  console.log(snowflake.generate())
  // let userId = snowflake.generate()
  let userId = parseInt(Math.random() * 100000)
  console.log("userId:" + userId)
  //保存用户ID到本地变量
  sessionStorage.setItem("userId", userId);

  let data = {
    "expire_seconds": 604800,
    "action_name": "QR_SCENE",
    "action_info": {
      "scene": {"scene_id": userId}
    }
  }
  axios.post("/backend/getQrCode", data).then(res => {
    console.log(res)
    if (res.status === 200) {
      let ticket = "";
      ticket = res.data.ticket
      //拼接微信二维码路径
      // let qRCodeUrl = `https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${res.data.ticket}`
      let qRCodeUrl = `https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${ticket}`

      qrCodeLoading.value = false

      console.log(qRCodeUrl)
      //渲染微信二维码供用户去扫描
      loginQRCodePic.value = new URL(qRCodeUrl, import.meta.url).href;
    }
  })
}

//判断轮询函数是否已经启动
const startLoopIsStart = ref(false)

//加载微信登录二维码
const loadLoginQRCode = () => {
  //加载登录二维码
  loadLoginQRCodePic()
  isMobileLogin.value = !isMobileLogin.value
  isWeiXinLogin.value = !isWeiXinLogin.value

  //重新判断登录方式
  loadLoginType()

  if (!startLoopIsStart.value) {
    // 开始轮询
    startLoop()
  }

}

const loadLoginType = () => {
  if (isMobileLogin.value === true) {
    loginType.value = "验证码登录"
  } else {
    loginType.value = "微信登录"
  }
}


//前端的图形验证码
import Vcode from "vue3-puzzle-vcode";


const isShow = ref(false);

//暂时图片验证码
const onShowPicCode = () => {
  //验证手机号码
  if(!isValidChinaPhoneNumber(loginForm.value.mobile)){
    ElMessage({
      type:"error",
      message: "请填写正确的手机号"
    })
    return
  }
  isShow.value = true;
};

const onClose = () => {
  isShow.value = false;
  //验证成功，调用发送验证码方法
  sendVerificationCode()
};

const onSuccess = () => {
  onClose(); // 验证成功，需要手动关闭模态框
};


const loginForm = ref({
  mobile: "",
  code: "",
  userId: null
});

//获取短信验证码
const sendCodeToUser = () => {
  axios.get("/backend/sendSmsCodeToMobile/"+loginForm.value.mobile).then(res => {
    let data = res.data
    if(data.code === 200){
      ElMessage({
        type: "success",
        message: data.msg
      })
    }
  })
}


const login = () => {
  let userId =  sessionStorage.getItem('userId')
  if( isNaN(userId) ){
    userId = parseInt(Math.random() * 100000)
  }
  sessionStorage.setItem("userId",userId)
  console.log("Retrieved or generated userId:", userId);
  loginForm.value.userId = userId
  console.log("LoginForm value:", loginForm.value);


  axios.post("/backend/login",loginForm.value).then(res => {
    let data = res.data
    if(data.code === 200){
      //表示登录成功，跳转到首页
      ElMessage({
        type: "success",
        message: data.msg
      })
      router.push("/chat")
    }
  })
}

const isValidChinaPhoneNumber = (phoneNumber) =>{
  // 定义正则表达式来匹配中国手机号码
  const regex = /^1[3456789]\d{9}$/;
  // 使用正则表达式测试输入的电话号码
  return regex.test(phoneNumber);
}


//做发送验证码成功的倒计时
let isCountingDown = ref(false);
let countdown = ref(60);

const sendVerificationCode = () => {
  //验证手机号码
  if(!isValidChinaPhoneNumber(loginForm.value.mobile)){
    ElMessage({
      type:"error",
      message: "请填写正确的手机号"
    })
    return
  }

  if (isCountingDown.value) return;

  // 模拟发送验证码
  console.log('发送验证码...');
  sendCodeToUser()

  isCountingDown.value = true;
  const timer = setInterval(() => {
    countdown.value--;
    if (countdown.value <= 0) {
      clearInterval(timer);
      isCountingDown.value = false;
      countdown.value = 60;
    }
  }, 1000);

  // 清理定时器
  onUnmounted(() => {
    clearInterval(timer);
  });
};


//扫码登录部分
const loopFun = () => {
  console.log("执行轮询。。。")
  // 根据本地存储的userId区分用户,在生成二维码的时候存储的，也传给后台了
  let userId = sessionStorage.getItem('userId') || parseInt(Math.random() * 100000)
  axios.get("Login", {
    params: {
      userId: userId
    }
  }).then(res => {
    console.log(res)
    // 如果用户已经登录，可以在这里处理并停止轮询
    if (res.data.login) {
      stopLoop();
      // 处理登录成功的逻辑
      router.push("/chat")
      console.log("用户已登录:", res.data);
    }

  })


}

let loopTimer; // 用于存储 setInterval 的返回值

// 启动轮询
const startLoop = () => {
  startLoopIsStart.value = true
  loopTimer = setInterval(() => {
    // 轮询后台，看用户是否扫码成功
    loopFun();
  }, 2000); // 每隔2秒调用一次
};

// 停止轮询
const stopLoop = () => {
  startLoopIsStart.value = false
  if (loopTimer) {
    clearInterval(loopTimer);
  }
};


</script>

<style scoped>
.common-layout {
  height: 98vh;
  width: 100%;
  background-color: #f2f4f3;
}

/*

.login_container {
  height: 65%;
  width: 50%;
  background-color: #ffffff;
  border-radius: 2%;
  text-align: center;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

 */

.login_container {
  height: auto; /* 移除固定高度 */
  max-width: 60%; /* 设置一个最大宽度 */
  width: 100%; /* 宽度为100%，但不超过最大宽度 */
  background-color: #ffffff;
  border-radius: 2%;
  text-align: center;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-sizing: border-box; /* 确保内边距和边框不会增加元素的总宽度 */
}

@media (max-width: 768px) {
  .login_container {
    max-width: 90%; /* 在小屏幕上稍微增加宽度 */
  }

  .el-col {
    flex: 0 0 100%; /* 小屏幕上一列占据整个宽度 */
  }
}

.login_img {
  width: 100%;
  height: 100%;
}


.login_header {
  display: flex;
  align-items: center;
  justify-content: right;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-row {
  margin-bottom: 15px;
}


.loginQRCodePic {
  width: 70%;
  height: 70%;
}


.input {
  height: 50px;
}

.button {
  height: 50px;
  width: 90%;
  background-color: #f2f4f3;
  color: #545454;
}

.login_button {
  height: 50px;
  width: 100%;
  margin-top: 60px;
  background-color: #7376ec;
}


.qrCode {
  width: 20%;
  height: 20%;
  clip-path: polygon(0 0, 100% 0%, 100% 100%);
}


</style>
