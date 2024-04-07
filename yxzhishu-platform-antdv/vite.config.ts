import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    //别名配置
    alias: {
      //./src路径直接用@标识,resolve:	npm install @types/node --save-dev
      '@': resolve('./src'),
    },
    // 使用路径别名时想要省略的后缀名
    extensions: ['.js', '.json', '.ts', '.vue'],
  },
  server: {
    //自动打开浏览器
    open: true,
    //指定服务器应该监听哪个IP地址，默认'localhost'，如果将此设置为 0.0.0.0 或者 true 将监听所有地址，包括局域网和公网地址。
    host: '0.0.0.0',
    //反向代理
    proxy: {
      '/api': {
        //后台地址
        target: 'http://127.0.0.1:8092/api',
        //允许跨域
        changeOrigin: true,
        //去掉请求路径中识别用的/api字符串
        rewrite: path => path.replace(/^\/api/, ''),
      }
    }
  }
})
