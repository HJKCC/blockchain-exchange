package com.cc.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付单主表
 *
 * @author chencheng0816@gmail.com
 * @date 2019/09/04
 */
public class PayOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增编号
     */
    private Long id;

    /**
     * 第三方交易号，商户订单号
     */
    private String outTradeNo;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 渠道编码
     */
    private String appId;

    /**
     * 商品订单号
     */
    private String orderNo;

    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 订单金额
     */
    private BigDecimal orderFee;

    /**
     * 支付截止时间
     */
    private Date payEndTime;

    /**
     * 支付单状态:PENDING_PAY,PAY_SUCCESS,PAY_FAIL,PAY_PROGRESSING,CLOSED,REFUND_PROGRESSING, REFUND_SUCCESS,REFUND_FAIL,UNKNOWN
     */
    private String orderStatus;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付账号编号
     */
    private Integer payAccountId;

    /**
     * 交易类型:PC, H5, APP, JSAPI，SMARTAPP
     */
    private String tradeType;

    /**
     * 订单备注
     */
    private String memo;

    /**
     * 支付渠道:WX,ALIPAY
     */
    private String payChannel;

    /**
     * 更新人
     */
    private Integer modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(BigDecimal orderFee) {
        this.orderFee = orderFee;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", appId=").append(appId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", orderFee=").append(orderFee);
        sb.append(", payEndTime=").append(payEndTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payTime=").append(payTime);
        sb.append(", payAccountId=").append(payAccountId);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", memo=").append(memo);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", modifier=").append(modifier);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}