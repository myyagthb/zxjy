<template>
  <div class="my_course">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="全部" name="first">

        <div class="course_list">
          <div class="course_item" v-for="(item,index) in itemList" :key="index" >
            <div class="course_item_img_div">
              <!-- <router-link to="/">
                <img :src="item.courseImg" >
              </router-link> -->
              <!-- <router-link to="/" tag="img" src="item.courseImg"></router-link> -->
              <img :src="item.courseImg" @click="handleCourseClick(item)">
            </div>
            <div class="item_title">
              {{item.courseTitle}}
            </div>
            <div class="item_progress">
              <el-progress :percentage="item.percentage" />
            </div>
            <div class="item_desc">
              已学习{{item.learned}}/{{item.total}}节
            </div>
          </div>
        </div>

        <div style="text-align: left;">
          <h2>
            &emsp;&emsp;&emsp;课程推荐
          </h2>
        </div>

        <div class="course_list">
          <div class="course_item" v-for="(item,index) in itemList" :key="index" >
            <div class="course_item_img_div">
              <img :src="item.courseImg" >
            </div>
            <div class="item_title">
              {{item.courseTitle}}
            </div>
            <div class="item_progress">
              <el-progress :percentage="item.percentage" />
            </div>
            <div class="item_desc">
              已学习{{item.learned}}/{{item.total}}节
            </div>
          </div>
        </div>

      </el-tab-pane>
      <el-tab-pane label="免费课程" name="second">
        <div class="course_list">
          <div class="course_item" v-for="(item,index) in itemList" :key="index" >
            <div class="course_item_img_div">
              <img :src="item.courseImg" >
            </div>
            <div class="item_title">
              {{item.courseTitle}}
            </div>
            <div class="item_progress">
              <el-progress :percentage="item.percentage" />
            </div>
            <div class="item_desc">
              已学习{{item.learned}}/{{item.total}}节
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="会员课程" name="third">
        <div class="course_list">
          <div class="course_item" v-for="(item,index) in itemList" :key="index" >
            <div class="course_item_img_div">
              <img :src="item.courseImg" >
            </div>
            <div class="item_title">
              {{item.courseTitle}}
            </div>
            <div class="item_progress">
              <el-progress :percentage="item.percentage" />
            </div>
            <div class="item_desc">
              已学习{{item.learned}}/{{item.total}}节
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";
import { ref } from 'vue'
import { useCoursesQuery } from '@/data/course/course-query'
const activeName = ref('first')

const handleClick = (tab, event) => {
  console.log(tab, event)
}

const handleCourseClick = (item) => {
  console.log(item.courseTitle)
  // router.push("/course-specifics?item="+item)
  router.push({path:"/course-specifics",query:{item:JSON.stringify(item)}})
  // router.push({path:"/course-specifics",query:{item:item}})
  // router.push("/course-specifics",{params:{item:item}})
}

const courses = useCoursesQuery()

const itemList = courses

// const itemList = ref([
//   {
//     courseId: '1',
//     courseImg: require('assets/images/student/vue-img.jpeg'),  //课程封面
//     courseTitle: "vue课程",      //课程标题
//     percentage: 20,                       //已学习占比
//     customColor: "#409eff",               //进度条颜色
//     learned: 2,                           //已学习课程数
//     total: 8,                             //课程总数
//   },{
//     courseImg: require('assets/images/student/suanfa-img.jpg'),  //课程封面
//     courseTitle: "数据结构与算法",      //课程标题
//     percentage: 20,                       //已学习占比
//     customColor: "#409eff",               //进度条颜色
//     learned: 2,                           //已学习课程数
//     total: 8,                             //课程总数
//   },{
//     courseImg: require('assets/images/student/weixin-img.png'),  //课程封面
//     courseTitle: "微信小程序入门与实践",      //课程标题
//     percentage: 20,                       //已学习占比
//     customColor: "#409eff",               //进度条颜色
//     learned: 2,                           //已学习课程数
//     total: 8,                             //课程总数
//   },{
//     courseImg: require('assets/images/student/python-img.jpeg'),  //课程封面
//     courseTitle: "Python入门加进阶",      //课程标题
//     percentage: 20,                       //已学习占比
//     customColor: "#409eff",               //进度条颜色
//     learned: 2,                           //已学习课程数
//     total: 8,                             //课程总数
//   }
// ])


</script>

<style scoped>
.demo-tabs{
  width: 100%;
}

.course_list{
  width: 100%;
  height: auto;
  display: flex;
  align-items: self-start;
  justify-content: flex-start;
  flex-wrap: wrap;
}

.course_item{
  width: 20%;
  margin-left: 4%;
}

.course_item_img_div>img{
  width: 100%;
  height: 25%;
  min-height: 160px;
  border-radius: 10px;
  cursor: pointer;
}

.item_title{
  margin-top: 5%;
  font-weight: bold;
  font-size: 18px;
}

.item_progress{
  margin-top: 5%;
  margin-bottom: 5%;
}

.item_desc{
  text-align: left;
}
</style>