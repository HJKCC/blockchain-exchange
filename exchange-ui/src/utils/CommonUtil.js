// value为空时，界面显示"--"
export function formatNull(str) {
  if (typeof(str) == 'undefined' || str == '') {
    return '--'
  } else {
    return str;
  }
}

// this_week:当周 next_week:下周 quarter:季度
export function contractType(str) {
  let type = '当周';
  if (str == 'next_week') {
    type = '下周';
  } else if (str == 'quarter') {
    type = '季度';
  }
  return type;
}

// this_week:当周 next_week:下周 quarter:季度
export function getSymbol(symbol, contractType) {
  let tail = '_CW';
  if (contractType == 'next_week') {
    tail = '_NW';
  } else if (contractType == 'quarter') {
    tail = '_CQ';
  }
  return symbol + tail;
}

export function isEmpty(str) {
  if (typeof(str) == 'undefined' || str == '') {
    return true;
  } else {
    return false;
  }
}

// 转obj 为 a='a' & b='b' & ...
export function serialize(obj) {
  var str = [];
  for(var p in obj)
    if (obj.hasOwnProperty(p)) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    }
  return str.join("&");
}
