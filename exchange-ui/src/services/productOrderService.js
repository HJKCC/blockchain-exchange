import * as FetchUtil from '../utils/FetchUtil';
import * as ReqwestUtil from '../utils/ReqwestUtil';

export function create(productInfoList) {
  return FetchUtil.request({
    url: '/exchange-web/productOrder/add.action',
    headers: {
      // 数据类型必须为application/x-www-form-urlencoded之外的类型，payload提交
      'Content-Type':'application/json;charset=UTF-8'
    },
    body: JSON.stringify(productInfoList)
  });
}
