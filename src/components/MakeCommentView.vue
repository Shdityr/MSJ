<template>
    <!-- 这里是模板部分，定义组件的 HTML 结构 -->
    <div class="example-component">
      <h1>{{ title }}</h1>
      <button @click="handleClick">点击我</button>
    </div>
  </template>
  
  <script>
  // 这里是脚本部分，包含组件的逻辑
  export default {
    name: 'ExampleComponent',
    data() {
      return {
        title: '欢迎来到 Vue 组件示例'
      };
    },
    methods: {
      handleClick() {
        this.title = '按钮已被点击！';
      }
    }
  };
  </script>
  
  <style scoped>
  /* 这里是样式部分，为当前组件定义样式 */
  .example-component {
    font-family: Arial, sans-serif;
    text-align: center;
  }
  
  button {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  </style>
  