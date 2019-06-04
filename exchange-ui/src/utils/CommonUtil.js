export function formatNull(str) {
  if (typeof(str) == 'undefined' || str == '') {
    return '--'
  } else {
    return str;
  }
}

export function isEmpty(str) {
  if (typeof(str) == 'undefined' || str == '') {
    return true;
  } else {
    return false;
  }
}
