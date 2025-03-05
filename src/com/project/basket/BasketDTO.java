package com.project.basket;

public class BasketDTO {
	
	private int pnum;
	private int basketCount;
	private int price;
	private String ship;
	private String saveFileName;
	
	
	//테이블에는 들어가지 않고, 코딩 편의성을 위해 추가함.
	private String id;
	private String pname;
	
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public void setShip(String ship) {
		this.ship = ship;
	}
	public int getBasketCount() {
		return basketCount;
	}
	public void setBasketCount(int basketCount) {
		this.basketCount = basketCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int totalPrice) {
		this.price = totalPrice;
	}
	public String getShip() {
		return ship;
	}
	
	
	
	

}
