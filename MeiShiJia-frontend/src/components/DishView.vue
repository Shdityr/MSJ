<!-- src/components/MerchantView.vue -->
<script setup lang="ts">
import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

// 获取路由信息和路由器实例
const route = useRoute()
const router = useRouter()

const merchants = [
  {
    id: '1',
    name: 'Golden Dragon Restaurant',
    image: 'https://via.placeholder.com/300x200',
    rating: 4.8,
    averagePrice: 100,
    location: '123 Dragon Street',
    contactInfo: '123-456-7890',
    businessHours: '9:00 AM - 10:00 PM',
    style: ['Chinese', 'Seafood'],
    menuDishes: [
      {
        id: 101,
        name: 'Sweet and Sour Pork',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 102,
        name: 'Kung Pao Chicken',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.9
      },
      {
        id: 103,
        name: 'Steamed Fish',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.6
      },
      {
        id: 104,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 105,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      }
    ]
  },
  {
    id: '2',
    name: 'Sakura Sushi Bar',
    image: 'https://via.placeholder.com/300x200',
    rating: 4.5,
    averagePrice: 150,
    location: '789 Sakura Avenue',
    contactInfo: '987-654-3210',
    businessHours: '11:00 AM - 9:00 PM',
    style: ['Japanese', 'Sushi'],
    menuDishes: [
      {
        id: 201,
        name: 'Salmon Sushi',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.8
      },
      {
        id: 202,
        name: 'Tuna Roll',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 203,
        name: 'Miso Soup',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.5
      },
      {
        id: 204,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 205,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      }
    ]
  },
  {
    id: '3',
    name: 'Pasta Palace',
    image: 'https://via.placeholder.com/300x200',
    rating: 4.6,
    averagePrice: 80,
    location: '456 Pasta Blvd',
    contactInfo: '123-789-4560',
    businessHours: '10:00 AM - 11:00 PM',
    style: ['Italian', 'Pasta'],
    menuDishes: [
      {
        id: 301,
        name: 'Spaghetti Carbonara',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.9
      },
      {
        id: 302,
        name: 'Lasagna',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.8
      },
      {
        id: 303,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 304,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      },
      {
        id: 305,
        name: 'Fettuccine Alfredo',
        image: 'https://via.placeholder.com/300x200',
        rating: 4.7
      }
    ]
  }
]

const dishId = route.params.dishId
const merchantId = route.params.merchantId

const currentMerchant = computed(() => {
  return merchants.find((merchant) => merchant.id === merchantId)
})

const currentDish = computed(() => {
  return currentMerchant.menuDishes.find((dish) => dish.id === dishId)
})

const sortedMenuDishes = computed(() => {
  const currentMerchant = merchants.find((merchant) => merchant.id === merchantId)
  if (!currentMerchant || !currentMerchant.menuDishes) {
    return []
  }

  return currentMerchant.menuDishes.slice().sort((a, b) => b.rating - a.rating)
})

const sortedReviews = ref([
  {
    id: 1,
    userAvatar: 'https://via.placeholder.com/300x300',
    userName: 'Frank',
    time: '2024-10-01',
    content: 'If you love spicy food, this is the place to be! The hotpot is fantastic.',
    images: [
      'https://via.placeholder.com/300x300',
      'https://via.placeholder.com/300x300',
      'https://via.placeholder.com/300x300',
      'https://via.placeholder.com/300x300',
      'https://via.placeholder.com/300x300'
    ],
    rating: 5.0
  },
  {
    id: 2,
    userAvatar: 'https://via.placeholder.com/300x300',
    userName: 'Grace',
    time: '2024-09-30',
    content: 'Mapo tofu is a must-try! A bit too spicy for me though.',
    images: ['https://via.placeholder.com/300x300'],
    rating: 4.5
  }
])

function goBack(merchantId) {
  router.push({ name: 'merchant', params: { merchantId: merchantId } })
}

const selectDish = (merchantId, dishId) => {
  router.push({ name: 'dish', params: { merchantId: merchantId, dishId: dishId } })
}
</script>

<template>
  <div class="dish-container">
    <div class="dish-header">
      <svg
        @click="goBack(merchantId)"
        xmlns="http://www.w3.org/2000/svg"
        class="back-arrow"
        viewBox="0 0 24 24"
      >
        <path
          d="M15 18l-6-6 6-6"
          stroke="currentColor"
          stroke-width="2"
          fill="none"
          stroke-linecap="round"
          stroke-linejoin="round"
        />
      </svg>
      <h2 class="merchant-name">{{ currentMerchant.name }}</h2>
    </div>
    <!-- 商家信息 -->
    <div class="menu-dishes">
      <h3>Menu Dishes</h3>
      <div class="dishes-container">
        <div
          v-for="dish in sortedMenuDishes"
          :key="dish.id"
          class="dish-item"
          @click="selectDish(currentMerchant.id, dish.id)"
        >
          <img :src="dish.image" alt="菜品图片" class="dish-image" />
          <div class="dish-info">
            <h4 class="dish-name">{{ dish.name }}</h4>
            <p class="dish-rating">{{ dish.rating }}分</p>
          </div>
        </div>
      </div>
    </div>
    <!-- <p>123</p> -->

    <div class="info">
      <h2 class="merchant-name">{{ currentMerchant.name }}</h2>
      <p class="merchant-rating">Rating: {{ currentMerchant.rating }}</p>
      <p class="merchant-location">Location: {{ currentMerchant.location }}</p>
      <p class="merchant-contact">Contact: {{ currentMerchant.contactInfo }}</p>
      <p class="merchant-hours">Business Hours: {{ currentMerchant.businessHours }}</p>
      <p class="merchant-average">Average Price: ¥{{ currentMerchant.averagePrice }}</p>
      <p class="merchant-style">Style: {{ currentMerchant.style.join(', ') }}</p>
    </div>

    <div class="reviews-section">
      <h3>Reviews</h3>
      <div class="review-item" v-for="review in sortedReviews" :key="review.id">
        <div class="review-header">
          <div class="review-header-left">
            <img :src="review.userAvatar" alt="用户头像" class="review-avatar" />
            <div class="review-info">
              <h4 class="review-username">{{ review.userName }}</h4>
              <p class="review-time">{{ review.time }}</p>
            </div>
          </div>
          <!-- 将 rating 显示在右上角 -->
          <div class="review-rating">{{ review.rating }} ★</div>
        </div>
        <p class="review-content">{{ review.content }}</p>
        <div v-if="review.images.length > 0" class="review-images">
          <img
            v-for="(image, index) in review.images.slice(0, 3)"
            :key="index"
            :src="image"
            alt="评论图片"
            class="review-image"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 整体容器 */
.dish-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
}

/* 顶部返回箭头和商家图片 */
.dish-header {
  display: flex; /* 使用 Flexbox 布局 */
  align-items: center; /* 垂直居中对齐 */
  position: relative;
  width: 100%; /* 修改为 100% */
  max-width: 600px; /* 限制最大宽度 */
  padding-bottom: 20px;
}

/* 返回箭头 */
.back-arrow {
  width: 24px;
  height: 24px;
  cursor: pointer;
  color: #333;
  margin-right: 10px; /* 给返回箭头添加右边距，使其与商家名称分开 */
}

/* 商家名称样式 */
.merchant-name {
  font-size: 24px;
  font-weight: bold;
  margin: 0; /* 去掉默认的 margin，确保紧接着返回箭头 */
}

.info {
  margin-top: 20px;
  width: 108%;
  max-width: 600px;
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 商家其他信息样式 */
.merchant-rating,
.merchant-location,
.merchant-contact,
.merchant-hours,
.merchant-average,
.merchant-style {
  font-size: 16px;
  margin: 5px 0;
}

.menu-dishes {
  overflow-x: auto; /* 允许水平滚动 */
  padding-left: 0px;
  padding-right: 0px;
  padding-top: 20px;
  gap: 10px; /* 为菜品之间添加间隙 */
}

.dishes-container {
  display: flex;
  overflow-x: auto; /* 允许横向滚动 */
}

.dish-item {
  flex: 0 0 30%; /* 每个菜品占用 30% 的宽度 */
  background: rgba(255, 255, 255, 0.9); /* 背景色 */
  border-radius: 5px; /* 圆角边框 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* 阴影效果 */
  padding: 5px;
  text-align: center; /* 文本居中 */
}

.dish-image {
  width: 100%; /* 图片占满整个菜品项 */
  height: auto; /* 自动调整高度 */
  border-radius: 5px; /* 圆角边框 */
  object-fit: cover;
}

.dish-info {
  margin-top: 5px; /* 上边距 */
  text-align: center;
}

.dish-name {
  font-size: 0.6em;
  margin-bottom: 5px;
}

.dish-rating {
  font-size: 0.4em;
  color: #888;
}

.dish-item:last-child {
  margin-right: 0; /* 去掉最后一个菜品的右边距 */
}

.reviews-section {
  margin-top: 20px;
}

.review-item {
  margin-bottom: 20px;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
}

.review-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.review-header-left {
  display: flex;
  align-items: center;
}

.review-avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.review-info {
  display: flex;
  flex-direction: column;
}

.review-username {
  font-size: 16px;
  font-weight: bold;
}

.review-time {
  font-size: 12px;
  color: #666;
}

.review-rating {
  font-size: 14px;
  color: #f39c12;
  align-self: flex-start; /* 确保评分与顶部对齐 */
}

.review-content {
  margin-top: 10px;
}

.review-images {
  margin-top: 10px;
}

.review-image {
  width: 80px;
  height: 80px;
  margin-right: 10px;
  object-fit: cover;
}
</style>
