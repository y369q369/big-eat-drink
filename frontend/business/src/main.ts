import {createApp} from "vue"
import {createPinia} from "pinia"

import App from "./App.vue"
import router from "./router"

import installElementPlus from "./plugins/element"

import 'element-plus/dist/index.css'
import "./assets/css/main.css"


const app = createApp(App)

installElementPlus(app)

app.use(createPinia())
app.use(router)

app.mount("#app")
