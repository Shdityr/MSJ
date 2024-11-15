<template>
  <div>
    <button @click="toggleAddMarker" class="toggle-button">
      {{ canAddMarker ? '停止添加点' : '添加点' }}
    </button>
    <div id="containerMap" style="width: 100%; height: 500px"></div>

    <!-- 模态框 -->
    <div v-if="isModalVisible" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>编辑点信息</h2>
        <input v-model="currentPoint.name" placeholder="点名" />
        <input v-model="currentPoint.image" placeholder="图片路径" />
        <button @click="savePoint">确认</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import AMapLoader from '@amap/amap-jsapi-loader' //Gaode api

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

onMounted(() => {
  fetchMerchants()
})

const points = [
  {
    name: '点1',
    position: [114.36432501897332, 30.535764663035046],
    image: '../assets/icon1.png'
  },
  {
    name: '点2',
    position: [114.365, 30.536],
    image: '../assets/icon1.png'
  }
]

function addMarker(point) {
  const marker = new AMap.Marker({
    position: new AMap.LngLat(point.position[0], point.position[1]),
    content: `
      <div style="text-align: center;">
        <img src="${point.image}" alt="${point.name}" style="width: 50px; height: 50px; border-radius: 5px;"/>
        <p>${point.name}</p>
      </div>
    `
  })
  marker.setMap(map)

  marker.on('click', () => {
    currentPoint.value = { ...point } // 复制点信息
    isModalVisible.value = true // 显示模态框
  })
}

function savePoint() {
  const index = points.findIndex(
    (p) =>
      p.position[0] === currentPoint.value.position[0] &&
      p.position[1] === currentPoint.value.position[1]
  )
  if (index !== -1) {
    points[index] = { ...currentPoint.value }
    // 更新地图上的标记内容
    // map.remove(marker) // 移除旧标记
    addMarker(points[index]) // 重新添加标记以更新内容
  }
  closeModal()
}

function closeModal() {
  isModalVisible.value = false
}

function toggleAddMarker() {
  canAddMarker.value = !canAddMarker.value // 切换添加点的状态
}

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

      // 点击事件，添加点
      map.on('click', (e) => {
        if (canAddMarker.value) {
          const newPoint = {
            name: '新点',
            position: [e.lnglat.getLng(), e.lnglat.getLat()],
            image: '../assets/icon1.png' // 默认图片路径
          }
          points.push(newPoint) // 添加新点到数组
          addMarker(newPoint) // 添加标记到地图
        }
      })

      // 添加所有初始点
      points.forEach((point) => {
        addMarker(point)
      })
    })
    .catch((e) => {
      console.error(e)
    })
})

onUnmounted(() => {
  map?.destroy()
})
</script>

<style>
/* 模态框样式 */
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
