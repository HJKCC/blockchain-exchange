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
  return FetchUtil.hbRequest({
    url: '/market/detail/merged?symbol=' + symbol,
    method: 'GET',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}

export function kline() {
  return FetchUtil.request({
    url: '/exchange-web/hbdm/market/history/kline.action?period=15min&size=200&symbol=BTC_CQ',
    method: 'GET',
    headers: {
      'Content-Type':'application/json;charset=UTF-8'
    }
  });
}
