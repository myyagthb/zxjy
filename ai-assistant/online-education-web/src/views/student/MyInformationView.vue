<template>
  <div class="info">
    <el-form
        label-position="right"
        label-width="auto"
        :model="userInfoForm"
        style="max-width: 600px"
    >
      <el-form-item label="用户身份：">
        <el-input v-model="userInfoForm.status" size="large" disabled/>
      </el-form-item>
      <el-form-item label="教育阶段：">
        <el-select
            v-model="userInfoForm.educateStage"
            placeholder="请选择教育阶段"
            size="large"
            style="width: 100%"
        >
          <el-option
              v-for="(item,index) in educateStageList" :key="index"
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
        <el-input v-model="userInfoForm.realName" size="large" placeholder="请输入姓名"/>
      </el-form-item>

      <el-form-item label="证件类型：">
        <el-select
            v-model="userInfoForm.licenseType"
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
        <el-button class="sava_button" @click="saveUserInfo">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {ElForm, ElFormItem, ElInput, ElMessage} from 'element-plus';
import store from "@/store";
import {isEmptyObject} from "../../../public/js/utils";
import axios from "axios";


const userInfoForm = ref({
  userId: undefined,        //学生ID
  status: '',               //学生身份
  educateStage: '',         //教育阶段
  nickname: '',             //昵称
  realName: '',                 //真实姓名
  licenseType: '',          //证件类型
  licenseNumber: '',        //证件号码
  school: ''                //学校
});

onMounted(()=>{
  getUserInfoById()
})


//获取用户信息
const getUserInfoById = ()=> {
  //获取缓存中的用户
  let user = store.state.user
  console.log(user)
  if(!isEmptyObject(user)){
    axios.get("/user/getUserInfoById",{
      params:{
        userId : user.userId
      }
    }).then(res => {
      let data = res.data
      if(data.code === 200){
        ElMessage({
          type: "success",
          message: data.msg
        })
        //更新用户信息表
        userInfoForm.value = data.content
        //根据角色状态码，转化成对应的显示标签
        if(data.content.role === 0){
          userInfoForm.value.status = "学生"
        } else {
          userInfoForm.value.status = "教师"
        }
      } else {
        ElMessage({
          type: "error",
          message: data.msg
        })
      }
    })

  } else {
    ElMessage({
      type: "error",
      message: "用户信息错误，请重新登录"
    })
  }

}

//保存用户信息
const saveUserInfo = ()=>{
  //获取缓存中的用户
  let user = store.state.user
  if(!isEmptyObject(user)){
    userInfoForm.value.userId = user.userId
  } else {
    ElMessage({
      type: "error",
      message: "用户信息错误，请重新登录"
    })
    return;
  }

  //将用户信息传递给后端，保存并更新用户信息
  axios.post("/user/saveUserInfo",userInfoForm.value).then(res => {
    let data = res.data
    if(data.code === 200){
      ElMessage({
        type: "success",
        message: data.msg
      })
      //更新成功后调用查询用户信息接口
      getUserInfoById()
    } else {
      ElMessage({
        type: "error",
        message: data.msg
      })
    }
  })
}





//教育类型
const educateStageList = ref([{
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