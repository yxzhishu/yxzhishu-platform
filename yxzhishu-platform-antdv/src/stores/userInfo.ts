import { defineStore } from 'pinia'
import useSessionStorage from '../utils/useSessionStorage'

export const useUserInfoStore = defineStore('userInfo', {
    state: () => {
        return {
            user: useSessionStorage.getter('user'),
        }
    },
    actions: {
        setUser(data: JSON) {
            this.user = data;
            useSessionStorage.setter('user', data);
        },
        cleanUser() {
            this.user = null;
            useSessionStorage.clean();
        },
        switchRoles(token: string, deptNmae: string, roleName: string) {
            this.user.token = token
            this.user.deptName = deptNmae
            this.user.roleName = roleName
            useSessionStorage.setter("user", this.user);
        }
    }
})