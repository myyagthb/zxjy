<template>
  <div class="common-layout">
    <el-container class="login_container">
      <el-main>
        <el-row>
          <el-col :span="12">
            <div class="grid-content ep-bg-purple">
              <img :src="require('@/assets/images/login-background.png')" class="login_img"
                   style="width: 100%;height: 480px;"/>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content ep-bg-purple-light">
              <el-container>
                <el-header>
                  <div class="login_header">
                    <div class="tip">
                      <img :src="require('@/assets/images/shield.png')" width="15" height="15">
                      微信扫码登录
                    </div>
                    <div class="QRCode">
                      <img :src="require('@/assets/images/QRCode-image.png')" class="qrCode" @click="loadLoginQRCode">
                    </div>
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
                  <div v-if="isWeiXinLogin">
                    <img :src="loginQRCodePic" style="width: 200px;height: 200px;">
                    <p>请使用微信扫码登录</p>
                  </div>
                  <el-form :model="loginForm" label-width="auto" style="max-width: 600px" v-if="isMobileLogin">
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
                            <el-input class="input" placeholder="验证码" v-model="loginForm.validationCode"/>
                          </el-form-item>
                        </div>
                      </el-col>
                      <el-col :span="8">
                        <div class="grid-content ep-bg-purple-light">
                          <el-button type="primary" class="button" @click="sendCode">获取验证码</el-button>
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
  </div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import store from "@/store";

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


//获取微信二维码
const loadLoginQRCodePic = () => {
  //loginQRCodePic.value = new URL('@/assets/images/QRCode.png', import.meta.url).href;
  //这里随机生成本用户Id
  let userId = parseInt(Math.random() * 100000)
  //保存用户ID到本地变量
  sessionStorage.setItem("userId", userId);

  let data = {
    "expire_seconds": 604800,
    "action_name": "QR_SCENE",
    "action_info": {
      "scene": {"scene_id": userId}
    }
  }
  axios.post("/getQrCode", data).then(res => {
    console.log(res)
    if (res.status === 200) {
      //拼接微信二维码路径
      let qRCodeUrl = `https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${res.data.ticket}`

      console.log(qRCodeUrl)
      //渲染微信二维码供用户去扫描
      loginQRCodePic.value = new URL(qRCodeUrl, import.meta.url).href;
    }
  })
}


//加载微信登录二维码
const loadLoginQRCode = () => {
  //加载登录二维码
  loadLoginQRCodePic()
  isMobileLogin.value = !isMobileLogin.value
  isWeiXinLogin.value = !isWeiXinLogin.value

  //重新判断登录方式
  loadLoginType()

  // 开始轮询
  startLoop()
}

const loadLoginType = () => {
  if (isMobileLogin.value === true) {
    loginType.value = "验证码登录"
  } else {
    loginType.value = "微信登录"
  }
}


const loginForm = reactive({
  mobile: "",
  validationCode: ""
});
//获取短信验证码
const sendCode = () => {
}


const login = () => {
  axios.post("/user/login", loginForm).then(res => {
    let data = res.data
    console.log(data)
    if (data.code == 200) {
      loginForm.validationCode = res.data.content
      ElMessage({
        message: data.message,
        type: 'success',
      })
      store.commit("setUser", data.content)
      router.push("/main")
    } else {
      loginForm.validationCode = ""
      ElMessage({
        message: data.message,
        type: 'error',
      })
    }
  })
}

const loopFun = () => {
  console.log("执行轮询。。。")
  // 根据本地存储的userId区分用户,在生成二维码的时候存储的，也传给后台了
  let userId = sessionStorage.getItem('userId') || 'a'
  axios.get("Login",{
    params:{
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
  loopTimer = setInterval(() => {
    // 轮询后台，看用户是否扫码成功
    loopFun();
  }, 2000); // 每隔2秒调用一次
};

// 停止轮询
const stopLoop = () => {
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


.login_header {
  display: flex;
  align-items: center;
  justify-content: right;
}

.tip {
  background-color: #e4e8fb;
}

.QRCode {
  width: 60px;
  height: 60px;
}


.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-row {
  margin-bottom: 15px;
}

.input {
  height: 50px;
}

.button {
  height: 50px;
  background-color: #f2f4f3;
  color: #545454;
}

.login_button {
  height: 50px;
  width: 360px;
  margin-top: 60px;
  background-color: #7376ec;
}

.qrCode {
  width: 100%;
  height: 100%;
  clip-path: polygon(0 0, 100% 0%, 100% 100%);
}

</style>