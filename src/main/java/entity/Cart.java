package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 购物车实体
 */
public class Cart {
	private Integer cid;
	private Integer uid;
	private Integer bid;
	private Long price;
	private Integer num;

	@Override
	public String toString() {
		return "Cart{" +
				"cid=" + cid +
				", uid=" + uid +
				", bid=" + bid +
				", price=" + price +
				", num='" + num + '\'' +
				'}';
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
