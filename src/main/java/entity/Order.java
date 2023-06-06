package entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单实体
 */
public class Order {
	private Integer id;
	private Integer uid;
	private Date orderTime;

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", uid=" + uid +
				", orderTime=" + orderTime +
				", price=" + price +
				", state=" + state +
				'}';
	}
	private double price;
	private boolean state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
