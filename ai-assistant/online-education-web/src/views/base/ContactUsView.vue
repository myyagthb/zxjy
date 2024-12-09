<template>

  <div class="header">
    <h1>联系我们</h1>
    <p>欢迎访问我们的定价计划页面，我们提供两种全面的选项来满足您的需求：免费和专业。我们相信为我们的服务提供灵活和负担得起的定价选择。无论您是
      希望提高技能的个人，还是寻求专业发展解决方案的企业，我们都有适合您的计划。浏览下面的定价选项，并选撑最符合您要求的定价选项。</p>
  </div>

  <div class="contact-container">
    <el-row gutter="20">
      <el-col :span="24">
        <el-card class="form-card">
          <el-row>
            <!-- 左侧表单区域 -->
            <el-col :span="16">
              <el-form ref="form" :model="form" label-position="top" class="left-aligned-form">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="用户名" class="bold-label" >
                      <el-input v-model="form.username" style="height: 60px;" placeholder="输入您的用户名" ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="姓名" class="bold-label">
                      <el-input v-model="form.name" style="height: 60px;" placeholder="输入您的姓名" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Email" class="bold-label">
                      <el-input v-model="form.email" style="height: 60px;" placeholder="输入您的Email" ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="手机号" class="bold-label">
                      <el-input v-model="form.phone" style="height: 60px;" placeholder="输入您的手机号" ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-form-item label="主题" class="bold-label">
                  <el-input v-model="form.subject" style="height: 60px;" placeholder="输入您的留言主题"></el-input>
                </el-form-item>
                <el-form-item label="留言" class="bold-label">
                  <el-input v-model="form.message" style="height: 150px;" placeholder="在此处输入您的消息..." ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" class="submit-button" @click="submitForm">留下您的消息</el-button>
                </el-form-item>
              </el-form>
            </el-col>

            <!-- 右侧联系信息区域 -->
            <el-col :span="8">
              <div class="contact-info">
                <div class="info-item card">
                  <el-icon><Message style="margin-bottom: 30px" /></el-icon>
                  <span>1234@gmail.com</span>
                </div>
                <div class="info-item card">
                  <el-icon><Phone style="margin-bottom: 30px" /></el-icon>
                  <span>+86 13188888888</span>
                </div>
                <div class="info-item card">
                  <el-icon><Location  style="margin-bottom: 30px"/></el-icon>
                  <span>山东省这里是公司位置</span>
                </div>
                <div class="info-item card social-media">
                  <el-icon><Pointer style="margin-bottom: 30px"/></el-icon>
                  <span>社交媒体</span>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import {Location, Message, Phone, Pointer} from "@element-plus/icons-vue";

export default {
  components: {Pointer, Location, Phone, Message},
  data() {
    return {
      form: {
        username: "",
        name: "",
        email: "",
        phone: "",
        subject: "",
        message: ""
      }
    };
  },
  methods: {
    submitForm() {
      console.log("表单内容：", this.form);
      this.$message.success("消息已发送！");
    }
  }
};
</script>

<style scoped>
.contact-container {
  padding: 20px;
  background-color: #f5f5f5;
}

/* 左对齐表单 */
.left-aligned-form {
  text-align: left;
}

.form-card {
  padding: 40px;
  background-color: #ffffff;
}

.contact-info {
  display: flex;
  flex-direction: column;
  gap: 30px; /* 控制卡片之间的间距 */
  margin-left: 80px; /* 左侧间隔，调整为合适的距离 */
  margin-top: 40px;
}

.info-item.card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 30px;
  border: 1px solid #e0e0e0; /* 卡片边框颜色 */
  border-radius: 8px; /* 圆角 */
  background-color: #f9f9f9; /* 卡片背景颜色 */
}

.info-item.card el-icon {
  font-size: 24px; /* 调整图标大小 */
  margin-bottom: 8px;
  color: #333; /* 图标颜色 */
}

.info-item.card span {
  font-size: 16px;
  color: #666;
}

.info-item.card.social-media {
  display: flex;
  align-items: center;
  justify-content: center;
}

.info-item.card.social-media el-icon {
  margin: 0 8px; /* 控制社交媒体图标之间的间距 */
}


.bold-label {
  font-weight: bold; /* 标签加粗 */
}

.submit-button {
  margin-left: 280px;
  height: 40px;
  width: 30%;
  background-color: #f59a1a;
  border-color: #f59a1a;
  color: #fff;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header h1 {
  font-size: 2em;
  margin-left: 80px;
  margin-top: 100px;
  margin-bottom: 100px;
}

.header p {
  font-size: 1em;
  margin: 0;
  color: #666;
  max-width: 50%;
}
</style>
