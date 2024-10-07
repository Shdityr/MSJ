<!-- src/components/MerchantView.vue -->
<script setup lang="ts">
import { defineProps, computed, defineEmits } from 'vue';

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
]

const currentMerchant = computed(() => {
  return merchants.find(merchant => merchant.id === props.merchantId);
});
</script>

<template>
  <div v-if="currentMerchant">
    <button @click="goBack">返回</button>
    <h2>{{ currentMerchant.name }}</h2>
    <p>Rating: {{ currentMerchant.rating }}</p>
    <p>Average Price: {{ currentMerchant.averagePrice }}</p>
    <p>Location: {{ currentMerchant.location }}</p>
  </div>
  <div v-else>
    <p>商家未找到</p>
  </div>
</template>

<style scoped>
</style>
