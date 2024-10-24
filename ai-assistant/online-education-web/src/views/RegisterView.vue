<template>
  <div class="main_div">
    <div class="left_div">
      <div style="width:100%;display: flex;align-items: center;justify-content: start;">
        <h1>学员感受</h1>
      </div>

      <div style="text-align: left;">
        老师的教学方式非常生动有趣，让我对这门课程产生了浓厚的兴趣。这门课程的教材非常详细，让我能够更好地理解课程内容。课程的难度适中，让我能够顺利掌握知识。
      </div>
      <br>
      <br>
      <br>
      <el-card class="box-card">
        <div style="text-align: left;padding: 30px;">
          网页设计课程为我奠定了坚实的基础。老师们知识渊博，给予我支持，
          互动学习环境引人入胜。我强烈推荐它!网页设计课程为我奠定了坚实的基础。
          老师们知识渊博，给予我支持互动学习环境引人入胜。我强烈推荐它!
        </div>
        <hr />
        <div class="clearfix">
          <div class="block">
            <el-avatar shape="square" :size="50" :src="squareUrl" />
          </div>
          <div class="clearfix_name">
            徐欣怡
          </div>
          <div style="margin-left: 60%;">
            <el-button>查看更多</el-button>
          </div>
        </div>
      </el-card>

    </div>
    <div class="right_div">
      <div class="common-layout">
        <el-container>
          <el-header class="form_header">
            <div class="from_title">
              注册
            </div>
            <p>创建一个账户以解锁独家功能</p>
          </el-header>
          <el-main>
            <el-form :model="loginForm" label-width="auto" style="max-width: 600px">
              <el-form-item label="用户名">
                <el-input v-model="loginForm.username" placeholder="请输入您的邮箱" class="form_input" />
              </el-form-item>
              <el-form-item label="Email">
                <el-input v-model="loginForm.email" placeholder="请输入您的邮箱" class="form_input" />
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="loginForm.password" type="password" placeholder="请输入您的密码" class="form_input" />
              </el-form-item>
              <el-form-item>
                <el-checkbox>记住我</el-checkbox>我同意<a href="#">使用条款</a>和<a href="#">隐私政策</a>
              </el-form-item>
            </el-form>
            <el-button  @click="onSubmit" class="form_button" style="background-color: orange;color: white;">注册</el-button>
          </el-main>
          <div class="hr-text">
            <span>其他方式</span>
          </div>
          <el-footer>
            <el-button  @click="onSubmit" class="form_button" style="background-color: #f7f7f8;">
              <el-icon><Iphone /></el-icon>
              使用其它方式登录
            </el-button>
            <br>
            <br>
            <div>
              已有账户<a href="#">立即登录</a><el-icon><TopRight /></el-icon>
            </div>
          </el-footer>
        </el-container>
      </div>


    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'

// do not use same name with ref
const loginForm = ref({
  email: '',
  password: '',
})

const onSubmit = () => {
  console.log('submit!')
}


onMounted(()=> {
  onLoadTeacherAvatar()
})
const squareUrl = ref("")

const onLoadTeacherAvatar = ()=> {
  squareUrl.value = new URL('@/assets/images/login/teacher.jpeg', import.meta.url).href;
}

</script>

<style scoped>


.main_div{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.left_div{
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.right_div{
  width: 30%;
}

.common-layout{
  margin-top: 20%;
  margin-bottom: 20%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  min-height: 600px;
  height: 100%;
}

.form_header{
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}


.from_title{
  font-weight: bold;
  font-size: 30px;
}

.form_input{
  width: 100%;
  height: 40px;
  background-color: #fcfcfd;
}
.form_button{
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

}




/*卡片样式*/
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix{
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.clearfix_name{
  margin-left: 5%;
}

.box-card {
  width: 100%;
}

.hr-text {
  display: flex;
  align-items: center;
  text-align: center;
  margin-top: 5%;
  margin-bottom: 5%;
}

.hr-text span {
  padding: 0 15px; /* 文字两边的内边距 */
  background-color: white; /* 确保文字背景是白色的，这样横线不会穿过文字 */
  position: relative;
  z-index: 1;
}

.hr-text::before,
.hr-text::after {
  content: "";
  flex: 1;
  border-bottom: 1px solid #ccc; /* 横线样式 */
}

/* 如果你想要横线有一定的长度限制 */
.hr-text::before,
.hr-text::after {
  max-width: 400px; /* 可以根据需要调整最大宽度 */
}
</style>