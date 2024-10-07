<!-- src/components/MerchantView.vue -->
<script setup lang="ts">
import { defineProps, computed, defineEmits, ref } from 'vue';

const props = defineProps<{
  merchantId: number; // 接收商家ID
}>();

const emit = defineEmits();

const goBack = () => {
  // 触发自定义事件 goBack
  emit('goBack');
};

const merchants = [
  {
    id: 1,
    name: "Golden Dragon Restaurant",
    image: "https://via.placeholder.com/300x200",
    rating: 4.8,
    averagePrice: 100,
    location: "123 Dragon Street",
    contactInfo: "123-456-7890",
    businessHours: "9:00 AM - 10:00 PM",
    style: ["Chinese", "Seafood"],
    menuDishes: [
      {
        id: 101,
        name: "Sweet and Sour Pork",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 102,
        name: "Kung Pao Chicken",
        image: "https://via.placeholder.com/300x200",
        rating: 4.9
      },
      {
        id: 103,
        name: "Steamed Fish",
        image: "https://via.placeholder.com/300x200",
        rating: 4.6
      },
      {
        id: 104,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 105,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      }
    ]
  },
  {
    id: 2,
    name: "Sakura Sushi Bar",
    image: "https://via.placeholder.com/300x200",
    rating: 4.5,
    averagePrice: 150,
    location: "789 Sakura Avenue",
    contactInfo: "987-654-3210",
    businessHours: "11:00 AM - 9:00 PM",
    style: ["Japanese", "Sushi"],
    menuDishes: [
      {
        id: 201,
        name: "Salmon Sushi",
        image: "https://via.placeholder.com/300x200",
        rating: 4.8
      },
      {
        id: 202,
        name: "Tuna Roll",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 203,
        name: "Miso Soup",
        image: "https://via.placeholder.com/300x200",
        rating: 4.5
      },
      {
        id: 204,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 205,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      }
    ]
  },
  {
    id: 3,
    name: "Pasta Palace",
    image: "https://via.placeholder.com/300x200",
    rating: 4.6,
    averagePrice: 80,
    location: "456 Pasta Blvd",
    contactInfo: "123-789-4560",
    businessHours: "10:00 AM - 11:00 PM",
    style: ["Italian", "Pasta"],
    menuDishes: [
      {
        id: 301,
        name: "Spaghetti Carbonara",
        image: "https://via.placeholder.com/300x200",
        rating: 4.9
      },
      {
        id: 302,
        name: "Lasagna",
        image: "https://via.placeholder.com/300x200",
        rating: 4.8
      },
      {
        id: 303,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 304,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      },
      {
        id: 305,
        name: "Fettuccine Alfredo",
        image: "https://via.placeholder.com/300x200",
        rating: 4.7
      }
    ]
  }
];


const currentMerchant = computed(() => {
  return merchants.find(merchant => merchant.id === props.merchantId);
});


const sortedMenuDishes = computed(() => {
  const currentMerchant = merchants.find(merchant => merchant.id === props.merchantId);
  if (!currentMerchant || !currentMerchant.menuDishes) {
    return [];
  }
  
  return currentMerchant.menuDishes.slice().sort((a, b) => b.rating - a.rating);
});
</script>

<template>
  <div v-if="currentMerchant" class="merchant-container">
    <div class="merchant-header">
      <svg @click="goBack" xmlns="http://www.w3.org/2000/svg" class="back-arrow" viewBox="0 0 24 24">
        <path d="M15 18l-6-6 6-6" stroke="currentColor" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round" />
      </svg>
      <img :src="currentMerchant.image" alt="商家图片" class="merchant-image" />
    </div>
    <!-- <p>123</p> -->
    <!-- 商家信息 -->
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
          v-for="dish in sortedMenuDishes"
          :key="dish.id"
          class="dish-item"
        >
          <img :src="dish.image" alt="菜品图片" class="dish-image" />
          <div class="dish-info">
            <h4 class="dish-name">{{ dish.name }}</h4>
            <p class="dish-rating">{{ dish.rating }}分</p>
          </div>
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

</style>