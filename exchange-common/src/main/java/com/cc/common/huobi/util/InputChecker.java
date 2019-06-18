package com.cc.common.huobi.util;

import com.cc.common.huobi.exception.HuobiApiException;
import com.cc.common.util.CommonUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

  private static final String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\t";

  private static final InputChecker checkerInst;

  static {
    checkerInst = new InputChecker();
  }

  public static InputChecker checker() {
    return checkerInst;
  }

  private boolean isSpecialChar(String str) {

    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }

  public <T> InputChecker shouldNotNull(T value, String name) {
    if (value == null) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + name + " should not be null");
    }
    return checkerInst;
  }

  public <T> InputChecker shouldNull(T value, String name) {
    if (value != null) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + name + " should be null");
    }
    return checkerInst;
  }

  public InputChecker checkSymbol(String symbol) {
    if (symbol == null || "".equals(symbol)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] Symbol is mandatory");
    }
    if (isSpecialChar(symbol)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + symbol + " is invalid symbol");
    }
    return checkerInst;
  }

  public InputChecker checkCurrency(String currency) {
    if (currency == null || "".equals(currency)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] Currency is mandatory");
    }
    if (isSpecialChar(currency)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + currency + " is invalid currency");
    }
    return checkerInst;
  }

  public InputChecker checkNull(String symbol) {
    if (CommonUtil.isEmpty(symbol)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "symbol is null");
    }
    return checkerInst;
  }

  private InputChecker checkRange(int size, int min, int max, String name) {
    if (!(min <= size && size <= max)) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + name + " is out of bound. " + size + " is not in [" + min + "," + max + "]");
    }
    return checkerInst;
  }

  public InputChecker checkSymbolList(List<String> symbols) {
    if (symbols == null || symbols.size() == 0) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR, "[Input] Symbol is mandatory");
    }
    for (String symbol : symbols) {
      checkSymbol(symbol);
    }
    return checkerInst;
  }

  public InputChecker checkRange(Integer size, int min, int max, String name) {
    if (size != null) {
      checkRange(size.intValue(), min, max, name);
    }
    return checkerInst;
  }

  public InputChecker greaterOrEqual(Integer value, int base, String name) {
    if (value != null && value < base) {
      throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
          "[Input] " + name + " should be greater than " + base);
    }
    return checkerInst;
  }

  public <T> InputChecker checkList(List<T> list, int min, int max, String name) {
    if (list != null) {
      if (list.size() > max) {
        throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
            "[Input] " + name + " is out of bound, the max size is " + max);
      } else if (list.size() < min) {
        throw new HuobiApiException(HuobiApiException.INPUT_ERROR,
            "[Input] " + name + " should contain " + min + " item(s) at least");
      }
    }
    return checkerInst;
  }
}
