
const useSessionStorage = {
    getter: function (key: string) {
        return JSON.parse(sessionStorage.getItem(key) || '{}')
    },
    setter: function (key: string, value: any) {
        return sessionStorage.setItem(key, JSON.stringify(value))
    },
    remove: function (key: string) {
        sessionStorage.removeItem(key)
    },
    clean: function () {
        sessionStorage.clear()
    }
}

export default useSessionStorage;