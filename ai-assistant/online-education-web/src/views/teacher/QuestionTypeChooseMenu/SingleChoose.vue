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
    <div class="question-title">
      <span style="color:red;">*&nbsp;</span>
      <span >题目</span>
    </div>
    <div class="question-editor">
      <component :is="TestEdit" v-if="false"></component>
      富文本编辑器
    </div>
  </div>
<!--选项*********************************************************************************************************-->
  <div class="options">
    <div class="rowone">
      <div class="A">
        <div  class="optionsA">
          <span style="color:red;">*&nbsp;</span>
          <span >选项A</span>
        </div>
        <div class="question-editorA">
          <component :is="TestEdit" v-if="false"></component>
          富文本编辑器
        </div>
      </div>
      <div class="B">
        <div  class="optionsB">
          <span style="color:red;">*&nbsp;</span>
          <span >选项B</span>
        </div>
        <div class="question-editorB">
          <component :is="TestEdit" v-if="false"></component>
          富文本编辑器
        </div>
      </div>
    </div>
    <div class="rowtwo">
      <div class="C">
        <div  class="optionsC">
          <span style="color:red;">*&nbsp;</span>
          <span >选项C</span>
        </div>
        <div class="question-editorC">
          <component :is="TestEdit" v-if="false"></component>
          富文本编辑器
        </div>
      </div>
      <div class="D">
        <div  class="optionsD">
          <span style="color:red;">*&nbsp;</span>
          <span >选项D</span>
        </div>
        <div class="question-editorD">
          <component :is="TestEdit" v-if="false"></component>
          富文本编辑器
        </div>
      </div>
    </div>
  </div>
<!--解析*********************************************************************************************************-->
  <div class="analysis">
    <div class="analysis-title">
      <span style="color:red;">*&nbsp;</span>
      <span >解析</span>
    </div>
    <div class="analysis-editor">
      <component :is="TestEdit" v-if="false"></component>
      富文本编辑器
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import TestEdit from './TestEditor.vue';
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
/* 题目 ***********************************************************************************************************/
.question {
  display: flex;
  margin: 20px 0  20px 0;
}
.question-title{
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.question-editor {
  width: 1400px;
  height: 200px;
  background: #F5F5F5;
}
/* 选项 ***********************************************************************************************************/
.options {
  display: flex;
  flex-direction: column;
  margin: 20px 0  20px 0;
}
.rowone {
  display: flex;
  margin: 0 0 20px 0;
}
.rowtwo {
  display: flex;
}
.A {
  display: flex;
  margin: 0 20px 0 0;
}
.B {
  display: flex;
}
.optionsA {
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.optionsB {
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.question-editorA {
  width: 650px;
  height: 120px;
  background: #F5F5F5;
}
.question-editorB {
  width: 650px;
  height: 120px;
  background: #F5F5F5;
}
/*CD*************/

.C {
  display: flex;
  margin: 0 20px 0 0;
}
.D {
  display: flex;
}
.optionsC {
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.optionsD {
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.question-editorC {
  width: 650px;
  height: 120px;
  background: #F5F5F5;
}
.question-editorD {
  width: 650px;
  height: 120px;
  background: #F5F5F5;
}
/* 解析 ***********************************************************************************************************/
.analysis {
  display: flex;
  margin: 20px 0  20px 0;
}
.analysis-title{
  display: flex;
  justify-content: flex-end; /* 右对齐 */
  width: 60px;
  margin-right: 20px;
}
.analysis-editor {
  width: 1400px;
  height: 200px;
  background: #F5F5F5;
}
</style>