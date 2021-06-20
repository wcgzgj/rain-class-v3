import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import Download from '../views/public/Download.vue'
import About from '../views/public/About.vue'
import Comment from '../views/public/Comment.vue'
import ClassInfo from '../views/class/ClassInfo.vue'
import StudentClassList from '../views/student/StudentClassList.vue'
import StudentChosenList from '../views/student/StudentChosenList.vue'
import StudentChosenClass from '../views/student/StudentChosenClass.vue'
import StudentScore from '../views/student/StudentScore.vue'
import TeacherClassInfo from '../views/teacher/TeacherClassInfo.vue'
import TeacherClassList from '../views/teacher/TeacherClassList.vue'
import TeacherComment from '../views/teacher/TeacherComment.vue'
import AdminClassList from '../views/admin/AdminClassList.vue'
import AdminClassInfo from '../views/admin/AdminClassInfo.vue'
import AdminClassAdd from '../views/admin/AdminClassAdd.vue'
import AdminComment from '../views/admin/AdminComment.vue'
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
    path: '/studentScore',
    name: 'StudentScore',
    component: StudentScore
  },
  {
    path: '/teacherClassInfo',
    name: 'TeacherClassInfo',
    component: TeacherClassInfo
  },
  {
    path: '/teacherClassList',
    name: 'TeacherClassList',
    component: TeacherClassList
  },
  {
    path: '/teacherComment',
    name: 'TeacherComment',
    component: TeacherComment
  },
  {
    path: '/resetPassword',
    name: 'ResetPassword',
    component: ResetPassword
  },
  {
    path: '/adminClassList',
    name: 'AdminClassList',
    component: AdminClassList
  },
  {
    path: '/adminClassInfo',
    name: 'AdminClassInfo',
    component: AdminClassInfo
  },
  {
    path: '/adminClassAdd',
    name: 'AdminClassAdd',
    component: AdminClassAdd
  },
  {
    path: '/adminComment',
    name: 'AdminComment',
    component: AdminComment
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
