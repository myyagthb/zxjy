const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 443,
    allowedHosts: "all",
    client: {
      overlay: false,
    },
    proxy: {
      '/storage': {
        target: 'http://localhost:5000', // 替换为实际的第三方服务器地址
        // target: 'https://hxydt.com/storage/v1/*', // 替换为实际的第三方服务器地址
        changeOrigin: true,
        pathRewrite: { '^/storage': '' },
        onProxyRes: (proxyRes, req, res) => {
          console.log(`[Proxy] ${req.method} ${req.url} -> ${res.statusCode}`);
        },
        logLevel: 'debug'
      },
      '/backend': {
        target: 'http://localhost:8888', // 第三方服务器2
        // target: process.env.VUE_APP_SERVER, // 使用环境变量, // 第三方服务器2
        changeOrigin: true,
        pathRewrite: { '^/backend': '' },
        onProxyRes: (proxyRes, req, res) => {
          console.log(`[Proxy] ${req.method} ${req.url} -> ${res.statusCode}`);
        },
        logLevel: 'debug'
      },
    }
  },
})

