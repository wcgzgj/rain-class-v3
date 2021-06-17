import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import Download from '../views/public/Download.vue'
import About from '../views/public/About.vue'
import Comment from '../views/public/Comment.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/download',
    name: 'Download',
    component: Download
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/comment',
    name: 'Comment',
    component: Comment
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
