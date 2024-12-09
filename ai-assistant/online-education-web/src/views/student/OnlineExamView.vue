<template>
  <div class="my_course">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <div style="text-align: left; margin-bottom: 20px;">
        <h2>
          序号        课程名称            考试名称          总分        最新得分          操作
        </h2>
      </div>
      <el-tab-pane label="选择课程" name="first">
        <div class="course_list">
          <div class="course_item" v-for="(item, index) in itemList" :key="index">
            <div class="item_info">
              <div class="item_index">{{ index + 1 }}</div>
              <div class="item_course">{{ item.courseName }}</div>
              <div class="item_exam">{{ item.examName }}</div>
              <div class="item_score">{{ item.totalScore }}</div>
            </div>
            <!-- 最新得分和操作按钮可以根据实际情况添加 -->
          </div>
        </div>
      </el-tab-pane>
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
    learned: 2,
    total: 8,
  },
  {
    courseName: "数据结构与算法c++版",
    examName: "2021年第八期考试任务",
    totalScore: "80",
    learned: 2,
    total: 8,
  },
  {
    courseName: "微信小程序入门与实践",
    examName: "2021年第八期考试任务",
    totalScore: "100",
    learned: 2,
    total: 8,
  },
  {
    courseName: "Python3.8系统入门+进阶",
    examName: "2021年第八期考试任务",
    totalScore: "60",
    learned: 2,
    total: 8,
  },
  {
    courseName: "vue.js2.5-ui重构饿了么",
    examName: "2021年第八期考试任务",
    totalScore: "50",
    learned: 2,
    total: 8,
  },
  {
    courseName: "微信小程序入门与实践",
    examName: "2021年第八期考试任务",
    totalScore: "60",
    learned: 2,
    total: 8,
  },
  {
    courseName: "数据结构与算法c++版",
    examName: "2021年第八期考试任务",
    totalScore: "70",
    learned: 2,
    total: 8,
  },
  {
    courseName: "Python3.8系统入门+进阶",
    examName: "2021年第八期考试任务",
    totalScore: "70",
    learned: 2,
    total: 8,
  },
  {
    courseName: "vue.js2.5-ui重构饿了么",
    examName: "2021年第八期考试任务",
    totalScore: "80",
    learned: 2,
    total: 8,
  },
])
</script>

<style scoped>
.my_course {
  width: 100%;
}

.demo-tabs {
  width: 100%;
}

.course_list {
  width: 100%;
  display: block; /* 修改为 block 以纵向排列 */
}

.course_item {
  width: 100%;
  margin-bottom: 20px; /* 增加间隔 */
  padding: 10px; /* 增加内边距 */
  border-bottom: 1px solid #ebeef5; /* 增加底部边框 */
}

.item_info {
  display: flex; /* 使用 flex 布局 */
  justify-content: space-between; /* 间隔分布 */
  margin-bottom: 10px; /* 增加间隔 */
}

.item_index, .item_course, .item_exam, .item_score {
  margin-bottom: 5px; /* 增加间隔 */
}

.item_index {
  font-weight: bold;
}

/* 可以根据需要添加其他样式 */
</style>