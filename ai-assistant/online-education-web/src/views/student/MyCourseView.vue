<template>
   <!-- <Suspense> -->
  <div class="my_course">
    <div v-if="isLoading">Loading...</div>
    <div v-else>
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="全部" name="first">
          <div class="course_list">
            <div class="course_item" v-for="(item) in itemList" :key="item" >
              <div class="course_item_img_div">
                <!-- <router-link to="/">
                  <img :src="item.courseImg" >
                </router-link> -->
                <!-- <router-link to="/" tag="img" src="item.courseImg"></router-link> -->
                <!-- <img :src="item.courseImg" @click="handleCourseClick(item)"> -->
              </div>
              <div>a</div>
              <div class="item_title">
                a
              </div>
              <div class="item_progress">
                <!-- <el-progress :percentage="item.percentage" /> -->
              </div>
              <div class="item_desc">
                <!-- 已学习{{item.learned}}/{{item.total}}节 -->
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
                <!-- <img :src="item.courseImg" > -->
              </div>
              <div class="item_title">
                {{item.courseName}}
              </div>
              <div class="item_progress">
                <!-- <el-progress :percentage="item.percentage" /> -->
              </div>
              <div class="item_desc">
                <!-- 已学习{{item.learned}}/{{item.total}}节 -->
              </div>
            </div>
          </div>

        </el-tab-pane>
        <el-tab-pane label="免费课程" name="second">
          <div class="course_list">
            <div class="course_item" v-for="(item,index) in itemList" :key="index" >
              <div class="course_item_img_div">
                <!-- <img :src="item.courseImg" > -->
              </div>
              <div class="item_title">
                {{item.courseName}}
              </div>
              <div class="item_progress">
                <!-- <el-progress :percentage="item.percentage" /> -->
              </div>
              <div class="item_desc">
                <!-- 已学习{{item.learned}}/{{item.total}}节 -->
              </div>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="会员课程" name="third">
          <div class="course_list">
            <div class="course_item" v-for="(item,index) in itemList" :key="index" >
              <div class="course_item_img_div">
                <!-- <img :src="item.courseImg" > -->
              </div>
              <div class="item_title">
                {{item.courseName}}
              </div>
              <div class="item_progress">
                <!-- <el-progress :percentage="item.percentage" /> -->
              </div>
              <div class="item_desc">
                <!-- 已学习{{item.learned}}/{{item.total}}节 -->
              </div>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
<!-- </Suspense> -->
</template>

<script setup>
// import { applyPureReactInVue} from 'veaury'
// import {applyPureReactInVue, createCrossingProviderForPureVueInReact } from 'veaury'
// import { onMounted, onUnmounted} from "vue";
// import {useQueryClient, useQuery } from '@tanstack/vue-query'
import { useQuery } from '@tanstack/vue-query'
// import router from "@/router";
import store from "@/store";
import { ref } from 'vue'
import axios from "axios";
// import { useCoursesQuery } from '@/data/course/course-query'
// import {getAllCourses} from '@/data/course/course-query'
// import { courseKeys } from '@/data/course/keys'
// const activeName = ref('first')

// const itemList = ref([])

// const handleClick = (tab, event) => {
//   console.log(tab, event)
// }

// const handleCourseClick = (item) => {
//   console.log(item.courseTitle)
//   // router.push("/course-specifics?item="+item)
//   router.push({path:"/course-specifics",query:{item:JSON.stringify(item)}})
//   // router.push({path:"/course-specifics",query:{item:item}})
//   // router.push("/course-specifics",{params:{item:item}})
// }

// onMounted(async () => {


// const courseQuery = applyPureReactInVue(useCoursesQuery)
// const courses = courseQuery()
const userId = store.state.user.userId
console.log('userId '+userId)
// const courses = useCoursesQuery({userId})
// const {data:courses} = useCoursesQuery({
//   async onSuccess(courses){
//     console.log('courses '+JSON.stringify(courses))
//     const itemList = courses
//     // console.log('courses '+JSON.stringify(itemList))
//     console.log('courses '+itemList[0].courseName)
//   }
// })

// const courses = useCoursesQuery({})

const courseId = ref('1'); // 假设用户ID是1
// const queryClient = useQueryClient()
const {data: itemList,isLoading} = useQuery({
      // courseKeys.list(),
      // ({ signal }) => getCourses(signal),
      queryKey: ['getCourse',courseId],
      // queryKey: courseKeys.list(),
      // queryFn: ({ signal }) => getAllCourses(signal),
      queryFn: () => {  return axios.get('/api/backend/course/getCourseList').then(
        response => {
          console.log("axios returned data "+JSON.stringify(response.data));
        return response.data.content;
        })
      }
      // {
      //   ...options,
      //   // enabled: IS_PLATFORM && (options.enabled ?? true),
      //   enabled: true,
      //   staleTime: 30 * 60 * 1000,
      // }
    })
// console.log("get courses is loading "+  isLoading.value)
// console.log('courses '+JSON.stringify(data))
// itemList.value = data
// // console.log('courses '+JSON.stringify(itemList))
// console.log('first course '+itemList[0])


  

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



// export default {
//   components: {
//     // Use HOC 'applyReactInVue' or 'applyPureReactInVue'
//     Basic: applyReactInVue(useCoursesQuery), 
//     BasicPure: applyPureReactInVue(useCoursesQuery)
//   },
//   setup() {
//     return {
//       foo: ref('Hello!')
//     }
//   }
// }



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