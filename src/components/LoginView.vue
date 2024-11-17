<template>
  <div class="login-container">
    <div class="login-card">
      <h1 class="login-title">欢迎来到“美食珈”！</h1>
      <input
        v-model="username"
        class="login-input"
        type="text"
        placeholder="用户名"
      />
      <input
        v-model="password"
        class="login-input"
        type="password"
        placeholder="密码"
      />
      <button @click="login" class="login-button">登录</button>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5; 
}

.login-card {
  width: 100%;
  max-width: 400px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  text-align: center;
}

.login-title {
  font-size: 24px;
  margin-bottom: 20px;
  color: #333333;
  font-weight: bold;
}

.login-input {
  width: 100%;
  padding: 12px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

.login-input:focus {
  outline: none;
  border-color: #007bff; /* 类似于 Twitter 的蓝色 */
  box-shadow: 0 0 4px rgba(0, 123, 255, 0.3);
}

.login-button {
  width: 100%;
  padding: 12px;
  margin-top: 10px;
  background-color: #007bff; /* Twitter 蓝色 */
  border: none;
  border-radius: 4px;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #0056b3;
}

.login-button:focus {
  outline: none;
}
</style>


<script>
export default {
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      // 简单的登录验证逻辑，你可以替换为实际的 API 调用
      if (this.username && this.password) {
        // 假设登录成功，将状态存储在 localStorage 中
        localStorage.setItem('isLoggedIn', 'true');
        this.$router.push('/user'); // 登录成功后跳转到用户页面
      } else {
        alert('请输入用户名和密码');
      }
    },
  },
};
</script>
