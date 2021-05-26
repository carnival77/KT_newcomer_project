import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../components/views/Home.vue";
import Order from "../components/views/Order.vue";

import Info from "../components/views/Info.vue";
import Info_TV from "../components/views/Info_Tv.vue";
import Info_Mobile from "../components/views/Info_Mobile.vue";
import Info_Internet from "../components/views/Info_Internet.vue";
import Info_User from "../components/views/Info_User.vue";
import Recommend from "../components/views/Recommend.vue";
import Home_Redirect from "../components/views/RedirectHome.vue";

import VueChartJS from "../components/views/VueChartJS";
import Login from "@/components/Login";
import layout from "../layout";

// import Login from "../components/views/Login.vue";
Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
    },
    {
      path: "/order",
      name: "Order",
      component: Order,
    },
    {
      path: "/info",
      name: "Info",
      component: Info,
    },
    {
      path: "/info/tv",
      name: "Info_TV",
      component: Info_TV,
    },
    {
      path: "/info/mobile",
      name: "Info_Mobile",
      component: Info_Mobile,
    },
    {
      path: "/info/internet",
      name: "Info_Internet",
      component: Info_Internet,
    },
    {
      path: "/info/user",
      name: Info_User,
      component: Info_User,
    },
    {
      path: "/result",
      name: "Recommend",
      component: Recommend,
    },
    {
      path: "/result_",
      name: "Home_Redirect",
      component: Home_Redirect,
    },
    {
      path: "/chartjs",
      name: "VueChartJS",
      component: VueChartJS,
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
    },
    {
      path: "/dashboard",
      component: layout,
      children: [
        {
          path: "",
          name: "dashboard",
          component: () => import("@/pages/dashboard"),
        },
      ],
    },
  ],
});

export default router;
