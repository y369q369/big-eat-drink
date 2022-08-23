import {defineStore} from "pinia";

export const localStore = defineStore({
    id: "local",
    state: () => ({
        token: localStorage.getItem('token'),
    }),
    actions: {
        storeToken(data: string) {
            this.token = data
            localStorage.setItem('token', data)
        },
    },
});

export const routeStore = defineStore({
    id: "route",
    state: () => ({
        access: '',
    }),
});