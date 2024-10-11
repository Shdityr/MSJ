<script setup lang="ts">
import ReviewView from '../components/ReviewView.vue'
import MapView from '../components/MapView.vue'
</script>

<template>
  <div class="windows">
    <ReviewView />
  </div>
  <MapView />
</template>

<style scoped>
.windows {
  position: fixed;
  top: 20px;
  left: 20px;
  width: 400px;
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
</style>
