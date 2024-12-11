import { createRouter, createWebHistory } from 'vue-router'
import BaseView from "@/views/BaseView.vue";
import StudentBaseView from "@/views/student/StudentBaseView.vue";
import TeacherBaseView from "@/views/teacher/TeacherBaseView.vue";

const routes = [
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/base/AboutView.vue')
  // },
  {
    path: '/',
    name: 'base',
    component: BaseView,
    children: [
      {
        path: '',
        component: ()=>import('@/views/base/HomeView.vue')
      },
      {
        path: 'login',
        component: ()=>import('@/views/base/LoginView.vue')
      },
      {
        path: 'register',
        component: ()=>import('@/views/base/RegisterView.vue')
      },
      {
        path: 'online-course',
        component: ()=>import('@/views/base/OnlineCourseView.vue')
      },
      {
        path: 'about',
        component: () => import('@/views/base/AboutView.vue')
      },
      {
        path: 'course-specifics',
        component: () => import('@/views/base/CourseSpecificsView.vue')
      },
      {
        path: 'member-price',
        component: () => import('@/views/base/MemberPriceView.vue')
      },{
        path: 'contact-us',
        component: ()=>import('@/views/base/ContactUsView.vue')
      }
    ]
  }
  ,{
    path: '/student',
    name: 'student',
    component: StudentBaseView,
    children: [
      {
        path: 'my-course',
        component: () => import('@/views/student/MyCourseView.vue')
      },{
        path: 'my-information',
        component: () => import('@/views/student/MyInformationView.vue')
      },{
        path: 'study-path-plan-one',
        component: () => import('@/views/student/StudyPathPlanOneView.vue')
      },{
        path: 'study-path-plan-two',
        component: () => import('@/views/student/StudyPathPlanTwoView.vue')
      },{
        path: 'study-progress',
        component: () => import('@/views/student/StudyProgressView.vue')
      },
    ]
  },
  {
    path: '/teacher',
    name: 'teacher',
    component: TeacherBaseView,
    children: [
      {
        path: 'my-course',
        component: () => import('@/views/teacher/MyCourseView.vue')
      },{
        path: 'upload-course',
        component: () => import('@/views/teacher/UploadCourseView.vue')
      },{
        path: 'upload-video',
        component: () => import('@/views/teacher/UploadVideoView.vue')
      },{
        path: 'course-calendar',
        component: () => import('@/views/teacher/CourseCalendarView.vue')
      },{
        path: 'test-management',
        component: () => import('@/views/teacher/TestManagementView.vue')
      },{
        path: 'questionBank-management',
        component: () => import('@/views/teacher/QuestionBankManagementView.vue')
      },{
        path: 'mytest-management',
        component: () => import('@/views/teacher/MyTest.vue')
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
