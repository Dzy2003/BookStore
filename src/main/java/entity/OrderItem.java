package entity;

import java.math.BigInteger;

/**
 * 订单项实体
 */
public class OrderItem {
	private Integer id;
	private Long oid;
	private Integer bid;
	private Integer quantity;

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", oid=" + oid +
				", bid=" + bid +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}

	private double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
