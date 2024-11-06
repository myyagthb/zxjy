import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//中文化
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

import axios from 'axios';

const app = createApp(App)

// 全局异常处理
app.config.errorHandler = (err, vm, info) => {
    // 处理错误
    // `info` 是 Vue 特定的错误信息，比如错误所在的生命周期钩子
    console.log(vm)
    console.log(info)
    console.log('====== 有报错了兄弟!!! ======');
    if (err instanceof Error) {
        console.log('❌ 提示:', err.message);
    }

};


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(store).use(router).use(ElementPlus).use(ElementPlus, {
    locale: zhCn,
})

app.mount('#app')


axios.defaults.baseURL = process.env.VUE_APP_SERVER;
