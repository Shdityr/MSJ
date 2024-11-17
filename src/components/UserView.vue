<template>
    <div class="user-view-container">
  
      <!-- 主内容区域 -->
      <main class="main-content">
        <header class="header">
          <h1>用户主页</h1>
          <p>欢迎回来，用户！这是您的主页。</p>
        </header>
  
        <!-- 发布新帖模块 -->
        <section class="post-creator">
          <textarea
            v-model="newPost"
            placeholder="在这里发帖讲讲你想告诉大家的事吧~"
            class="post-input"
          ></textarea>
          <button @click="createPost" class="post-button">发布</button>
        </section>
  
        <!-- 帖子列表模块 -->
        <section class="posts">
          <h2 class="closePosts">最近的帖子</h2>
          <div v-for="(post, index) in posts" :key="index" class="post">
            <p>{{ post }}</p>
          </div>
        </section>
      

        <!-- <h2 class="sidebar-title">导航菜单</h2> -->
        <!-- <ul class="menu-list">
          <li>主页</li>
          <li>个人资料</li>
          <li>
            我的消息
          </li>
          <li>设置</li>
        </ul> -->
        <button class="logout-button" @click="logout">登出</button>
        <!-- <button class="home-button" @click="goToHome">回到主页</button>       -->
      
      </main>
          <!-- 侧边栏 -->
      <!-- <aside class="sidebar"> -->
      <!-- </aside> -->
    </div>

  </template>
  
  <script>
  export default {
    data() {
      return {
        newPost: '',
        posts: ['肯德基的塔克系列无疑是一次令人惊喜的体验！鸡肉塔克，外层的饼皮酥脆可口，内里则是嫩滑的鸡肉，搭配上香辣的酱料，每一口都能感受到层次分明的口感和丰富的味道。此外，肯德基在塔克的包装上也下足了功夫，色彩鲜艳、设计独特，不仅便于携带，更增添了几分用餐的乐趣。总之，肯德基的塔克系列是一次值得推荐的美食之旅，无论是寻找新鲜感的食客，还是肯德基的忠实粉丝，都不应错过。', '欢迎来到我的主页！'],
      };
    },
    methods: {
      logout() {
        localStorage.setItem('isLoggedIn', 'false');
        this.$router.push('/login');
      },
      goToHome() {
        this.$router.push('/');
      },
      createPost() {
        if (this.newPost) {
          this.posts.unshift(this.newPost);
          this.newPost = '';
        } else {
          alert('请输入内容后再发布');
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .user-view-container {
    display: flex;
    min-height: 100vh;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  
  .sidebar {
    width: 250px;
    padding: 20px;
    background-color: #f5f5f5;
    border-right: 1px solid #dcdcdc;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  }
  
  .sidebar-title {
    font-size: 20px;
    margin-bottom: 15px;
    color: #333;
  }
  
  .menu-list {
    list-style: none;
    padding: 0;
  }
  
  .menu-list li {
    padding: 12px 10px;
    margin-bottom: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    border-radius: 4px;
  }
  
  .menu-list li:hover {
    background-color: #e8e8e8;
  }
  
  .logout-button {
    margin-top: 20px;
    padding: 12px;
    width: 100%;
    border: none;
    background-color: #ff4d4f;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .logout-button:hover {
    background-color: #d43d3f;
  }
  .home-button {
    margin-top: 20px;
    padding: 12px;
    width: 100%;
    border: none;
    background-color: #0f752d;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .home-button:hover {
    background-color: #0f752d;
  }
  
  .main-content {
    flex: 1;
    padding: 20px 40px;
    background-color: #f9f9f9;
  }
  
  .header {
    margin-bottom: 25px;
  }
  
  .header h1 {
    font-size: 24px;
    margin-bottom: 8px;
    color: #444;
  }
  
  .header p {
    font-size: 16px;
    color: #666;
    margin-top: 8px;
  }
  
  .post-creator {
    margin-bottom: 30px;
    background-color: white;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .post-input {
    width: 100%;
    height: 200px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 6px;
    resize: none;
  }
  
  .post-button {
    margin-top: 10px;
    padding: 12px 24px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .post-button:hover {
    background-color: #0056b3;
  }
  
  .posts {
    
    margin-top: 20px;
  }
  
  .closePosts {
    font-size: 18px;
    margin-bottom: 15px;
    color: #333;
  }
  
  .post {
    padding: 15px;
    margin-bottom: 15px;
    border-radius: 6px;
    border: 1px solid #e0e0e0;
    background-color: white;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  }
  </style>
  