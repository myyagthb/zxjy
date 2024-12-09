

<template>
<div class="study_progress">
  <div class="study_progress_left">
    <div class="progress_card" v-for="(item,index) in studyCardItemList" :key="index" :style="{backgroundColor:item.backgroundColor}">
      <div class="progress_card_left">
        <div class="card_title">
          {{item.cardTitle}}
        </div>
        <div class="card_frequency">
          学习频次：{{item.frequency}}
        </div>
        <div class="card_period">
          学习安排：{{item.cardPeriod}}
        </div>
        <div class="card_date">
          学习日：{{item.cardDate}}
        </div>
      </div>
<!--      <div class="progress_card_right" :style="{color: item.color}">-->
<!--        {{item.percent}} %-->
<!--      </div>-->
    </div>
  </div>
  <div class="study_progress_right">
    <el-calendar>
      <template #date-cell="{ data }">
        <p :class="data.isSelected ? 'is-selected' : ''">
          {{ data.day.split('-').slice(1).join('-') }}
          {{ data.isSelected ? '✔️' : '' }}<br>
          <span class="calendar_span" v-if="events[data.day]">{{ events[data.day] }}</span> <!-- 特定日期的任务或活动 -->
        </p>
      </template>
    </el-calendar>
  </div>
</div>
</template>

<script setup>

import {ref} from "vue";

const studyCardItemList = ref([{
  cardTitle: "高等数学秋季计划",                      //标题
  cardPeriod: "2024年11月4日-2025年1月1日",           //学习时间段
  frequency: "每周两次任务，每次约60分钟，共24个任务",    //学习频次
  cardDate: "周一,周五",                               //学习时间
  percent: 15,                                       //占比
  backgroundColor: "#fcf3e7",                           //卡片背景色
  color: "orange"                           //字体颜色
},{
  cardTitle: "高等物理秋季计划",                      //标题
  cardPeriod: "2024年11月4日-2025年1月1日",           //学习时间段
  frequency: "每周两次任务，每次约60分钟，共24个任务",    //学习频次
  cardDate: "周一,周五",                               //学习时间
  percent: 15,                                       //占比
  backgroundColor: "#eef4fe",                           //卡片背景色
  color: "blue"
}])


const events = ref({
  '2024-11-06': ' ',
  '2024-11-10': ' ',
// 可以继续添加其他日期的事件
});

</script>

<style scoped>
.study_progress{
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.study_progress_left{
  width: 30%;
  height: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
  flex-wrap: wrap;
}
.study_progress_left>.progress_card{
  margin-top: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80%;
  height: 120px;
}

.progress_card_left{
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: column;
}
.progress_card_left>.card_title,.card_period,.card_date{
  width: 100%;
  text-align: left;
}
.progress_card_left>.card_title{
  font-size: 18px;
  font-weight: bold;
}

.progress_card_left>.card_period,.card_date,.card_frequency{
  text-align: left;
  width: 100%;
  font-size: 14px;
  color: #8c939d;
}
/*
.progress_card_right{
  width: 30%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 20px;
}

 */



.study_progress_right{
  width: 70%;
  height: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
}
.calendar_span{
  width: 60%;
  height: 20px;
  min-width: 30px;
  background-color: #fcf3e7;
  border-radius: 5px;
  text-align: left;
  color: orange;
  border: 1px solid gold;
  display: inline-block;
  overflow-x: hidden;
  overflow-y: hidden;
  white-space: nowrap; /* 防止文字换行 */
  text-overflow: ellipsis; /* 超出部分用省略号表示 */
}


/*日历*/
.is-selected {
  color: #1989fa;
}
</style>