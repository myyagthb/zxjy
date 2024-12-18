import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import MainView from "@/views/MainView.vue";

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView,
    meta: {
      requireAuth: true,
    },
    children: [
      {
        path: '/chat',
        component: ()=>import('@/views/main/ChatView.vue')
      },
      {
        path: '/present',
        component: ()=>import('@/views/main/PresentView.vue')
      },
      {
        path: '/history',
        component: ()=>import('@/views/main/HistoryView.vue')
      },
      {
        path: '/user',
        component: ()=>import('@/views/main/UserView.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
