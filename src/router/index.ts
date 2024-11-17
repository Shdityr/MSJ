import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MapView from '../views/MapView.vue'
import WindowsView from '../views/WindowsView.vue'
import MerchantView from '../views/MerchantView.vue'
import DishView from '../views/DishView.vue'
import ReviewView from '../views/ReviewView.vue'
import UserView from '../views/UserView.vue'
import LoginView from '../views/LoginView.vue'
import MakeCommentView from '../views/MakeCommentView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView
    },
    {
      path: '/map',
      name: 'map',
      component: MapView
    },
    {
      path: '/windows',
      name: 'windows',
      component: WindowsView
    },
    {
      path: '/merchant/:merchantId',
      name: 'merchant',
      component: MerchantView
    },
    {
      path: '/dish/:merchantId/:dishId',
      name: 'dish',
      component: DishView
    },
    {
      path: '/review/:reviewId',
      name: 'review',
      component: ReviewView
    },
    {
      path: '/user',
      name: 'user',
      component: UserView,
      beforeEnter: (to, from, next) => {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        if (isLoggedIn) {
          next(); // 用户已登录，继续访问 UserView
        } else {
          next('/login'); // 用户未登录，重定向到 LoginView
        }
      },
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    }
    ,{
      path: '/make-comment',
      name: 'make-comment',
      component: MakeCommentView
    },
  ]
})

export default router
