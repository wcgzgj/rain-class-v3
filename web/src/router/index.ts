import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import Download from '../views/public/Download.vue'
import About from '../views/public/About.vue'
import Comment from '../views/public/Comment.vue'
import ClassInfo from '../views/class/ClassInfo.vue'
import StudentClassList from '../views/student/StudentClassList.vue'
import StudentChosenList from '../views/student/StudentChosenList.vue'
import StudentChosenClass from '../views/student/StudentChosenClass.vue'
import ResetPassword from '../views/user/ResetPassword.vue'

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
  {
    path: '/classinfo',
    name: 'ClassInfo',
    component: ClassInfo
  },
  {
    path: '/studentClassList',
    name: 'StudentClassList',
    component: StudentClassList
  },
  {
    path: '/studentChosenList',
    name: 'StudentChosenList',
    component: StudentChosenList
  },
  {
    path: '/studentChosenClass',
    name: 'StudentChosenClass',
    component: StudentChosenClass
  },
  {
    path: '/resetPassword',
    name: 'ResetPassword',
    component: ResetPassword
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
