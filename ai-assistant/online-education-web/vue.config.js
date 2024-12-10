const { defineConfig } = require('@vue/cli-service')
var path = require('path');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 443,
    allowedHosts: "all",
    client: {
      overlay: false,
    },
    // overlay: {
    //   warnings: false,
    //   errors: false
    // },
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
      '/api/backend': {
        target: 'http://localhost:8889', // 第三方服务器2
        // target: process.env.VUE_APP_SERVER, // 使用环境变量, // 第三方服务器2
        changeOrigin: true,
        pathRewrite: { '^/api/backend': '' },
        onProxyRes: (proxyRes, req, res) => {
          console.log(`[Proxy] ${req.method} ${req.url} -> ${res.statusCode}`);
        },
        logLevel: 'debug'
      },
    }
  },


  configureWebpack: {
    resolve: { 
      extensions: [".ts", ".tsx", ".js", ".json", ".vue"] ,
      alias: {
        "@pt/*": path.resolve(__dirname, '/root/autodl-tmp/hxy/platform/pt/*'),
        "supabase/*": path.resolve(__dirname, '../../../platform/pt/*'),
        "*": path.resolve(__dirname, '../../../platform/pt/apps/studio/*'),
        "@/*": path.resolve(__dirname, './src/*')
      },
      fallback: {
        "fs": false,
        "tls": false,
        "net": false,
        "path": false,
        "zlib": false,
        "http": false,
        "https": false,
        "stream": false,
        "crypto": false,
        // "crypto-browserify": require.resolve('crypto-browserify'), //if you want to use this module also don't forget npm i crypto-browserify 
      },
      modules:[path.resolve(__dirname, '../../../platform/pt/*')], // Path to external module],
      // resolveLoader: {
      //   modules: ['/root/autodl-tmp/hxy/platform/pt/*']
      // } 
    },
    module: {
      rules: [
        {
          test: /\.tsx?$/,
          loader: 'ts-loader',
          exclude: /node_modules/,
          options: {
            appendTsSuffixTo: [/\.vue$/],
          }
        },
        {
          test: /\.d\.ts$/,
          loader: 'ignore-loader',
        },        
      ]
    }
  }


  // configureWebpack: {
  //   resolve: { 
  //     fallback: {
  //       "fs": false,
  //       "tls": false,
  //       "net": false,
  //       "path": false,
  //       "zlib": false,
  //       "http": false,
  //       "https": false,
  //       "stream": false,
  //       "crypto": false,
  //       // "crypto-browserify": require.resolve('crypto-browserify'), //if you want to use this module also don't forget npm i crypto-browserify 
  //     } 
  //   },
  // }


})

