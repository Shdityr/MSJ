<script setup lang="ts">
import DishView from '../components/DishView.vue'
import MapView from '../components/MapView.vue'
</script>

<template>
  <div class="windows">
    <DishView />
    <div class="footer-buttons">
      <router-link to="/" class="button">首页</router-link>
      <router-link to="/user" class="button">个人中心</router-link>
    </div>
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
</style>
