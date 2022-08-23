<template>
    <el-menu
        active-text-color="#ffd04b"
        background-color="#151f29"
        class="el-menu-vertical-demo"
        text-color="#fff"
        router
        :collapse="isCollapse"
    >
        <sidebar-item v-for="route in routes" :key="route.path" :route="route"></sidebar-item>
    </el-menu>
</template>

<script setup lang="ts">
import SidebarItem from "@/views/layout/SidebarItem.vue";
import { useRouter } from "vue-router";
import {ref, watch} from "vue";
import {localStore} from "@/stores/local";

const isCollapse = ref(false)

const routes = useRouter().options.routes

const store = localStore()

watch( () => store.sidebarShowFlag, (flag) => {
    isCollapse.value = flag
})

</script>

<style scoped>

</style>