import * as FetchUtil from '../utils/FetchUtil';
import * as ReqwestUtil from '../utils/ReqwestUtil';

export function listProducts() {
  return FetchUtil.request({
    url: '/exchange-web/product/list.action',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}

export function removeProducts(ids) {
  return FetchUtil.request({
    url: '/exchange-web/product/deleteProduct.action',
    headers: {
      'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: 'ids=' + ids
  });
}
