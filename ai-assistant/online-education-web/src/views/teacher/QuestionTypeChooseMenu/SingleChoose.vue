<!-- SingleChoice.vue -->
<template>
  <div class="realAnswer">
    <div class="radio-group-container">
      <span style="color:red;">*&nbsp;</span>
      <span >正确答案&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <el-radio-group v-model="realAnswer" size="large">
        <el-radio value="1">A</el-radio>
        <el-radio value="2">B</el-radio>
        <el-radio value="3">C</el-radio>
        <el-radio value="4">D</el-radio>
      </el-radio-group>
    </div>
    <div class="action">
      <span style="color:red;">*&nbsp;</span>
      <span >操作&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <span style="color: #D09039;">添加选项</span>
    </div>
  </div>
<!--题目*********************************************************************************************************-->
  <div class="question">
    <div>
      <span style="color:red;">*&nbsp;</span>
      <span >题目</span>
    </div>

  </div>
<!--选项*********************************************************************************************************-->
<!--解析*********************************************************************************************************-->
</template>

<script setup>
import { ref } from 'vue'

const realAnswer = ref()
</script>

<style scoped>
/* 答案 ***********************************************************************************************************/
.realAnswer {
  display: flex;
  gap: 300px;
}
.radio-group-container {
 display: flex;
 align-items: center; /* 垂直居中对齐 */
}
.action{
 display: flex;
 align-items: center; /* 垂直居中对齐 */
}
</style>