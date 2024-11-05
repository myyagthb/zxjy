<template>
  <el-form :model="form" label-width="100px" style="width: 500px; margin: 20px auto;">
    <el-form-item label="课程名称">
      <el-input v-model="form.courseName" placeholder="请输入课程名称"></el-input>
    </el-form-item>

    <el-form-item label="课程特色">
      <el-input v-model="form.courseFeature" placeholder="请输入课程特色"></el-input>
    </el-form-item>

    <el-form-item label="课程介绍">
      <el-input v-model="form.courseIntroduction" placeholder="请输入课程介绍"></el-input>
    </el-form-item>

    <el-form-item label="课程面向对象">
      <el-input v-model="form.courseAudience" placeholder="请输入课程面向对象"></el-input>
    </el-form-item>

    <el-form-item label="课程有效期">
      <el-input v-model="form.courseDuration" placeholder="请输入课程有效期"></el-input>
    </el-form-item>

    <el-form-item label="课程售价">
      <el-input v-model="form.coursePrice" placeholder="请输入课程售价"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm">提交</el-button>
      <el-button @click="resetForm">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    // 表单数据
    const form = ref({
      courseName: '',
      courseFeature: '',
      courseIntroduction: '',
      courseAudience: '',
      courseDuration: '',
      coursePrice: '',
    });

    // 提交表单方法
    const submitForm = () => {
      console.log("提交的数据：", form.value);
      // 这里可以加入表单提交逻辑
    };

    // 重置表单方法
    const resetForm = () => {
      form.value = {
        courseName: '',
        courseFeature: '',
        courseIntroduction: '',
        courseAudience: '',
        courseDuration: '',
        coursePrice: '',
      };
    };

    return {
      form,
      submitForm,
      resetForm,
    };
  },
};
</script>

<style scoped>
/* 可以根据需要调整样式 */
</style>
