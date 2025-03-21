import * as FetchUtil from '../utils/FetchUtil';
import * as ReqwestUtil from '../utils/ReqwestUtil';
import * as CommonUtil from '../utils/CommonUtil';

export function create(orderInfo) {
  return FetchUtil.request({
    url: '/exchange-web/payOrder/create.action', 
    headers: {
      // 将数据编码为表单模式, Form提交
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    // Form提交的数据必须为JS对象，不可是字符串
    body: CommonUtil.serialize(orderInfo)
  });
}

export function pay(orderInfo) {
  return FetchUtil.request({
    url: '/exchange-web/payOrder/pay.action', 
    headers: {
      'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: CommonUtil.serialize(orderInfo)
  });
}
