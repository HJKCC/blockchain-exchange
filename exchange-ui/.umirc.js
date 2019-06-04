
// ref: https://umijs.org/config/
export default {
  treeShaking: true,
  publicPath: "/exchange-web/webpack/",
  outputPath: "../exchange-web/src/main/webapp/webpack",
  base: "/exchange-web/webpack/",
  plugins: [
    // ref: https://umijs.org/plugin/umi-plugin-react.html
    ['umi-plugin-react', {
      antd: true,
      dva: true,
      dynamicImport: { webpackChunkName: true },
      title: 'exchange-ui',
      dll: true,

      routes: {
        exclude: [
          /models\//,
          /services\//,
          /model\.(t|j)sx?$/,
          /service\.(t|j)sx?$/,
          /components\//,
        ],
      },
    }],
  ],

  proxy: {
         "/exchange-web/": {
            "target": "http://127.0.0.1:8080/",
            "changeOrigin": true
        }
   }
}
