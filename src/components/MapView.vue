<template>
   <!-- <div> -->
    <!-- <button @click="toggleAddMarker" class="toggle-button">
      {{ canAddMarker ? '停止添加点' : '添加点' }}
    </button> -->
    <div id="containerMap" style="width: 100%; height: 100vh"></div>

</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader' //Gaode api

import type { MerchantInfo } from './datatype'
import { convertBase64ToImageUrl } from './Utils';
import { useRouter } from 'vue-router'
import axios from 'axios'
const router = useRouter()
const merchants = ref([])

let map = null
let AMap = null
const canAddMarker = ref(false)
const isModalVisible = ref(false)
const currentPoint = ref({ name: '', image: '', position: [] })

const fetchMerchants = async () => {
  try {
    const response = await axios.get('http://121.40.208.74:8081/Home')
    merchants.value = response.data
    merchants.value.forEach((merchant) => {
      if (merchant.images) {
        console.log(merchant.images)
        const byteCharacters = atob(merchant.images) // 将 base64 字符串转换为字节
        const byteNumbers = new Uint8Array(byteCharacters.length)

        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i)
        }

        const blob = new Blob([byteNumbers], { type: 'image/jpeg' }) // 根据实际情况调整 MIME 类型
        merchant.imageUrl = URL.createObjectURL(blob) // 生成 URL
        console.log(merchant.imageUrl)
      }
    })
    console.log(merchants.value)
  } catch (error) {
    console.error('Error fetching merchants:', error)
  }
}

// const points = [
//   {
//     name: '点1',
//     position: [114.36432501897332, 30.535764663035046],
//     image: '../assets/icon1.png'
//   },
//   {
//     name: '点2',
//     position: [114.365, 30.536],
//     image: '../assets/icon1.png'
//   }
// ]

const addMarker = (merchant) => {
  if (map) {
    const marker = new AMap.Marker({
      position: [114 + merchant.location_x, 30 + merchant.location_y], // 基于偏移值计算实际位置
      title: merchant.name // 显示商家名称
    })
    console.log(`Added marker for ${merchant.name} at ${merchant.location_x}, ${merchant.location_y}`)
  } else {
    console.warn('Map is not initialized yet!')
  }
  });

  // 添加自定义内容（如图片）
  // if (merchant.image) {
  //   marker.setContent(`
  //     <div style="text-align: center;">
  //       <img src="${merchant.image}" alt="${merchant.name}" style="width: 50px; height: 50px; border-radius: 5px;" />
  //       <p>${merchant.name}</p>
  //     </div>
  //   `);
  // }

  marker.setMap(map); // 将标记添加到地图上
  console.log(`Added marker for ${merchant.name} at ${merchant.location_x}, ${merchant.location_y}`)
};

onMounted(() => {
  AMapLoader.load({
    key: '2f70ab6d3da02f752e9ad66585d479a8',
    version: '2.0',
    plugins: ['AMap.ToolBar', 'AMap.Scale', 'AMap.Geolocation']
  })
    .then((AMap) => {
      map = new AMap.Map('containerMap', {
        viewMode: '3D',
        zoom: 15,
        center: [114.36432501897332, 30.535764663035046],
        resizeEnable: true
      })

      const toolbar = new AMap.ToolBar()
      map.addControl(toolbar)

      const scale = new AMap.Scale()
      map.addControl(scale)

      const geolocation = new AMap.Geolocation()
      map.addControl(geolocation)

      await fetchMerchants()
      // 添加所有初始点
      merchants.value.forEach((merchant) => {
        addMarker(merchant)
      })
      // fetchMerchants().then(() => {
      //   merchants.value.forEach((merchant) => {
      //     addMarker(merchant); // 遍历每个商家并添加到地图
      //   });
      // });
    })
    .catch((e) => {
      console.error(e)
    })
})

  onUnmounted(() => {
    map.destroy();
})
</script>

<style>
/* 模态框样式 */
#mapContainer {
  width: 100%;
  height: 100vh; /* 设置全屏高度 */
}
.modal {
  display: flex;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.close {
  cursor: pointer;
  float: right;
}
</style>
