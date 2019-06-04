import fetch from 'dva/fetch';
import { message } from 'antd';
import router from 'umi/router';
function checkStatus(response) {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }
  const error = new Error(response.statusText);
  error.response = response;
  throw error;
}

function parseJSON(response) {
  // let contentType = response.headers.get('content-type');
  // if (contentType.includes('application/json')) {
  //   return response.json();
  // } else if (contentType.includes('text/html')) {
  //   return response.text();
  // } else {
  //   throw new Error(`Sorry, content-type ${contentType} not supported`);
  // }
  return response.json();
}

//方法一、此函数校验时，若用户登录信息失效，会先进入当前界面，然后再跳入'/login'界面
//方法二、在 model层 effects 中进行数据校验
function checkCode(data) {
  if (data.code == 6) {  // code：6  表示未登录
    localStorage.clear();
    message.success(data.info, 1);
    data.rows = [];
    router.push('/login');
  }
  return data;
}

export function request(options) {
  // const Authorization = localStorage.getItem('access_token')
  const {url} = options;
  options = {...options};
  delete options.url;
  options.method = 'POST';  // 默认请求方式
  options.mode = 'cors';  // 支持跨域请求
  options.credentials = 'include';  //发送包含凭据的请求

  return fetch(url, options)
    .then(checkStatus)
    .then(parseJSON)
    .then(checkCode)
    .catch(err=>({err}));
}
