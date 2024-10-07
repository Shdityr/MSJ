<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import MerchantView from './MerchantView.vue'

// 示例数据，可以替换成实际的商家数据
const merchants = ref([
  {
    id: 1,
    name: '商家1',
    rating: 4.7,
    averagePrice: 30,
    location: '位置1',
    image: '/path/to/image1.jpg'
  },
  {
    id: 2,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  },
  {
    id: 3,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  },
  {
    id: 4,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  },
  {
    id: 5,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  },
  {
    id: 6,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  },
  {
    id: 7,
    name: '商家2',
    rating: 4.5,
    averagePrice: 25,
    location: '位置2',
    image: '/path/to/image2.jpg'
  }
])

const currentMerchant = ref(null)
const currentMerchantView = ref(null)

const selectMerchant = (merchantId) => {
  currentMerchant.value = merchantId
}


watch(currentMerchant, (newMerchant) => {
  if (newMerchant) {
    currentMerchantView.value = MerchantView
  } else {
    currentMerchantView.value = null
  }
})

// 按评分顺序排序商家
const sortedMerchants = computed(() => {
  return merchants.value.sort((a, b) => b.rating - a.rating)
})
</script>

<template>
  <div class="merchant-list">
    <div v-if="!currentMerchant">
      <div
        v-for="merchant in sortedMerchants"
        :key="merchant.id"
        class="merchant-item"
        @click="selectMerchant(merchant.id)"
      >
        <img :src="merchant.image" alt="商家图片" class="merchant-image" />
        <div class="merchant-info">
          <h3 class="merchant-name">{{ merchant.name }}</h3>
          <p class="merchant-rating">
            评分: {{ merchant.rating }} | 人均: ¥{{ merchant.averagePrice }}
          </p>
          <p class="merchant-location">{{ merchant.location }}</p>
        </div>
      </div>
    </div>
    <div v-if="currentMerchant">
      <component
        :is="currentMerchantView"
        v-if="currentMerchantView"
        :merchant-id="currentMerchant"
        @goBack="currentMerchant = null"
      />
    </div>
  </div>
</template>

<style scoped>
.merchant-list {
  position: fixed;
  top: 20px;
  left: 20px;
  width: 350px;
  height: 90vh;
  overflow-y: auto;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  z-index: 10; /* 确保在上层 */
  opacity: 0.95; /* 增加一点透明感 */
  backdrop-filter: blur(5px); /* 可选：添加背景模糊效果 */
}

.merchant-item {
  display: flex;
  flex-direction: row;
  margin-bottom: 15px;
  padding: 10px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out;
}

.merchant-item:hover {
  transform: scale(1.02); /* 悬浮时稍微放大 */
}

.merchant-image {
  width: 60px;
  height: 60px;
  border-radius: 5px;
  margin-right: 15px;
}

.merchant-info {
  flex: 1;
}

.merchant-name {
  font-size: 16px;
  font-weight: bold;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.merchant-rating,
.merchant-location {
  font-size: 14px;
  color: #666;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
