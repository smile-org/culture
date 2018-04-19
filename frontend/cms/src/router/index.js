import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import login from '@/views/login'
// 首页
import advertising from '@/views/homepage/advertising'
import kernel from '@/views/homepage/kernel'
import hotTopic from '@/views/homepage/hotTopic'
// 文化展览
import exhibitionDetail from '@/views/exhibition/detail'
// 新闻配置
import newsDetail from '@/views/news/detail'

Vue.use(Router)

export default new Router({
  routes: [
    // 登录
    {
      path: '/login',
      name: 'login',
      component: login,
      meta: {requiresAuth: false}
    },
    {
      path: '/',
      name: 'index',
      component: advertising,
      meta: {requiresAuth: true}
    },
    {
      path: '/homepage/advertising',
      name: 'advertising',
      component: advertising,
      meta: {requiresAuth: true}
    },
    {
      path: '/homepage/kernel',
      name: 'kernel',
      component: kernel,
      meta: {requiresAuth: true}
    },
    {
      path: '/homepage/hotTopic',
      name: 'hotTopic',
      component: hotTopic,
      meta: {requiresAuth: true}
    },
    {
      path: '/exhibition/detail',
      name: 'exhibitionDetail',
      component: exhibitionDetail,
      meta: {requiresAuth: true}
    },
    {
      path: '/news/detail',
      name: 'newsDetail',
      component: newsDetail,
      meta: {requiresAuth: true}
    }
  ]
})
