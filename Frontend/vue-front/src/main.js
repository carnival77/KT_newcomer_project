import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import { store } from "./store/store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import KeenUI from "keen-ui";
import "keen-ui/dist/keen-ui.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.min.css";
// import { VueSlideBar } from 'vue-slide-bar'
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "chart.js";
import "hchs-vue-charts";
import axios from "axios";
import VueSidebarMenu from "vue-sidebar-menu";
import VueSweetalert2 from "vue-sweetalert2";
Vue.use(VueSweetalert2);

Vue.use(VueSidebarMenu);

Vue.config.productionTip = false;

Vue.prototype.$Axios = axios;

window.Vue = Vue;

Vue.use(window.VueCharts);

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// use keen UI "https://github.com/JosephusPaye/Keen-UI/tree/master#keen-ui"
Vue.use(KeenUI);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

// Vue.user(VueSlideBar)
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
