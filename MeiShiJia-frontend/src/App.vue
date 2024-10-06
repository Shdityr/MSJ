<script setup lang="ts">
</script>

<template>
  <div>
    <!-- <header>
      <h1>我的地图应用</h1>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/map">Map</RouterLink>
        <RouterLink to="/windows">Windows</RouterLink>
      </nav>
    </header> -->
    <RouterView />
  </div>
</template>


<style scoped>
header {
  background: #f5f5f5;
  padding: 20px;
  text-align: center;
}

nav {
  margin: 20px 0;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #42b983;
}
</style>