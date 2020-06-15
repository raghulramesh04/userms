package com.infosys.project.user.dto;

import com.infosys.project.user.entity.Wishlist;

public class WishlistDTO {
	Integer buyerid;
	Integer prodid;
	
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Integer getProdid() {
		return prodid;
	}
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}
	//Converts Entity into DTO
    public static WishlistDTO valueOf(Wishlist wishlist) {
        WishlistDTO wishlistDTO=new WishlistDTO();
        wishlistDTO.setBuyerid(wishlist.getBuyerId());
        wishlistDTO.setProdid(wishlist.getProdId());
        return wishlistDTO;
}
    //Converts DTO into Entity
        public  Wishlist  createEntity(WishlistDTO wish) {
        	Wishlist wishlist=new Wishlist();
            wishlist.setBuyerId(wish.getBuyerid());
            wishlist.setProdId(wish.getProdid());
            return wishlist;
    }
	
	@Override
	public String toString() {
		return "WishlistDTO [BuyerId="+ buyerid + "ProdId=" +prodid+"]";
	}
	
	

}
