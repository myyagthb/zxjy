<template>
  <div class="info">
    <el-form
        label-position="right"
        label-width="auto"
        :model="userInfoForm"
        style="max-width: 600px"
    >
      <el-form-item label="用户身份：">
        <el-input v-model="userInfoForm.status" size="large"/>
      </el-form-item>
      <el-form-item label="教育阶段：">
        <el-select
            v-model="userInfoForm.eduStage"
            placeholder="请选择教育阶段"
            size="large"
            style="width: 100%"
        >
          <el-option
              v-for="(item,index) in eduStageList" :key="index"
              :value="item.value"
              :label="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="昵称：">
        <el-input v-model="userInfoForm.nickname" size="large" placeholder="请输入昵称"/>
      </el-form-item>
      <el-form-item label="真实姓名：">
        <el-input v-model="userInfoForm.name" size="large" placeholder="请输入姓名"/>
      </el-form-item>

      <el-form-item label="证件类型：">
        <el-select
            v-model="userInfoForm.eduStage"
            placeholder="请选择证件类型"
            size="large"
            style="width: 100%"
        >
          <el-option
              v-for="(item,index) in licenseTypeList" :key="index"
              :value="item.value"
              :label="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="证件号码：">
        <el-input v-model="userInfoForm.licenseNumber" placeholder="请输入证件号" size="large"/>
      </el-form-item>

      <el-form-item label="学校：">
        <el-input v-model="userInfoForm.school" size="large"/>
      </el-form-item>
      <el-form-item >
        <el-button class="sava_button">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {ElForm, ElFormItem, ElInput} from 'element-plus';


const userInfoForm = ref({
  status: '',               //学生身份
  eduStage: '',             //教育阶段
  nickname: '',             //昵称
  name: '',                 //真实姓名
  licenseType: '',          //证件类型
  licenseNumber: '',        //证件号码
  school: ''                //学校
});

const eduStageList = ref([{
  key: 1,
  value: "基础教育"
},{
  key: 2,
  value: "高等教育"
},{
  key: 3,
  value: "职业教育"
}])

const licenseTypeList = ref([
  {
    key: 1,
    value: "身份证"
  }
])
</script>

<style scoped>
.info{
  margin-top: 5%;
}

.sava_button{
  width: 20%;
  height: 40px;
  color: white;
  background-color: orange;
}
</style>