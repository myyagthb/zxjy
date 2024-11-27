<template>
  <div class="upload_course">
    <el-dialog
        v-model="categoryDialogVisible"
        title="添加分类"
        width="500"
        :before-close="handleCategoryClose"
    >
      <el-form>
        <el-form-item label="父级类">
          <el-select v-model="categoryForm.parentId" placeholder="请选择父级分类" @click="getCategoryList"  size="large">
            <el-option v-for="(item,index) in categoryList" :key="index" :value="item.categoryId" :label="item.categoryName"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="子级类">
          <el-input v-model="categoryForm.categoryName"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="addCategory" :loading="categoryIsLoading">
            添加
          </el-button>
          <el-button @click="handleCategoryClose" >取消</el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
        v-model="tagDialogVisible"
        title="添加标签"
        width="500"
        :before-close="handleTagClose"
    >
      <el-form>
        <el-form-item label="新标签">
          <el-input v-model="tagForm.tagName" placeholder="请输入新标签" @click="getTagList"  size="large">
          </el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="addTag" :loading="tagIsLoading">
            添加
          </el-button>
          <el-button @click="handleTagClose" >取消</el-button>
        </div>
      </template>
    </el-dialog>


    <el-form :model="courseForm" label-width="100px" style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
      <div class="upload_course_left">
        <div class="form_title">&emsp;课程信息</div>
        <el-form-item label="课程名称">
          <el-input v-model="courseForm.courseName" placeholder="请输入课程名称" size="large"></el-input>
        </el-form-item>
        <el-form-item label="课程分类">
          <el-select v-model="courseForm.courseCategory" placeholder="请选择课程分类" @click="getCategoryList" style="width: 70%;" size="large">
            <el-option v-for="(item,index) in categoryList" :key="index" :value="item.categoryName"></el-option>
          </el-select>
          <a class="form_a" @click="handleCategoryShow">添加分类</a>
        </el-form-item>
        <el-form-item label="课程标签">
          <el-select multiple v-model="courseForm.chooseTagList" placeholder="请选择课程标签" @click="getTagList" style="width: 70%;" size="large">
            <el-option v-for="(item,index) in tagList" :key="index" :value="item.tagName"></el-option>
          </el-select>
          <a class="form_a" @click="handleTagShow">添加标签</a>
        </el-form-item>
        <el-form-item label="课程讲师">
          <el-input v-model="courseForm.courseTeacher" placeholder="请输入课程特色" size="large" style="width: 70%;"></el-input>
        </el-form-item>
        <el-form-item label="公开情况">
          <el-radio-group v-model="courseForm.publicState">
            <el-radio value="全部可见" size="large">全部可见</el-radio>
            <el-radio value="部分可见" size="large">部分可见</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="课程简介">
          <el-input v-model="courseForm.courseBlurb" type="textarea" rows="4" placeholder="请输入课程面向对象" size="large"></el-input>
        </el-form-item>
        <div class="form_title">&emsp;课程信息</div>
        <el-form-item label="课程附件">
          <el-upload
              action=""
              :on-change="handleChange"
              :on-success="handleUploadSuccess"
              :on-error="handleUploadError"
              :before-upload="beforeUpload"
              :file-list="courseForm.fileList"
              multiple
              accept="image/*"
              :limit="1"
              :on-exceed="handleExceed"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :auto-upload="false"
          >
            <el-button size="large" type="primary">点击上传封面</el-button>
          </el-upload>
          <el-dialog v-model="dialogVisible">
            <img w-full :src="dialogImageUrl" alt="预览图片" />
          </el-dialog>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm" style="width: 20%;height: 40px;background-color: orange;">提交</el-button>
          <el-button @click="resetForm"  style="width: 20%;height: 40px;background-color: gray;color: white;">取消</el-button>
        </el-form-item>
      </div>
      <div class="upload_course_right">
        课程封面：<img  :src="previewImageUrl" style="width: 320px;height: 300px;"/>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import axios from "axios";
import {ElMessage} from "element-plus";
import store from "@/store";
const courseForm = ref({
  courseName: '',
  courseCategory: '',
  chooseTagList: [],
  courseTeacher: '',
  publicState: '',
  courseBlurb: '',
  fileList: [],
  userId: ''
});

const dialogImageUrl = ref('');
const dialogVisible = ref(false);
const previewImageUrl = ref(require('@/assets/images/teacher/course-default-img.jpg'));

// 处理文件选择变化
const handleChange = (file, fileList) => {
  if (file.raw) {
    const reader = new FileReader();
    reader.onload = (e) => {
      previewImageUrl.value = e.target.result;
    };
    reader.readAsDataURL(file.raw);
  }
  courseForm.value.fileList = fileList;
};

// 处理文件上传成功
const handleUploadSuccess = (response, file, fileList) => {
  console.log('文件上传成功:', response, file, fileList);
  // 可以在这里处理文件上传成功的逻辑，例如更新表单中的文件列表
  courseForm.value.fileList = fileList;
};

// 处理文件上传失败
const handleUploadError = (error, file, fileList) => {
  console.error('文件上传失败:', error, file, fileList);
  ElMessage.error('文件上传失败，请稍后再试。');
};

// 文件上传前的处理
const beforeUpload = (file) => {
  const isImage = file.type.startsWith('image/');
  if (!isImage) {
    ElMessage.error('只能上传图片文件');
    return false;
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    ElMessage.error('文件大小不能超过 2MB');
    return false;
  }
  return true;
};

// 处理文件超出限制
const handleExceed = (files, fileList) => {
  ElMessage.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
};

// 处理图片预览
const handlePictureCardPreview = (file) => {
  dialogImageUrl.value = file.url || file.response.data.url;
  dialogVisible.value = true;
};

// 处理文件移除
const handleRemove = (file, fileList) => {
  console.log('文件移除:', file, fileList);
  courseForm.value.fileList = fileList;
  previewImageUrl.value = require('@/assets/images/teacher/course-default-img.jpg');
};

// 提交表单方法
const submitForm = async () => {
  console.log("提交的数据：", courseForm.value);
  if(courseForm.value.courseName.trim() === ''){
    ElMessage({
      type: "error",
      message: "请填写课程名称"
    })
    return
  }

  const formData = new FormData();
  formData.append('courseName', courseForm.value.courseName);
  formData.append('courseCategory', courseForm.value.courseCategory);
  formData.append('chooseTagList', courseForm.value.chooseTagList);
  formData.append('courseTeacher', courseForm.value.courseTeacher);
  formData.append('publicState', courseForm.value.publicState);
  formData.append('courseBlurb', courseForm.value.courseBlurb);
  formData.append('userId', store.state.user.userId)
  if (courseForm.value.fileList.length > 0) {
    formData.append('courseCovers', courseForm.value.fileList[0].raw);
  }

  console.log(formData)

  try {
    await axios.post('/backend/course/uploadCourse', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }).then(res => {
      console.log(res)
      let data = res.data
      if(data.code === 200){
        ElMessage.success(data.msg);
        resetForm();
      } else {
        ElMessage.error(data.msg)
      }
    });

  } catch (error) {
    ElMessage.error('课程创建失败，请重试');
  }

  // axios.post("/course/uploadCourse",courseForm.value).then(res =>{
  //   console.log(res)
  //   let data = res.data
  //   if(data.code === 200){
  //     ElMessage({
  //       type: "success",
  //       message: data.msg
  //     })
  //   } else {
  //     ElMessage({
  //       type: "error",
  //       message: data.msg
  //     })
  //   }
  // })



};

// 重置表单方法
const resetForm = () => {
  courseForm.value = {
    courseName: '',
    courseCategory: '',
    chooseTagList: [],
    courseTeacher: '',
    publicState: '',
    courseBlurb: '',
    courseDuration: '',
    fileList: []
  };
  previewImageUrl.value = require('@/assets/images/teacher/course-default-img.jpg');
  dialogImageUrl.value = '';
  dialogVisible.value = false;
};

//标签区域变量
const tagDialogVisible = ref(false)

//标签List
const tagList = ref([])
const getTagList = () => {
  let cacheTagList = store.state.tag_list
  if(cacheTagList.length > 0){
    tagList.value = cacheTagList
    return
  }

  axios.get("/backend/tag/queryTagList").then(res => {
    let data = res.data
    if(data.code === 200){
      tagList.value = data.content
      //保存tagList到本地存储
      store.commit("setTAGLIST",data.content)
    }
  })
}

//种类表单数据
const tagForm = ref({
  tagName : ""
})

//展示Dialog界面触发事件
const handleTagShow = ()=>{
  //清空数据
  clearTagForm()
  tagDialogVisible.value = true
}


//关闭Dialog界面触发事件
const handleTagClose = ()=>{
  //清空数据
  tagDialogVisible.value = false
  clearTagForm()
}

const clearTagForm = ()=>{
  //清空数据
  tagForm.value = {
    tagName : ""
  }
}

//表示程序正在处理状态，防止短时间内重复提交
const tagIsLoading = ref(false)
//添加标签
const addTag = () => {
  if(tagForm.value.tagName.trim() === ''){
    ElMessage({
      type: "error",
      message: "请填写标签信息"
    })
    return
  }
  tagIsLoading.value = true
  axios.post("/backend/tag/addTag",tagForm.value).then(res => {
    let data = res.data
    if(data.code === 200){
      ElMessage({
        type: "success",
        message: data.msg
      })
      //添加完毕之后，清空缓存，让程序重新查询最新的记录来共用户选择
      store.commit('setTAGLIST', [])
      clearTagForm()
    } else {
      ElMessage({
        type: "error",
        message: data.msg
      })
    }

  }).catch(error => {
    // 处理请求错误
    ElMessage({
      type: "error",
      message: "请求失败，请稍后再试"
    });
    console.error("Error:", error);
  }).finally(() => {
    // 无论成功还是失败，都将加载状态设置为 false
    tagIsLoading.value = false;
  });
}






//种类区域变量===============

const categoryDialogVisible = ref(false)
//种类List
const categoryList = ref([])
const getCategoryList = () => {
  let cacheCategoryList = store.state.categorylist
  if(cacheCategoryList.length > 0){
    categoryList.value = cacheCategoryList
    return
  }
  axios.get("/backend/category/queryCategoryList").then(res => {
    let data = res.data
    if(data.code === 200){
      categoryList.value = data.content
      //保存tagList到本地存储
      store.commit("setCATEGORYLIST",data.content)
    }
  })
}

//种类表单数据
const categoryForm = ref({
  parentId: "",
  categoryName : ""
})

//展示Dialog界面触发事件
const handleCategoryShow = ()=>{
  //清空数据
  clearCategoryForm()
  categoryDialogVisible.value = true
}

//关闭Dialog界面触发事件
const handleCategoryClose = ()=>{
  //清空数据
  categoryDialogVisible.value = false
  clearCategoryForm()
}

const clearCategoryForm = ()=>{
  //清空数据
  categoryForm.value = {
    parentId: "",
    categoryName : ""
  }
}

//表示程序正在处理状态，防止短时间内重复提交
const categoryIsLoading = ref(false)
//添加种类
const addCategory = () => {
  if(categoryForm.value.categoryName.trim() === ''){
    ElMessage({
      type: "error",
      message: "请填写种类信息"
    })
    return
  }
  categoryIsLoading.value = true
  axios.post("/backend/category/addCategory",categoryForm.value).then(res => {
    let data = res.data
    if(data.code === 200){
      ElMessage({
        type: "success",
        message: data.msg
      })
      clearCategoryForm()
      //添加完毕之后，清空缓存，让程序重新查询最新的记录来共用户选择
      store.commit('setCATEGORYLIST', [])
    } else {
      ElMessage({
        type: "error",
        message: data.msg
      })
    }

  }).catch(error => {
    // 处理请求错误
    ElMessage({
      type: "error",
      message: "请求失败，请稍后再试"
    });
    console.error("Error:", error);
  }).finally(() => {
    // 无论成功还是失败，都将加载状态设置为 false
    categoryIsLoading.value = false;
  });
}


</script>

<style scoped>
/* 可以根据需要调整样式 */
.upload_course {
  height: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

.upload_course_left {
  width: 50%;
}

.form_title{
  font-weight: bold;
  font-size: 20px;
  width: 100%;
  text-align: left;
  border-left: 5px solid orange;
  margin-left: 3%;
  margin-bottom: 3%;
}

.form_a{
  text-decoration: none;
  color: orange;
  margin-left: 3%;
}
.form_a:hover{
  cursor: pointer;
}

.upload_course_right {
  width: 45%;
  height: 80vh;
  display: flex;
  align-items: self-start;
  justify-content: center;
}


.upload-demo ::v-deep .el-upload--text {
  width: auto;
}
</style>
