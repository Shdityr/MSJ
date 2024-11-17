<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import type { MerchantInfo } from './datatype'
import { convertBase64ToImageUrl } from './Utils';
const router = useRouter()
const merchants = ref<MerchantInfo[]>([])

const fetchMerchants = async () => {
  try {
    const response = await axios.get('http://121.40.208.74:8081/Home');
    merchants.value = response.data;
    
    merchants.value.forEach((merchant) => {
      merchant.images.forEach((image, index) => {
        merchant.images[index] = convertBase64ToImageUrl(image);
      });
    });

    console.log(merchants.value);
  } catch (error) {
    console.error('Error fetching merchants:', error);
  }
};

onMounted(() => {
  fetchMerchants()
})
function goHome() {
  router.push({ name: 'Home' })
}
function goUser() {
  router.push({ name: 'User' })
}
const selectMerchant = (merchantId) => {
  router.push({ name: 'merchant', params: { merchantId: merchantId } })
}
</script>

<template>
  <div v-if="merchants && merchants.length > 0">
    <div
      v-for="merchant in merchants"
      :key="merchant.id"
      class="merchant-item"
      @click="selectMerchant(merchant.id)"
    >
      <img :src="merchant.images[0]" alt="商家图片" class="merchant-image" />
      <div class="merchant-info">
        <h3 class="merchant-name">{{ merchant.name }}</h3>
        <p class="merchant-rating">
          评分: {{ merchant.rating }} | 人均: ¥{{ merchant.averagePrice }}
        </p>
        <p class="merchant-location">{{ merchant.location }}</p>
        <p class="merchant-businessHours">营业时间： {{ merchant.businessHours }}</p>
      </div>
    </div>
  </div>
  <div class="footer-buttons">
      <!-- 使用 router-link 进行路由跳转 -->
      <router-link to="/" class="button">首页</router-link>
      <router-link to="/user" class="button">个人中心</router-link>
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
  width: 70px;
  height: 70px;
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
.merchant-businessHours,
.merchant-location {
  font-size: 14px;
  color: #666;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.footer-buttons {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  display: flex;
  justify-content: space-around;
  padding: 16px;
  background-color: #f2f2f2;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
}

/* 设置按钮样式 */
.button {
  padding: 10px 20px;
  background-color: transparent; /* 透明背景 */
  color: #000; /* 调整文字颜色为黑色或你需要的颜色 */
  text-align: center;
  border: none; /* 移除边框 */
  text-decoration: none;
  font-size: 16px;
}

.button:hover {
  color: #555; /* 悬停时改变文字颜色 */
}
</style>
