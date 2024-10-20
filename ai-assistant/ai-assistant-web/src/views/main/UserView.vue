<template>
  <el-container>
    <el-dialog
        v-model="userInfoDialogVisible"
        title="绑定个人资料"
        width="500"
        :before-close="handleUserInfoQrCodeDialogClose"
    >
      <div>
        <img :src="userInfoQrCode" width="200" height="200" alt="信息码"/>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleUserInfoQrCodeDialogClose">关闭</el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
        v-model="dialogVisible"
        title="修改个人资料"
        width="500"
        :before-close="handleClose"
    >
      <el-form :model="userForm" label-width="auto" style="max-width: 600px">
        <el-row>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="头像">
                <el-avatar :src="userForm.avatar"></el-avatar>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="用户名">
                <el-input v-model="userForm.username" disabled/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="电话">
                <el-input v-model="userForm.mobile" disabled/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="国家">
                <el-input v-model="userForm.country"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="省">
                <el-input v-model="userForm.province"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="24">
            <div class="grid-content ep-bg-purple-dark">
              <el-form-item label="市">
                <el-input v-model="userForm.city"/>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleClose">取消</el-button>
          <el-button type="primary" @click="updateUserInfo">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
    <div class="personal">
      <el-card style="max-width: 90%;min-width: 1000px;">
        <template #header>
          <div class="card-header">
            <span>个人信息</span>
          </div>
        </template>
        <el-descriptions
            class="margin-top"
            title="个人详情"
            :column="1"
            :size="size"
            border
        >
          <template #extra>
            <el-button type="info" @click="showUserInfoQrCodeDialog">扫码绑定信息</el-button>
            <el-button type="primary" @click="showDialog">修改</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon>
                  <Avatar/>
                </el-icon>
                头像
              </div>
            </template>
            <el-avatar
                :src="userInfo.avatar"
            />
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <user/>
                </el-icon>
                用户名
              </div>
            </template>
            {{ userInfo.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone/>
                </el-icon>
                手机号
              </div>
            </template>
            {{ userInfo.mobile }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <location/>
                </el-icon>
                性别
              </div>
            </template>
            {{userInfo.gender}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <tickets/>
                </el-icon>
                国家
              </div>
            </template>
            <el-tag size="small">{{userInfo.country}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building/>
                </el-icon>
                省
              </div>
            </template>
            {{userInfo.province}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building/>
                </el-icon>
                市
              </div>
            </template>
            {{userInfo.city}}
          </el-descriptions-item>
        </el-descriptions>
        <template #footer>
          尾部
        </template>
      </el-card>
    </div>
  </el-container>

</template>

<script setup>
import {onMounted, ref} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";



const dialogVisible = ref(false)
const userInfoDialogVisible = ref(false)

const userInfo = ref({
  openid: "",   //微信号
  avatar: "",  //头像
  username: "", //用户名
  gender: "",
  country: "",//国家
  province: "",//省份
  city: "",//城市
  mobile: ""  //电话
})


const userForm = ref({
  id: "",
  openid: "",   //微信号
  avatar: "",  //头像
  username: "", //用户名
  gender: "",
  country: "",//国家
  province: "",//省份
  city: "",//城市
  mobile: ""  //电话
})


onMounted(()=>{
  queryUserByUserId()
})


const userInfoQrCode = ref("")

const showUserInfoQrCodeDialog = ()=>{
  axios.get("/qrUserInfoCode").then(res => {
    console.log(res)
    //渲染微信二维码供用户去扫描
    userInfoQrCode.value = new URL(res.data, import.meta.url).href;
    userInfoDialogVisible.value = true
  })
}

const showDialog = () => {
  queryUserByUserId()
  dialogVisible.value = true
}

const handleClose = () => {
  ElMessageBox.confirm('确定要取消修改吗?')
      .then(() => {
        dialogVisible.value = false
      })
      .catch(() => {
        // catch error
        userForm.value = {}
        dialogVisible.value = true
      })
}

const handleUserInfoQrCodeDialogClose = () => {
  ElMessageBox.confirm('确定要关闭该吗?')
      .then(() => {
        userInfoDialogVisible.value = false
        //调用查询信息方法，更新用户信息数据
        queryUserByUserId()
      })
      .catch(() => {
        // catch error
        userInfoDialogVisible.value = true
      })
}

const queryUserByUserId = () => {

  let userId = sessionStorage.getItem("userId") || 'a'

  axios.get("/getWxUserInfoById",{
    params:{
      userId: userId
    }
  }).then(res => {

    console.log(res)

    let data = res.data
    if (data.code === 200) {
      if(data.content != null){
        //给userInfo赋值
        userInfo.value.avatar = data.content.avatarUrl || ""
        userInfo.value.username = data.content.nickname
        userInfo.value.gender = data.content.gender === 0 ? "男" : "女"
        userInfo.value.mobile = data.content.mobile
        userInfo.value.country = data.content.country
        userInfo.value.province = data.content.province
        userInfo.value.city = data.content.city

        //给userForm赋值
        userForm.value.avatar = data.content.avatarUrl || ""
        userForm.value.username = data.content.nickname
        userForm.value.gender = data.content.gender === 0 ? "男" : "女"
        userForm.value.mobile = data.content.mobile
        userForm.value.country = data.content.country
        userForm.value.province = data.content.province
        userForm.value.city = data.content.city
        userForm.value.id = data.content.id
      } else {
        //用户身份过期，查询不到该用户信息，要求用户重新登录
        ElMessage({
          type: "warning",
          message: "用户信息错误或未绑定信息，请扫码绑定"
        })
        //router.push("/login")
      }
      console.log(userInfo.value)
    } else {
      ElMessageBox({
        type: "error",
        message: data.message
      })
    }
  })
}

const updateUserInfo = () => {
  axios.post("/updateWxUserInfo",userForm.value).then(res => {
    let data = res.data
    if (data.code === 200) {
      ElMessage({
        type: "success",
        message: data.message
      })
      userForm.value = {}
      dialogVisible.value = false
      queryUserByUserId()
    } else {
      ElMessage({
        type: "error",
        message: data.message
      })
    }
  })
}



</script>
<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
}
.avatar {
  width: 50px;
  height: 50px;
  display: block;
}
</style>