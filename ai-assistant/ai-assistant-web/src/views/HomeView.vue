<template>
  <div class="common-layout">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
    >
      <el-menu-item index="0">
        <img
            style="width: 80px"
            :src="require('assets/images/cloud-image.png')"
            alt="海讯云AI"
        />
        <div class="sign_box">
          <div>
            <h1>海讯云AI</h1>
          </div>
          <div>
            你的学术科研AI助手
          </div>
        </div>
      </el-menu-item>
      <el-menu-item index="1">
        <div class="weixin_group">
          <img :src="require('assets/images/user-group.png')" width="35" height="35" style="margin-right: 10px">
          微信群
        </div>
      </el-menu-item>
      <el-menu-item index="2">
        <el-button>登录</el-button>
      </el-menu-item>
    </el-menu>
    <el-container>

      <el-header>
        <h1 class="title">一站式学术科研AI助手</h1>
      </el-header>
      <el-main>
        <div class="menu_cards">
          <div v-for="(item,index) in menus" :key="index" class="block menu_card">
            <el-image style="width: 100px; height: 100px" :src="item.iconUrl" :fit="fit" />
            <h4>{{ item.title }}</h4>
            <div>
              <span class="demonstration">{{ item.description }}</span>
            </div>
            <el-button class="menu_card_button" round>{{item.buttonText}}</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'

const activeIndex = ref('2')
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}


onMounted(()=>{

})



const menus = ref([
  {
    iconUrl: require("assets/images/menu/paper.png"),
    title: "论文助手",
    description: "类似ChatGPT聊天神器，撰写论文的神器",
    buttonText: "立即使用"
  },{
    iconUrl: require("assets/images/menu/paper-polish.png"),
    title: "论文润色",
    description: "类似ChatGPT聊天神器，撰写论文的神器",
    buttonText: "敬请期待"
  },{
    iconUrl: require("assets/images/menu/paper-read.png"),
    title: "论文助手",
    description: "类似ChatGPT聊天神器，撰写论文的神器",
    buttonText: "敬请期待"
  },{
    iconUrl: require("assets/images/menu/paper-more.png"),
    title: "论文助手",
    description: "类似ChatGPT聊天神器，撰写论文的神器",
    buttonText: "敬请期待"
  }
])


</script>

<style scoped>
.common-layout{
  width: 100%;
  height: 98vh;
  background: linear-gradient(to right, #f1f1e4, #c8e2f1); /* 从左到右的渐变 */
}


.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}

.sign_box{
  display: flex;
  align-items: center;
  justify-content: center;
}

.sign_box >div> h1{
  margin-left: 10px;
  margin-right: 10px;
  font-size: 30px;
}

.sign_box >div{
  font-size: 14px;
}

.weixin_group{
  display: flex;
  align-items: center;
  justify-content: center;
}

.title{
  font-weight: bold;
  font-size: 36px;
  margin-top: 5%;
}

.menu_cards{
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10%;
}
.menu_card{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 18%;
  height: 400px;

  margin-left: 15px;
  margin-right: 15px;
  background-color: #f0f3f2;

}

.menu_card_button{

  font-weight: bold;
  width: 160px;
  height: 24px;
  margin-top: 10%;

}

</style>
