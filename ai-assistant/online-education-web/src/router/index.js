import { createRouter, createWebHistory } from 'vue-router'
import BaseView from "@/views/BaseView.vue";


const routes = [
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/base/AboutView.vue')
  },
  {
    path: '/base',
    name: 'base',
    component: BaseView,
    children: [
      {
        path: 'login',
        component: ()=>import('@/views/base/LoginView.vue')
      },
      {
        path: 'register',
        component: ()=>import('@/views/base/RegisterView.vue')
      },
      {
        path: 'home',
        component: ()=>import('@/views/base/HomeView.vue')
      },{
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
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
