<template>
  <!-- <div> -->
  <!-- <button @click="toggleAddMarker" class="toggle-button">
      {{ canAddMarker ? '停止添加点' : '添加点' }}
    </button> -->
  <!-- <div class="info" id="text">
    请用鼠标在地图上操作试试
  </div>
  <div class="input-card" style="width:16rem">
    <h4>地图点击相关事件</h4>
    <div>
      <div class="input-item">
        <button id="clickOn" class="btn" style="margin-right:1rem;">绑定事件</button>
        <button id="clickOff" class="btn">解绑事件</button>
      </div>
    </div>
  </div> -->
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

const selectMerchant = (merchantId) => {
  router.push({ name: 'merchant', params: { merchantId: merchantId } })
}

function haversine(lat1, lon1, lat2, lon2) {
  const R = 6371; // 地球半径，单位为公里
  const dLat = (lat2 - lat1) * (Math.PI / 180);
  const dLon = (lon2 - lon1) * (Math.PI / 180);

  const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c * 1000; // 返回单位为米的距离
}

function findNearestMerchant(clickedPosition) {
    let nearestMerchant = null;
    let minDistance = Infinity;
    merchants.value.forEach((merchant) => {
        let distance = haversine(
            clickedPosition.getLng(),clickedPosition.getLat(), 
            merchant.location_x + 114, merchant.location_y + 30
        );
        if (distance < minDistance && distance < 30) {
            minDistance = distance;
            nearestMerchant = merchant;
        }
    });
    return nearestMerchant;
}

function showInfoClick(e) {
    let clickedPosition = e.lnglat;
    let nearestMerchant = null;
    nearestMerchant = findNearestMerchant(clickedPosition);
    if(nearestMerchant != null) {
      selectMerchant(nearestMerchant.id);
      console.log(nearestMerchant.id);
    }
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

      if (navigator.geolocation) {
        // 调用浏览器定位功能
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const { latitude, longitude } = position.coords;

                // 打印用户位置信息
                console.log(`用户位置：经度 ${longitude}, 纬度 ${latitude}`);

                // 在高德地图上标记用户位置
                new AMap.Marker({
                    position: [longitude, latitude],
                    map: map,
                    title: '您的位置'
                });

                // 设置地图中心为用户位置
                map.setCenter([longitude, latitude]);
            },
            (error) => {
                console.error('定位失败:', error.message);
                alert('定位失败，请检查设备定位权限或网络连接。');
            },
            {
                enableHighAccuracy: true, // 启用高精度定位
                timeout: 10000,          // 超时时间
                maximumAge: 0            // 不使用缓存
            }
        );
    } else {
        alert('您的浏览器不支持定位功能，请更换设备或浏览器。');
    }


      // fetchMerchants()
      // // 添加所有初始点
      // merchants.value.forEach((merchant) => {
      //   addMarker(merchant)
      // })

      // const marker1 = new AMap.Marker({
      //   icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
      //   position: [114.36432501897332, 30.535764663035046], // 基于偏移值计算实际位置
      //   title: 'testmarker' // 显示商家名称
      // })
      // marker1.setMap(map)

      fetchMerchants().then(() => {
        merchants.value.forEach((merchant) => {
          const marker = new AMap.Marker({
            icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
            position: [114 + merchant.location_x, 30 + merchant.location_y], // 基于偏移值计算实际位置
            title: merchant.name // 显示商家名称
          });
          marker.setMap(map); // 将标记添加到地图上
        });
      });

      map.on('click', showInfoClick);

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
  height: 100vh;
  /* 设置全屏高度 */
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
