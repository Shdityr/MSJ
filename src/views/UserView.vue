<script setup lang="ts">
import UserView from '../components/UserView.vue'
import MapView from '../components/MapView.vue'
</script>


<template>
  <div class="windows">
    <div class="content">
      <UserView />
    </div>
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
  top: 20px;
  left: 20px;
  width: 400px;
  height: 90vh;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
  z-index: 10;
  opacity: 0.95;
  backdrop-filter: blur(5px);
}

.content {
  height: calc(100% - 88px); /* 减去底部按钮的高度和内边距 */
  overflow-y: auto; /* 允许内容滚动 */
  padding-bottom: 16px; /* 与底部按钮的间距 */
}

.footer-buttons {
  position: absolute; /* 改为绝对定位 */
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