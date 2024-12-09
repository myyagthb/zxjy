<template>
在线考试
  <div class="my_course">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <div class="header" >
          <h2 class="title">序号</h2>
          <h2 class="lesson_name">课程名称</h2>
          <h2 class="exam_name">考试名称</h2>
          <h2 class="total_score">总分</h2>
          <h2 class="new_score">最新得分</h2>
          <h2 class="action">操作</h2>
      </div>
      <div class="course_list">
          <div class="course_item" v-for="(item, index) in itemList" :key="index">
            <div class="item_info">
              <div class="item_index">{{ index + 1 }}</div>
              <div class="item_course">{{ item.courseName }}</div>
              <div class="item_exam">{{ item.examName }}</div>
              <div class="item_score">{{ item.totalScore }}</div>
              <div class="item_newscore">{{ item.newScore }}</div>
              <div class="item_actions">
                <div class="item_action">{{ item.action }}</div>
                <div class="item_result">{{ item.result }}</div>
              </div>
            </div>
            <!-- 最新得分和操作按钮可以根据实际情况添加 -->
          </div>
        </div>
      <!-- 其他 tab-pane 省略，与 first 相同 -->
    </el-tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
const activeName = ref('first')

const handleClick = (tab, event) => {
  console.log(tab, event)
}

// 调整数据结构，分离课程名称、考试名称和总分
const itemList = ref([
  {
    courseName: "微信小程序入门与实践",
    examName: "2021年第八期考试任务",
    totalScore: "100",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "数据结构与算法c++版",
    examName: "2021年第八期考试任务",
    totalScore: "80",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "微信小程序入门与实践",
    examName: "2021年第八期考试任务",
    totalScore: "100",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "Python3.8系统入门+进阶",
    examName: "2021年第八期考试任务",
    totalScore: "60",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "vue.js2.5-ui重构饿了么",
    examName: "2021年第八期考试任务",
    totalScore: "50",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "微信小程序入门与实践",
    examName: "2021年第八期考试任务",
    totalScore: "60",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "数据结构与算法c++版",
    examName: "2021年第八期考试任务",
    totalScore: "70",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "Python3.8系统入门+进阶",
    examName: "2021年第八期考试任务",
    totalScore: "70",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
  {
    courseName: "vue.js2.5-ui重构饿了么",
    examName: "2021年第八期考试任务",
    totalScore: "80",
    newScore: 0,
    action: "开始考试",
    result: "查看结果",
  },
])
</script>

<style scoped>
html, body, #app {
  height: 100%;
  margin: 0;
  padding: 0;
}

.header {
  display: flex; /* 使用Flexbox布局 */
  align-items: center; /* 垂直居中 */
  padding: 20px;
  margin-left: 1%;
  margin-bottom: 5px; /* 根据需要调整底部外边距 */
}

.title {
  margin: 0; /* 移除h2的默认外边距 */
  margin-left: 5%;
  white-space: nowrap; /* 防止内容换行 */
}

.lesson_name {
  margin-left: 13%;
  white-space: nowrap; /* 防止内容换行 */
}

.exam_name {
  margin-left: 10%;
  white-space: nowrap; /* 防止内容换行 */
}

.total_score {
  margin-left: 12%;
  white-space: nowrap; /* 防止内容换行 */
}
.new_score {
  margin-left: 13%;
  white-space: nowrap; /* 防止内容换行 */
}

.action {
  margin-left: 10%;
  white-space: nowrap; /* 防止内容换行 */
}




.my_course {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.demo-tabs {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 20px;
  text-align: left;
}

.course_list {
  flex: 1;
  overflow-y: auto; /* 添加滚动条 */
  padding: 10px;
}

.course_item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
}

.item_info {
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
  gap: 10px;
  width: 100%;
}

.item_actions {
  display: flex;
  gap: 5px;
  align-self: center;
}

.item_index, .item_course, .item_exam, .item_score, .item_newscore {
  margin-right: 20px;
  flex: 1;
}

.item_index {
  font-weight: bold;
}

/* 可以根据需要添加其他样式 */
</style>