<script setup lang="ts">
import { ref } from 'vue'

const scale = ref(1)
const translateX = ref(0)
const translateY = ref(0)
const isDragging = ref(false)
const lastMousePosition = ref({ x: 0, y: 0 })

// SVG 文件路径
const svgPath = new URL('../assets/campus-map.svg', import.meta.url).href

const zoomMap = (event: WheelEvent) => {
  event.preventDefault()
  const zoomFactor = event.deltaY < 0 ? 1.1 : 0.9 // 放大或缩小
  scale.value *= zoomFactor
}

const startDrag = (event: MouseEvent) => {
  isDragging.value = true
  lastMousePosition.value = { x: event.clientX, y: event.clientY }
}

const drag = (event: MouseEvent) => {
  if (!isDragging.value) return

  const dx = event.clientX - lastMousePosition.value.x
  const dy = event.clientY - lastMousePosition.value.y

  translateX.value += dx
  translateY.value += dy

  lastMousePosition.value = { x: event.clientX, y: event.clientY }
}

const endDrag = () => {
  isDragging.value = false
}
</script>

<template>
  <div
    class="map-container"
    @wheel="zoomMap"
    @mousedown="startDrag"
    @mousemove="drag"
    @mouseup="endDrag"
  >
    <img
      :src="svgPath"
      alt="地图"
      class="map-svg"
      :style="{ transform: `scale(${scale}) translate(${translateX}px, ${translateY}px)` }"
    />
    <div class="info-box">
      <h3>信息窗口</h3>
      <p>这里是一些信息。</p>
    </div>
  </div>
</template>

<style scoped>
.map-container {
  position: relative;
  width: 100vw; /* 全宽 */
  height: 100vh; /* 全高 */
  overflow: hidden; /* 隐藏溢出内容 */
}

.map-svg {
  width: 100%; /* 自适应宽度 */
  height: auto; /* 保持比例 */
  cursor: grab; /* 拖动光标 */
}

.info-box {
  position: absolute;
  top: 20px;
  left: 20px;
  background: rgba(255, 255, 255, 0.8);
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}
</style>
