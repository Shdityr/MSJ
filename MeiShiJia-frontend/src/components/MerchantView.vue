<!-- src/components/MerchantView.vue -->
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

// 获取路由信息和路由器实例
const route = useRoute()
const router = useRouter()

const merchantId = route.params.merchantId
const currentMerchant = ref(null)
const Reviews = ref([])
const Dishes = ref([])

const selectDish = (merchantId, dishId) => {
  router.push({ name: 'dish', params: { merchantId: merchantId, dishId: dishId } })
}

const fetchData = async () => {
  try {
    const response = await axios.get('localhost:8081/restaurants', {
      params: {
        RestaurantId: merchantId,
        ReviewsSorted: 1,
        DishesSorted: 1
      }
    })
    currentMerchant.value = response.data
  } catch (error) {
    console.error('获取商家信息失败:', error)
  }

  try {
    const DishIds = currentMerchant.value.DishesId;

    for (let i = 0; i < DishIds.length; i++) {
      const DishId = DishIds[i];

      const response = await axios.get('localhost:8081/dishes', {
        params: {
          DishesId: DishId,
          DishesSorted: 1
        }
      });

      Dishes.value.push(response.data);
    }
  } catch (error) {
    console.error('获取菜品信息失败:', error)
  }

  try {
    const reviewIds = currentMerchant.value.reviewsId;

    for (let i = 0; i < reviewIds.length; i++) {
      const reviewId = reviewIds[i];

      const response = await axios.get('localhost:8081/reviews', {
        params: {
          ReviewId: reviewId
        }
      });

      Reviews.value.push(response.data);
    }
  } catch (error) {
    console.error('获取回复信息失败:', error)
  }
}

onMounted(() => {
  fetchData()
})
</script>

<template>
  <div class="merchant-container">
    <div class="merchant-header">
      <svg
        @click="goBack"
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
      <img :src="currentMerchant.images[0]" alt="商家图片" class="merchant-image" />
    </div>

    <div class="merchant-info">
      <h2 class="merchant-name">{{ currentMerchant.name }}</h2>
      <p class="merchant-rating">Rating: {{ currentMerchant.rating }}</p>
      <p class="merchant-location">Location: {{ currentMerchant.location }}</p>
      <p class="merchant-contact">Contact: {{ currentMerchant.contactInfo }}</p>
      <p class="merchant-hours">Business Hours: {{ currentMerchant.businessHours }}</p>
      <p class="merchant-average">Average Price: ¥{{ currentMerchant.averagePrice }}</p>
      <p class="merchant-style">Style: {{ currentMerchant.style.join(', ') }}</p>
    </div>

    <div class="menu-dishes">
      <h3>Menu Dishes</h3>
      <div class="dishes-container">
        <div
          v-for="dish in Dishes"
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

    <div class="reviews-section">
      <h3>Reviews</h3>
      <div class="review-item" v-for="review in Reviews" :key="review.id">
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
.merchant-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
}

/* 顶部返回箭头和商家图片 */
.merchant-header {
  position: relative;
  width: 108%;
  height: 100%;
  max-width: 600px;
}

/* 返回箭头 */
.back-arrow {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 24px;
  height: 24px;
  cursor: pointer;
  color: #333;
}

/* 商家图片 */
.merchant-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  object-fit: cover;
}

/* 商家信息部分 */
.merchant-info {
  margin-top: 20px;
  width: 108%;
  max-width: 600px;
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 商家名称样式 */
.merchant-name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
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
