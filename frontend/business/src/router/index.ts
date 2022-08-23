import {createRouter, createWebHistory} from "vue-router";
import Layout from "../views/layout/Layout.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            redirect: '/dashboard',
            component: Layout,
            children: [
                {
                    path: '/dashboard',
                    name: 'dashboard',
                    component: () => import('../views/Dashboard.vue'),
                    meta: {title: '面板', icon: 'dashboard'}
                }
            ]
        },
        {
            path: '/order',
            name: 'order',
            redirect: '/order/detail',
            component: Layout,
            children: [
                {
                    path: '/order/detail',
                    name: 'orderDetail',
                    component: () => import('../views/order/Order.vue'),
                    meta: {title: '订单', icon: 'order'}
                }
            ]
        },
        {
            path: '/menu',
            name: 'menu',
            redirect: '/menu/info',
            component: Layout,
            meta: {title: '菜单', icon: 'menu'},
            children: [
                {
                    path: '/menu/info',
                    name: 'menuInfo',
                    component: () => import('../views/menu/MenuInfo.vue'),
                    meta: {title: '菜单1', icon: 'menu'}
                },
                {
                    path: '/menu/catalog',
                    name: 'catalog',
                    component: () => import('../views/menu/Catalog.vue'),
                    meta: {title: '类别', icon: 'menu'}
                }
            ]
        },
        {
            path: '/user',
            name: 'user',
            redirect: '/user/info',
            component: Layout,
            children: [
                {
                    path: '/user/info',
                    name: 'UserInfo',
                    component: () => import('../views/user/UserInfo.vue'),
                    meta: {title: '用户', icon: 'user'}
                }
            ]
        },
        {
            path: "/login",
            name: "login",
            component: () => import('../views/login/Login.vue'),
            meta: {hidden: true}
        },
    ],
});

export default router;
