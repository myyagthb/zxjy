<template>
  <div class="common-layout">
    <el-container>
      <el-main>
        <el-scrollbar class="scrollbar" ref="scrollbarRef" always @scroll="handleScroll">
          <div ref="innerRef">
            <div class="div-message" v-for="(item,index) in chatList" :key="index">
              <div class="message-box-opposite" v-if="item.isMe === false">
                <div class="avatar">
                  <el-avatar shape="square" :size="50" :src="item.avatar" style="background-color: white"/>
                </div>
                <div class="message">
                  {{ item.message }}
                </div>
              </div>
              <div class="message-box-self" v-else-if="item.isMe === true" >
                <div class="message">
                  {{ item.message }}
                </div>
                <div class="avatar">
                  <el-avatar shape="square" :size="50" :src="item.avatar"/>
                </div>
              </div>
            </div>
          </div>
        </el-scrollbar>
      </el-main>
      <el-footer>
        <el-card style="max-width: 100%">
          <template #header>
            <div class="card-header">
              <div class="card-header-menu">
                <img :src="require('assets/images/main/save.png')" class="card-icon"/>
                保存会话
              </div>
              <div class="card-header-menu">
                <img :src="require('assets/images/main/clear.png')" class="card-icon"/>
                清空会话
              </div>
            </div>
          </template>


          <div style="display: flex;align-items: center;justify-content: left;">
            <el-input
                v-model="textarea"
                style="width: 95%"
                :rows="5"
                type="textarea"
                placeholder="请在这里输入文本"
                resize="none"
            />
            <el-button :icon="Position" circle style="background-color: #588fe1;width: 60px;height: 60px;color: white;margin-left: 20px;" @click="send"></el-button>
          </div>
        </el-card>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup >

import {Position} from "@element-plus/icons-vue";

import {nextTick, onMounted, ref} from 'vue'


const chatList = ref([])

onMounted(()=>{
  chatList.value.push({
    isMe: true,
    avatar: require('assets/images/avatar/default-avatar.png'),
    message: "谈谈你对AI的看法"
  })
  chatList.value.push({
    isMe: false,
    avatar: require('assets/images/avatar/ai-avatar.png'),
    message: "人工智能（AI）正在深刻地改变我们的世界。它通过自动化和优化各种任务，提高了效率和生产力。在医疗、教育、交通等领域，AI的应用带来了许多积极的变化，比如更精准的疾病诊断、个性化的学习体验以及更安全的自动驾驶技术。\n" +
        "\n" +
        "然而，AI的发展也带来了一些挑战。就业市场可能会受到影响，某些工作可能会被自动化取代，这要求人们不断学习新技能以适应变化。此外，数据隐私和算法偏见是重要的伦理问题，需要制定相应的法规来保护个人权利和社会公平。\n" +
        "\n" +
        "总体来说，AI是一个强大的工具，能够为人类带来巨大的好处，但同时也需要谨慎管理和监管，确保其发展符合道德标准，并惠及所有人。通过政府、企业和公众的合作，我们可以最大化AI的潜力，同时减轻潜在的风险。"
  })

  chatList.value.push({
    isMe: false,
    avatar: require('assets/images/avatar/ai-avatar.png'),
    message: "人工智能（AI）正在深刻地改变我们的世界。它通过自动化和优化各种任务，提高了效率和生产力。在医疗、教育、交通等领域，AI的应用带来了许多积极的变化，比如更精准的疾病诊断、个性化的学习体验以及更安全的自动驾驶技术。\n" +
        "\n" +
        "然而，AI的发展也带来了一些挑战。就业市场可能会受到影响，某些工作可能会被自动化取代，这要求人们不断学习新技能以适应变化。此外，数据隐私和算法偏见是重要的伦理问题，需要制定相应的法规来保护个人权利和社会公平。\n" +
        "\n" +
        "总体来说，AI是一个强大的工具，能够为人类带来巨大的好处，但同时也需要谨慎管理和监管，确保其发展符合道德标准，并惠及所有人。通过政府、企业和公众的合作，我们可以最大化AI的潜力，同时减轻潜在的风险。"
  })

  chatList.value.push({
    isMe: false,
    avatar: require('assets/images/avatar/ai-avatar.png'),
    message: "人工智能（AI）正在深刻地改变我们的世界。它通过自动化和优化各种任务，提高了效率和生产力。在医疗、教育、交通等领域，AI的应用带来了许多积极的变化，比如更精准的疾病诊断、个性化的学习体验以及更安全的自动驾驶技术。\n" +
        "\n" +
        "然而，AI的发展也带来了一些挑战。就业市场可能会受到影响，某些工作可能会被自动化取代，这要求人们不断学习新技能以适应变化。此外，数据隐私和算法偏见是重要的伦理问题，需要制定相应的法规来保护个人权利和社会公平。\n" +
        "\n" +
        "总体来说，AI是一个强大的工具，能够为人类带来巨大的好处，但同时也需要谨慎管理和监管，确保其发展符合道德标准，并惠及所有人。通过政府、企业和公众的合作，我们可以最大化AI的潜力，同时减轻潜在的风险。"
  })

  setScrollToBottom();


})

const textarea = ref("")

const isMe = ref(true)

const send = ()=>{
  if(isMe.value){
    sendMessage()
  }else {
    receiveMessage()
  }
  isMe.value = !isMe.value
}

async function sendMessage() {
  if (textarea.value.trim()) {
    chatList.value.push({
      isMe: true,
      avatar: require('assets/images/avatar/default-avatar.png'),
      message: textarea.value
    });
    textarea.value = "";
    setScrollToBottom();
  }
}

async function receiveMessage() {
  if (textarea.value.trim()) {
    chatList.value.push({
      isMe: false,
      avatar: require('assets/images/avatar/ai-avatar.png'),
      message: textarea.value
    });
    textarea.value = "";
    setScrollToBottom();
  }
}






//调整消息在对底部
// 自动滚动到底部
// 定义将滚动条定位在底部的方法
/**
 * 当触发滚动事件时，返回滚动的距离
 */

function handleScroll({ scrollTop }) {
  console.log('scrollTop', scrollTop)
}

const scrollbarRef = ref() // 滚动条实例
const innerRef = ref() // 计数器内部实例

/**
 * 控制滚动条滚动到容器的底部
 */


async function setScrollToBottom() {
  await nextTick();
  const max = innerRef.value?.clientHeight || 0;
  console.log('max', max);
  if (scrollbarRef.value) {
    scrollbarRef.value.setScrollTop(max);
  }
}

</script>

<!--<style scoped>-->
<!--.common-layout {-->
<!--  height: 98vh;-->
<!--  width: 100%;-->
<!--  background-color: #f2f4f3;-->
<!--}-->



<!--/*消息盒子样式*/-->
<!--.scrollbar {-->
<!--  border-left: 1px solid red;-->
<!--  border-right: 1px solid red;-->
<!--  top: -20px;-->
<!--  width: 99%;-->
<!--  height: 100vh;-->
<!--  min-height: 400px;-->
<!--  max-height: 550px;-->
<!--  background-color: #f8f8fa;-->
<!--}-->

<!--.div-message {-->
<!--  margin-top: 10px;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.message-box-opposite {-->
<!--  display: flex;-->
<!--  align-content: center;-->
<!--  justify-content: flex-start;-->
<!--}-->

<!--.message-box-opposite > .avatar {-->
<!--  margin-right: 10px;-->
<!--}-->

<!--.message-box-opposite > .message {-->
<!--  overflow-anchor: auto;-->
<!--  /*border: 2px solid black;*/-->
<!--  border-radius: 2%;-->
<!--  max-width: 600px;-->
<!--  text-align: left;-->
<!--  color: black;-->
<!--  background-color: #ffffff;-->
<!--  padding: 8px;-->
<!--}-->

<!--.message-box-self {-->
<!--  display: flex;-->
<!--  align-content: center;-->
<!--  justify-content: flex-end;-->
<!--}-->

<!--.message-box-self > .avatar {-->
<!--  margin-left: 10px;-->
<!--}-->

<!--.message-box-self > .message {-->
<!--  overflow-anchor: auto;-->
<!--  /*border: 2px solid black;*/-->
<!--  border-radius: 5%;-->
<!--  max-width: 600px;-->
<!--  text-align: left;-->
<!--  color: black;-->
<!--  background-color: #ffffff;-->

<!--  padding: 8px;-->
<!--}-->



<!--.card-header{-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: left;-->
<!--}-->
<!--.card-header-menu{-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  margin-right: 5px;-->
<!--}-->

<!--.card-icon{-->
<!--  width: 20px;-->
<!--  height: 20px;-->
<!--  margin-right: 5px;-->
<!--}-->
<!--</style>-->


<style scoped>
.common-layout {
  height: 80vh;
  width: 100%;
  background-color: #f2f4f3;
  display: flex;
  flex-direction: column;
}

.el-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.el-main {
  flex: 1;
  overflow: hidden;
}

.scrollbar {
  height: 100%;
  padding: 10px;
  background-color: #f8f8fa;
}

.message-container {
  display: flex;
  flex-direction: column;
}

.div-message {
  margin: 10px 0;
}

.message-box-opposite {
  display: flex;
  align-items: center;
}

.message-box-opposite > .avatar {
  margin-right: 10px;
}

.message-box-opposite > .message {
  border-radius: 8px;
  max-width: 60%;
  text-align: left;
  color: black;
  background-color: #ffffff;
  padding: 8px;
}

.message-box-self {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.message-box-self > .avatar {
  margin-left: 10px;
}

.message-box-self > .message {
  border-radius: 8px;
  max-width: 60%;
  text-align: left;
  color: black;
  background-color: #e0e0e0;
  padding: 8px;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: left;
}

.card-header-menu {
  display: flex;
  align-items: center;
  margin-left: 1%;
}

.card-icon {
  width: 20px;
  height: 20px;
  margin-right: 5px;
}

.input-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
}

@media (max-width: 768px) {
  .message-box-opposite > .message,
  .message-box-self > .message {
    max-width: 90%;
  }

  .input-container {
    flex-direction: column;
  }

  .input-container .el-input {
    margin-bottom: 10px;
  }

  .input-container .el-button {
    margin-left: 0;
  }
}
</style>