import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//引入element plus的组件库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

//引入自定义的css样式
import '@/assets/css/global.css'

//引入国际化 中文组件
import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App).use(store).use(router).use(ElementPlus, {locale: zhCn, size: 'small'}).mount('#app')
