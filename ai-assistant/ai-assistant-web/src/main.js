import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus, {ElMessage} from 'element-plus'
import 'element-plus/dist/index.css'


import axios from 'axios';

// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const app = createApp(App)


//添加路由守卫
router.beforeEach(async (to, from, next) => {
    //需要权限
    if (to.meta.requireAuth) {
        //获取本地用户临时ID，
        let userId = sessionStorage.getItem("userId") || 'a'
        console.log("userId is " + userId)
        //访问服务器，确认本用户否在线
        let res = await axios.get("/judgeUserIsOnline",{params: { userId }})

        console.log(res)
        //判断当前是否拥有权限

        if (res.data.content) {
            //当用户在线时，或者登录位过期，不需要重新登录
            console.log("不需要登录")
            next();
        } else {
            //当用户没有在线时，或者登录已过期，需要重新登录
            ElMessage({
                type: "error",
                message: "登录已过期，请重新登录"
            })
            sessionStorage.setItem("userId",null)
            console.log("需要登录")
            // 无权，跳转登录
            next({
                path: "/login",
                query: {redirect: from.fullPath},
            });
        }
    } else {
        // 不需要权限，直接访问
        next();
    }
});





app.use(store).use(router).use(ElementPlus)
//引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// 挂载
app.mount('#app')


axios.defaults.baseURL = process.env.VUE_APP_SERVER;