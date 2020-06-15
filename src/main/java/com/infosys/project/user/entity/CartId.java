package com.infosys.project.user.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable {
	Integer buyerId;
	Integer prodId;
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	

}
