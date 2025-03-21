package com.cc.common.huobi.model;

import java.io.Serializable;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/6/21 15:44
 * @Description ContractVO 合约信息类
 */
public class ContractVO implements Serializable {
	/**
	 * 品种代码，"BTC"
	 */
	private String symbol;
	/**
	 * 合约代码，"BTC180914"
	 */
	private String contractCode;
	/**
	 * 合约类型，当周:"this_week", 次周:"next_week", 季度:"quarter"
	 */
	private String contractType;
	/**
	 * 合约面值，即1张合约对应多少美元
	 */
	private long contractSize;
	/**
	 * 合约价格最小变动精度
	 */
	private double priceTick;
	/**
	 * 合约交割日期
	 */
	private String deliveryDate;
	/**
	 * 合约上市日期
	 */
	private String createDate;
	/**
	 * 合约状态: 0:已下市、1:上市、2:待上市、3:停牌，4:暂停上市中、5:结算中、6:交割中、7:结算完成、8:交割完成、9:暂停上市
	 */
	private String contractStatus;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public long getContractSize() {
		return contractSize;
	}

	public void setContractSize(long contractSize) {
		this.contractSize = contractSize;
	}

	public double getPriceTick() {
		return priceTick;
	}

	public void setPriceTick(double priceTick) {
		this.priceTick = priceTick;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
}
