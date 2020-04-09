package com.springms.orderservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "order_master")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8229417345945204586L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_no", nullable = false)
	private int orderNo;
	@Column(name = "order_quantity", nullable = false)
	private int orderQuantity;
	@Column(name = "total_order_amount", nullable = false)
	private double totatOderAmount;
	@Column(name = "user_id", nullable = false)
	private int userId;
	@Column(name = "order_status")
	private String orderStatus;
	@Column(name = "message")
	private String message;
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public double getTotatOderAmount() {
		return totatOderAmount;
	}
	public void setTotatOderAmount(double totatOderAmount) {
		this.totatOderAmount = totatOderAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderNo=");
		builder.append(orderNo);
		builder.append(", orderQuantity=");
		builder.append(orderQuantity);
		builder.append(", totatOderAmount=");
		builder.append(totatOderAmount);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
