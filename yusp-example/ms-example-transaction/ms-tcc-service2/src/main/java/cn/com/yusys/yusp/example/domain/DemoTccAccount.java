/*
 * 代码生成器自动生成的
 * Since 2008 - 2018
 *
 */
package cn.com.yusys.yusp.example.domain;

import cn.com.yusys.yusp.commons.mapper.annotation.Generated;
import cn.com.yusys.yusp.commons.mapper.annotation.GenerationType;
import cn.com.yusys.yusp.commons.mapper.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @项目名称: test111模块
 * @类名称: DemoTccAccount
 * @类描述: #数据实体类
 * @功能描述: 
 * @创建人: yuhq
 * @创建时间: 2018-05-08 10:15:27
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
@Table(name = "DEMO_TCC_ACCOUNT")
public class DemoTccAccount extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/** ACCT_ID **/
	@Id 
	@Column(name = "ACCT_ID")
	@Generated(GenerationType.UUID)
	private String acctId;
	/** AMOUNT **/
	@Column(name = "AMOUNT")
	private java.math.BigDecimal amount;
	
	/** FROZEN **/
	@Column(name = "FROZEN")
	private java.math.BigDecimal frozen;
	
	
	/**
	 * @param acctId
	 */
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	
    /**
     * @return acctId
     */
	public String getAcctId() {
		return this.acctId;
	}
	
	/**
	 * @param amount
	 */
	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}
	
    /**
     * @return amount
     */
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	/**
	 * @param frozen
	 */
	public void setFrozen(java.math.BigDecimal frozen) {
		this.frozen = frozen;
	}
	
    /**
     * @return frozen
     */
	public java.math.BigDecimal getFrozen() {
		if(this.frozen==null)
			return new BigDecimal("0.00");
		return this.frozen;
	}

}