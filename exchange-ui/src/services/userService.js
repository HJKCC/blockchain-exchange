import * as FetchUtil from '../utils/FetchUtil';
import * as ReqwestUtil from '../utils/ReqwestUtil';
import * as CommonUtil from '../utils/CommonUtil';

export function listUsers() {
  return FetchUtil.request({
    url: '/exchange-web/user/list.action',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}

export function removeUsers(ids) {
  return FetchUtil.request({
    url: '/exchange-web/user/deleteUser.action',
    headers: {
      'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: 'ids=' + ids
  });
}

export function addUser(userInfo) {
  return ReqwestUtil.request({
    url: '/exchange-web/user/register.action',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    },
    body: JSON.stringify(userInfo)
  });
}
