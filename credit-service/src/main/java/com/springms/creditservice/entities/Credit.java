package com.springms.creditservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_MASTER")
public class Credit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4226629163205809928L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", nullable = false)
	private int userId;
	@Column(name="max_credit_amt", nullable = false)
	private double maxCreditAmount;
	@Column(name="credit_used", nullable = false)
	private double creditUsed;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getMaxCreditAmount() {
		return maxCreditAmount;
	}
	public void setMaxCreditAmount(double maxCreditAmount) {
		this.maxCreditAmount = maxCreditAmount;
	}
	public double getCreditUsed() {
		return creditUsed;
	}
	public void setCreditUsed(double creditUsed) {
		this.creditUsed = creditUsed;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Credit [userId=");
		builder.append(userId);
		builder.append(", maxCreditAmount=");
		builder.append(maxCreditAmount);
		builder.append(", creditUsed=");
		builder.append(creditUsed);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
