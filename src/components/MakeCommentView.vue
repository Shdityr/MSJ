<template>
    <div class="make-comment-view">
      <h2>发表评价</h2>
  
      <form @submit.prevent="submitComment">
        <!-- 评论输入框 -->
        <div class="form-group">
          <label for="comment">评论:</label>
          <textarea
            id="comment"
            v-model="commentText"
            placeholder="写下您的评论..."
            required
          ></textarea>
        </div>
  
        <!-- 评分输入 -->
        <div class="form-group">
          <label for="rating">评分 (1-5):</label>
          <select id="rating" v-model.number="rating">
            <option value="" disabled>请选择评分</option>
            <option v-for="n in 5" :key="n" :value="n">{{ n }}</option>
          </select>
        </div>
  
        <!-- 图片上传 -->
        <div class="form-group">
          <label for="image-upload">上传图片:</label>
          <input
            type="file"
            id="image-upload"
            @change="handleImageUpload"
            accept="image/*"
          />
        </div>
  
        <!-- 提交按钮 -->
        <button type="submit" class="submit-button">提交评论</button>
      </form>
    </div>
  </template>
  
  <script>
import { useRoute } from 'vue-router'; // 引入useRoute

export default {
  name: 'MakeCommentView',
  data() {
    const route = useRoute(); // 获取当前路由
    return {
      commentText: '',
      rating: 0,
      imageFile: null,
      merchantId: route.params.merchantId,
      dishId: route.params.dishId
    };
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0];
      if (file) {
        this.imageFile = file;
      }
    },
    async submitComment() {
      // 创建FormData对象以便上传文件
      const formData = new FormData();

      formData.append('merchantId', this.merchantId); // merchantId
      formData.append('dishId', this.dishId); // dishId
      formData.append('contents', this.commentText); // 评论文本
      formData.append('rating', this.rating); //评分
      if (this.imageFile) {
        formData.append('images', this.imageFile);//图片
      }

      try {
        // 替换为实际的API请求URL
        const response = await fetch('http://121.40.208.74:8081/reviews_insert', {
          method: 'POST',
          body: formData
        });

        if (!response.ok) {
          throw new Error('提交评论失败');
        }

        // 重置表单
        this.commentText = '';
        this.rating = '';
        this.imageFile = null;
        alert('评论提交成功');
      } catch (error) {
        console.error(error);
        alert('评论提交失败');
      }
    }
  }
};
</script>

  
  <style scoped>
.make-comment-view {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background-color: #f9f9f9;
}

.make-comment-view h2 {
  margin-bottom: 20px; /* 添加的空白间距 */
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

textarea,
select,
input[type="file"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-button {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 20px;
}
textarea {
  height: 150px; /* 增大高度 */
}
.submit-button:hover {
  background-color: #0056b3;
}
</style>
