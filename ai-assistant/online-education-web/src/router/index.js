import { createRouter, createWebHistory } from 'vue-router'
import BaseView from "@/views/BaseView.vue";


const routes = [
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
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
        path: 'course-specifics',
        component: () => import('@/views/CourseSpecificsView.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
