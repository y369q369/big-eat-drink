<template>
    <div style="width: 210px; float: left; height: 100%; background: #151f29; color: #ffffff">
        <Sidebar></Sidebar>
    </div>
    <div style="width: calc(100% - 210px); float: left">
        <Navbar></Navbar>
    </div>
    <div>
        <router-view />
    </div>
</template>

<script setup lang="ts">
import Sidebar from "./Sidebar.vue";
import Navbar from "./Navbar.vue";
</script>

<style scoped>

</style>