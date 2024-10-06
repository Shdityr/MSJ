<script setup lang="ts">
import { ref } from 'vue'
import WindowsView from './WindowsView.vue'
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
    <WindowsView />
  </div>
</template>

<style scoped>
.map-container {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

.map-svg {
  width: 100%;
  height: auto;
  cursor: grab;
}
</style>
