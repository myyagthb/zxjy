<template>
  <div class="study_path_plan">
    <div class="study_subject">
      <div class="content_title">
        设定学习科目：
      </div>
      <div class="content_list">
        <div class="subject_item" v-for="(item,index) in subjectItemList" :key="index">{{ item.subjectName }}</div>
      </div>
    </div>
    <div class="ability_access">
      <div class="content_title">
        学习能力评估：
      </div>
      <div class="content_list">
        <div class="subject_item" v-for="(item,index) in abilityItemList" :key="index">{{ item.abilityName }}</div>
      </div>
    </div>
    <div class="study_goal">
      <div class="content_title">
        设定学习科目：
      </div>
      <div class="content_list">
        <div>数学</div>
        <div class="goal_item" v-for="(item,index) in goalItemList" :key="index">{{ item.goalContent }}</div>
      </div>
    </div>
    <div class="progress_div">
      <div class="progress_div_title">计算时长：预计每周最多学习10小时</div>
      <el-progress :percentage="30" width="60%" />
      <div style="width: 100px;text-align: left;">
        本次新增计划10小时
      </div>
      <el-button class="generate_button">一键生成专属计划</el-button>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";

const subjectItemList = ref([{
  subjectId: 1,
  subjectName: "语文"
}, {
  subjectId: 2,
  subjectName: "数学"
}, {
  subjectId: 3,
  subjectName: "英语"
}, {
  subjectId: 4,
  subjectName: "专业课1"
}, {
  subjectId: 5,
  subjectName: "专业课2"
}, {
  subjectId: 6,
  subjectName: "专业课3"
}, {
  subjectId: 7,
  subjectName: "专业课4"
}])

//学习能力评估种类
const abilityItemList = ref([{
  abilityId: 1,
  abilityName: "一般"
}, {
  abilityId: 2,
  abilityName: "良好"
}, {
  abilityId: 3,
  abilityName: "优秀"
}])

const goalItemList = ref([{
    goalId: 1,
    goalContent: "思维拓展--提升学科素养"
},{
  goalId: 2,
  goalContent: "校内专项--全面，系统的学习——类专项，强化提醒和方法"
}])


</script>

<style scoped>
.study_path_plan {
  width: 100%;
  height: auto;
}
.ability_access,.study_subject,.study_goal{
  margin-top: 2%;
}

.study_subject {
  margin-top: 8%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  flex-direction: column;
}

.content_title {
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  text-align: left;
  font-size: 20px;
}

.content_list {
  width: 80%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-wrap: wrap;
}

.subject_item {
  margin-left: 3%;
  width: 120px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid gainsboro;
  border-radius: 5px;
}

.goal_item {
  margin-left: 3%;
  width: 300px;
  height: auto;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  border: 1px solid gainsboro;
  border-radius: 5px;
  padding: 20px;
}

.progress_div,.progress_div_title{
  width: 60%;
  text-align: left;
  margin-top: 2%;
  margin-bottom: 2%;
}
.progress_div_title{
  font-size: 16px;
  font-weight: bold;
}

.generate_button{
  margin-top: 2%;
  color: white;
  background-color: orange;
}
</style>