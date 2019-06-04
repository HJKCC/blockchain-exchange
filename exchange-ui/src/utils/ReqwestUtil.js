import reqwest from 'reqwest';
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
  const {success} = options;
  const {error} = options;
  options = {...options};
  delete options.success;
  delete options.error;

  options.type = 'json';
  options.method = 'POST';  // 默认请求方式

  return reqwest(options)
    .then(checkCode)
    .fail(
      (e, msg) => {
          message.error('网络出现故障，请稍后再试！', 1);
      }
    );
}
