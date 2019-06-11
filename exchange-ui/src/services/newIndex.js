import * as FetchUtil from '../utils/FetchUtil';
import * as ReqwestUtil from '../utils/ReqwestUtil';

export function listContracts() {
  return FetchUtil.hbRequest({
    url: '/api/v1/contract_contract_info',
    method: 'GET',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}

export function merged(symbol) {
  let data = FetchUtil.hbRequest({
    url: '/market/detail/merged?symbol=' + symbol,
    method: 'GET',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });

  return data;
}

export function kline(symbol) {
  return FetchUtil.request({
    url: '/market/history/kline?period=15min&size=200&symbol=' + symbol,
    method: 'GET',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}
