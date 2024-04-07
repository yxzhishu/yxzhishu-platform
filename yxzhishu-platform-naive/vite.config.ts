import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { NaiveUiResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),
  AutoImport({
    resolvers: [],
    // 自定引入 Vue VueRouter API,如果还需要其他的可以自行引入
    imports: [{
      'naive-ui': [
        'useDialog',
        'useMessage',
        'useNotification',
        'useLoadingBar'
      ]
    }],
    // 调整自动引入的文件位置
    dts: 'src/type/auto-import.d.ts'

  }),
  Components({
    resolvers: [
      //需要自动导入的组件
      NaiveUiResolver()
    ],
    dts: 'src/type/components.d.ts',
  })
  ],
  resolve: {
    //别名配置：./src路径可直接使用@表示
    alias: {
      '@': resolve('./src'),
    },
    // 使用路径别名时想要省略的后缀名
    extensions: ['.js', '.json', '.ts', '.vue'],
  },
  server: {
    //自动打开浏览器
    open: true,
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
