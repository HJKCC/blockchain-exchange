import reqwest from 'reqwest';
import { message } from 'antd';
import router from 'umi/router';

import * as CommonUtil from './CommonUtil';

let defData = {
  obj: '',
  rows: [],
  total: 0
};

function checkCode(data) {
  if (data.code == 1) {
    return data;
  } else if (data.code == 6) {  // code：6  表示未登录
    localStorage.clear();
    message.warn(data.info, 1);
    data = defData;
    router.push('/login');
  } else {
    message.error(data.info, 1);
    data = defData;
  }
  return data;
}

function error(e) {
  message.error(`${e.statusText}(${e.status})`, 1);
  return defData;
}

export function request(options) {
  const {method} = options;

  options.type = 'json';
  if (CommonUtil.isEmpty(method)) {
    options.method = 'POST';  // 默认请求方式
  }

  return reqwest(options)
    .then(checkCode)
    .fail(error);
}
