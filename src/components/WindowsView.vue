<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import MerchantView from './MerchantView.vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
const router = useRouter()
const merchants = ref([])

const fetchMerchants = async () => {
  try {
    const response = await axios.get('http://121.40.208.74:8081/Home')
    merchants.value = response.data
    // merchants.value.forEach(merchant => {
    //   if (merchant.images) {
    //     console.log(merchant.images)
    //     const byteCharacters = atob(merchant.images); // 将 base64 字符串转换为字节
    //     const byteNumbers = new Uint8Array(byteCharacters.length);
        
    //     for (let i = 0; i < byteCharacters.length; i++) {
    //       byteNumbers[i] = byteCharacters.charCodeAt(i);
    //     }
        
    //     const blob = new Blob([byteNumbers], { type: 'image/jpeg' }); // 根据实际情况调整 MIME 类型
    //     merchant.imageUrl = URL.createObjectURL(blob); // 生成 URL
    //     console.log(merchant.imageUrl)
    //   }
    // });
    console.log(merchants.value)
  } catch (error) {
    console.error('Error fetching merchants:', error)
  }
}

onMounted(() => {
  fetchMerchants()
})

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
</style>
