/**
 * 
 */
package com.nandashree.asset;

import java.util.Date;

/**
 * @author nandashree.r AssetRequestData.xlsx
 */
public class Order {

	private long orderId;
	private int id;
	private int empId;
	private int quantity;
	private long orderDate;
	private long deliverDate;
	private int status;

	public static enum OrderStatus {
		ORDERED, INPROGRESS, DELIVERED;
	}

	public Order(long orderId, int id, int empId, int quantity, long orderDate, long deliverDate, int status) {
		super();
		this.orderId = orderId;
		this.id = id;
		this.empId = empId;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.deliverDate = deliverDate;
		this.status = status;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

	public long getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(long deliverDate) {
		this.deliverDate = deliverDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {

		OrderStatus arr[] = OrderStatus.values();
		return "Order [orderId=" + orderId + ", AssetId=" + id + ", EmpId=" + empId + ", quantity=" + quantity
				+ ", orderDate=" + new Date(orderDate) + ", deliverDate=" + new Date(deliverDate) + ", status="
				+ arr[status] + "]";
	}
}
