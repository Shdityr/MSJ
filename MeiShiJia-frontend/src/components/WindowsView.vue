<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import MerchantView from './MerchantView.vue'
import { useRouter } from 'vue-router'
const router = useRouter()

// 示例数据，可以替换成实际的商家数据
const merchants = ref([
  {
    id: 1,
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
    id: 2,
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
    id: 3,
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
])

const selectMerchant = (merchantId) => {
  router.push({ name: 'merchant', params: { merchantId: merchantId } })
}

// 按评分顺序排序商家
const sortedMerchants = computed(() => {
  return merchants.value.sort((a, b) => b.rating - a.rating)
})
</script>

<template>
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
</template>

<style scoped>
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
