package com.cc.common.huobi.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/15 15:44
 * @Description ContractVO K 线信息类
 */
public class KLineVO  implements Serializable {

  /**
   * K线id： Get the UNIX formatted timestamp in UTC
   */
  private long id;
  /**
   * 成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
   */
  private double amount;
  /**
   * 成交笔数
   */
  private long count;
  /**
   * 开盘价
   */
  private double open;
  /**
   * 收盘价,当K线为最晚的一根时，是最新成交价
   */
  private double close;
  /**
   * 最低价
   */
  private double low;
  /**
   * 最高价
   */
  private double high;
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

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public long getVol() {
    return vol;
  }

  public void setVol(long vol) {
    this.vol = vol;
  }
}
