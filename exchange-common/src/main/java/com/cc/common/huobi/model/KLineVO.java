package com.cc.common.huobi.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The candlestick/kline data.
 */
public class KLineVO  implements Serializable {

  /**
   * K线id： Get the UNIX formatted timestamp in UTC
   */
  private long id;
  /**
   * 成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
   */
  private long amount;
  /**
   * 成交笔数
   */
  private long count;
  /**
   * 开盘价
   */
  private long open;
  /**
   * 收盘价,当K线为最晚的一根时，是最新成交价
   */
  private long close;
  /**
   * 最低价
   */
  private long low;
  /**
   * 最高价
   */
  private long high;
  /**
   * 成交量(张)
   */
  private long vol;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getAmount() {
    return amount;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public long getOpen() {
    return open;
  }

  public void setOpen(long open) {
    this.open = open;
  }

  public long getClose() {
    return close;
  }

  public void setClose(long close) {
    this.close = close;
  }

  public long getLow() {
    return low;
  }

  public void setLow(long low) {
    this.low = low;
  }

  public long getHigh() {
    return high;
  }

  public void setHigh(long high) {
    this.high = high;
  }

  public long getVol() {
    return vol;
  }

  public void setVol(long vol) {
    this.vol = vol;
  }
}
