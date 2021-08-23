import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Register from '@/components/Register'
import Subscribe from '@/components/Subscribe'
import Unsubscribe from '@/components/Unsubscribe'
import Information from '@/components/Information'
import Feedback from '@/components/Feedback'
import Admin from '@/components/Admin'
import Manage from '@/components/Manage'
import Notice from '@/components/Notice'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children:[
      {path: '1',component: Subscribe},
      {path: '2',component: Unsubscribe},
      {path: '3',component: Information},
      {path: '4',component: Feedback}
    ]
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/home/5',
    redirect: '/' 
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin,
    children: [
      {path: '1',component: Manage},
      {path: '2',component: Notice}
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
