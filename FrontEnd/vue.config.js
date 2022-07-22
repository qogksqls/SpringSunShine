const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    proxy: {
      '/':{
        "target":'http://localhost:8181',
        "pathRewrite":{'^/':''},
        "changeOrigin":true,
        "secure":false,
        ws:false,
      }
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
  
})
