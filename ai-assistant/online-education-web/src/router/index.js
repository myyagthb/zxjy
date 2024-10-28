import { createRouter, createWebHistory } from 'vue-router'
import BaseView from "@/views/BaseView.vue";


const routes = [
  {
    path: '/base',
    name: 'base',
    component: BaseView,
    children: [
      {
        path: 'login',
        component: ()=>import('@/views/LoginView.vue')
      },
      {
        path: 'register',
        component: ()=>import('@/views/RegisterView.vue')
      },
      {
        path: 'home',
        component: ()=>import('@/views/HomeView.vue')
      },{
        path: 'online-course',
        component: ()=>import('@/views/OnlineCourseView.vue')
      },
      {
        path: 'about',
        component: () => import('@/views/AboutView.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
