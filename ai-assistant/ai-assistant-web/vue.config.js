const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // host: 'localhost',
    port: 443,
    // historyApiFallback: true,
    allowedHosts: "all",
    client: {
      overlay: false,
    },
    proxy: {
      '/storage': {
        target: 'http://localhost:5000', // 替换为实际的第三方服务器地址
        changeOrigin: true,
        pathRewrite: { '^/storage': '' }
      },
      '/backend': {
        target: 'http://localhost:8888', // 第三方服务器2
        changeOrigin: true,
        pathRewrite: { '^/backend': '' }
      },
    }
  }
})
