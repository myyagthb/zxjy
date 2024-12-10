<template>
  <div class="upload-video" v-loading="isLoading">
    <el-form ref="form" :model="courseVideoForm" label-width="100px" style="width: 50%;">
      <el-form-item label="所属课程">
        <el-select v-model="courseVideoForm.courseId" placeholder="请选择课程分类" @click="getCourseList" style="width: 70%;" size="large">
            <el-option v-for="(item,index) in courseList" :key="index" :value="item.courseId" :label="item.courseName"></el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="所属目录">
        <el-input v-model="courseVideoForm.BelongPassage" size="large" rows="4"></el-input>
      </el-form-item>
      <el-form-item label="视频名称">
        <el-input v-model="courseVideoForm.videoName" size="large"></el-input>
      </el-form-item>
      <el-form-item label="上传视频">
        <el-upload
            style="width: 100%;"
          class="upload-demo"
          action=""
            :on-change="handleChange"
          :on-success="handleCourseVideoSuccess"
          :before-upload="beforeCourseVideoUpload"
          :on-error="handleCourseVideoError"
          :file-list="videoFileList"
          :limit="1"
          accept="video/*"
          drag
            :auto-upload="false"
        >
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            将文件拖到此处，或 <em>点击上传文件</em>
          </div>
          <div class="el-upload__tip">只能上传视频文件，且不超过50MB</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitCourseVideoForm">添 加</el-button>
        <el-button @click="cancelCourseVideo">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
//视频上传部分================================
import {ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";
import store from "@/store";
import { v4 as uuidv4 } from 'uuid';
import storageManager from '@/assets/js/utils'; // 替换为实际路径


const courseVideoForm = ref({
  courseId: '',
  userId: '',
  videoName: '',
  BelongPassage: '',
  videoFile: null,
  videoUrl: ''
});
//视频文件列表
const videoFileList = ref([]);

const handleCourseVideoSuccess = (response, file, videoFileList) => {
  ElMessage.success('视频上传成功');
  videoFileList.value = videoFileList;
};

const handleCourseVideoError = (err, file, fileList) => {
  console.log(err)
  console.log(file)
  console.log(fileList)
  ElMessage.error('视频上传失败');
};

const beforeCourseVideoUpload = (file) => {
  const isVideo = file.type.startsWith('video/');
  const isLt500M = file.size / 1024 / 1024 < 500;
  if (!isVideo) {
    ElMessage.error('只能上传视频文件');
  }
  if (!isLt500M) {
    ElMessage.error('视频大小不能超过500MB');
  }
  return isVideo && isLt500M;
};

// 处理文件选择变化
const handleChange = (file, fileList) => {
  courseVideoForm.value.videoFile = file.raw;
  videoFileList.value = fileList;
}

//表示正在上传
const isLoading = ref(false)

const submitCourseVideoForm = async () => {
  if (courseVideoForm.value.videoName) {
    isLoading.value = true

    const formData = new FormData();
    formData.append('courseId', courseVideoForm.value.courseId);
    formData.append('userId', store.state.user.userId);
    formData.append('videoName', courseVideoForm.value.videoName);
    formData.append('BelongPassage', courseVideoForm.value.BelongPassage);

    let fileForm = new FormData();
    fileForm.append('file', courseVideoForm.value.videoFile);
    const uuid = uuidv4();
    console.log(uuid)
    let fileKey = uuid + ".mp4"

    //把这个key传入数据库，方便以后使用来查找对应的视频文件
    formData.append('videoUrl', fileKey);

    //上传文件到storage-api
    // axios.post("/storage/object/test/"+fileKey, fileForm, {
    //   headers:{
    //     // 'Access-Control-Allow-Origin':'http://localhost:443',
    //     'Access-Control-Allow-Methods':'GET, POST, PUT, DELETE, OPTIONS',
    //     'Access-Control-Allow-Headers':'Content-Type, Authorization',
    //     'Content-Type': 'multipart/form-data',
    //     'x-forwarded-host':'demo',
    //     'authorization':'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-RRdA8gxmv5hhTLu8OpHbRGIX4P7VAu3eXPOUyDJZDE',
    //     'crossDomain' : true
    //   }
    // }).then(res  =>{
    //   console.log(res)
    // })

    //===========上传文件到supabase================

    //获取配置文件中bucket名称,后期从配置文件中获取
    let videoBucket = 'video'
    // 使用 StorageManager 上传文件
    const result = await storageManager.uploadFile(videoBucket, courseVideoForm.value.videoFile, fileKey);
    console.log(result)
    if (!result) {
      console.error('Error uploading file');
      ElMessage.error('视频上传失败');
      return;
    }

    // // // 获取文件的公共URL
    // const publicURL = await storageManager.getPublicUrl('video', fileKey);
    // console.log(publicURL)
    // if (!publicURL) {
    //   console.error('Error getting public URL');
    //   ElMessage.error('获取视频URL失败');
    //   return;
    // }


    //=============================

    console.log(courseVideoForm.value.videoFile)
    try {
      //将文件信息传到后端进行保存
      const response = await axios.post('/backend/course-video/addCourseVideo', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          timeout: 300000  // 增加请求超时时间
        }
      });
      console.log(response)
      isLoading.value = false
      ElMessage.success('视频添加成功');
      //重置表单数据
      resetVideoForm()
    } catch (error) {
      ElMessage.error('视频添加失败');
    }
  } else {
    ElMessage.error('请填写完整信息并选择视频文件');
  }
};

const cancelCourseVideo = () => {
  //重置表单数据
  resetVideoForm()
};

const resetVideoForm = ()=>{
  courseVideoForm.value.courseId = '';
  courseVideoForm.value.userId = '';
  courseVideoForm.value.videoName = '';
  courseVideoForm.value.BelongPassage = '';
  courseVideoForm.value.videoFile = null;
  videoFileList.value = [];
}







//根据userId获取课程列表==============================
const courseList = ref([]);
const getCourseList = () => {
  axios.get("/api/backend/course/getCourseListByUserId",{
    params:{
      userId: store.state.user.userId
    }
  }).then(res => {
    let data = res.data
    if(data.code === 200){
      //填充从后台获取的数据
      courseList.value = data.content
    }
  }).catch(err => {
    ElMessage({
      type: "error",
      message: err.message
    })
  })
}

</script>

<style scoped>
.upload-video{
  width: 100%;
  height: auto;
}
</style>
