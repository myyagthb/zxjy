<template>

  <div>
    <el-table :data="myCourseList" style="width: 100%">
      <el-table-column type="index" label="序号" width="80" />
      <el-table-column label="课程图片" >
        <template #default="scope">
          <img :src="scope.row.courseUrl" style="width: 40%;height: 40%;" alt="课程">
        </template>
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称" />
      <el-table-column prop="courseStatus" label="课程状态" />
      <el-table-column prop="courseCategory" label="课程分类" />
      <el-table-column prop="CourseTag" label="课程标签" />
      <el-table-column prop="courseTeacher" label="讲师" />
      <el-table-column prop="revenue" label="课程评分" />
      <el-table-column prop="revenue" label="学习人数" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="searchParams.searchText" size="small" placeholder="请输入搜索关键字" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEditCourse(scope.row)">
            编辑
          </el-button>
          <el-button
              size="small"
              type="danger"
              @click="handleDeleteCourse(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        layout="prev, pager, next"
        :current-page="pagingParams.currentPage"
        :total="pagingParams.total"
        :page-size="pagingParams.pageSize"
        @current-change="handlePageChange"
        style="margin-top: 20px"
    />
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from "axios";
import store from "@/store";
import {ElMessage} from "element-plus";

// 表格数据
const myCourseList = ref([]);


const searchParams = ref({
  currentPage: 1,
  pageSize: 5,
  searchText: "",
  userId: null
})

const pagingParams = ref({
  currentPage: 0,
  pageSize: 5,
  total: 0
})

onMounted(()=> {
  getCourseBySearchText()
})

//根据文本搜索课程信息
const getCourseBySearchText = () => {
  searchParams.value.userId = store.state.user.userId
  axios.post("/backend/course/searchCourseBySearchText",searchParams.value).then(res =>{
    let data = res.data
    if(data.code === 200){
      ElMessage.success(data.msg)
      myCourseList.value = data.content.records
      pagingParams.value.currentPage = data.content.current
      pagingParams.value.pageSize = data.content.size
      pagingParams.value.total = data.content.total



      //处理封面图片展示
      // for(let i = 0;i<myCourseList.value.length;i++){
      //   axios.get("/storage/object/test/"+myCourseList.value[i].courseUrl,{
      //     headers:{
      //       'Range': 'bytes=0-1023', // 如果需要范围请求
      //       'x-forwarded-host':'demo',
      //       'authorization':'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-RRdA8gxmv5hhTLu8OpHbRGIX4P7VAu3eXPOUyDJZDE'
      //     },
      //     responseType: "blob"
      //   }).then( response => {
      //     const blob = new Blob([response.data], { type: response.headers['content-type'] });
      //     myCourseList.value[i].courseUrl = URL.createObjectURL(blob);
      //     console.log(URL.createObjectURL(blob))
      //   }).catch(err => {
      //     ElMessage.error(err.message)
      //   })
      // }
    } else {
      ElMessage.error(data.msg)
    }
  }).catch(err => {
    ElMessage.error(err.message)
  })
}


//处理编辑课程事件
const handleEditCourse = (row) => {
  console.log(row)
}

//处理删除课程事件
const handleDeleteCourse = (row) => {
  console.log(row)
}


// 更新当前页
const handlePageChange = (page) => {
  console.log(page)
};
</script>

<style scoped>
/* 这里可以加入你的样式定义 */
</style>
