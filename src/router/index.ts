import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MapView from '../views/MapView.vue'
import WindowsView from '../views/WindowsView.vue'
import MerchantView from '../views/MerchantView.vue'
import DishView from '../views/DishView.vue'
import ReviewView from '../views/ReviewView.vue'

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
    }
  ]
})

export default router
