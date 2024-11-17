<script setup lang="ts">
import DishView from '../components/DishView.vue'
import MapView from '../components/MapView.vue'
</script>

<template>
  <div class="app-container">
    <MapView />
    <div class="windows">
      <div class="content">
        <DishView />
      </div>
      <div class="footer-buttons">
        <router-link to="/" class="button">首页</router-link>
        <router-link to="/user" class="button">个人中心</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.app-container {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden; /* 防止整个页面滚动 */
}

MapView {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0; /* 确保MapView在背景层 */
}

.windows {
  position: absolute;
  top: 20px;
  left: 20px;
  width: 400px;
  height: calc(100% - 40px); /* 减去顶部和底部的边距 */
  background-color: #ffffff;
  padding: 15px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  z-index: 10; /* 确保在上层 */
  opacity: 0.95;
  backdrop-filter: blur(5px);
  overflow: hidden; /* 防止内容溢出 */
}

.content {
  height: calc(100% - 88px); /* 减去底部按钮的高度和内边距 */
  overflow-y: auto; /* 允许内容滚动 */
  padding-bottom: 16px; /* 与底部按钮的间距 */
}

.footer-buttons {
  position: absolute;
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