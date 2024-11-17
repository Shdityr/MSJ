<script setup lang="ts">
import WindowsView from '../components/WindowsView.vue'
import MapView from '../components/MapView.vue'
</script>

<template>
  <div class="windows">
    <WindowsView />
    <div class="footer-buttons">
      <router-link to="/" class="button">首页</router-link>
      <router-link to="/user" class="button">个人中心</router-link>
    </div>
  </div>
  <MapView />
</template>

<style scoped>
.windows {
  position: relative; /* 修改为相对定位 */
  width: 400px;
  height: 90vh;
  overflow-y: auto; /* 保持内容可以滚动 */
  background-color: #ffffff;
  padding: 15px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  opacity: 0.95; /* 增加一点透明感 */
  backdrop-filter: blur(5px); /* 可选：添加背景模糊效果 */
}

.footer-buttons {
  position: absolute; /* 修改为绝对定位 */
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
