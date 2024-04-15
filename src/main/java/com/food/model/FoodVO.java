package com.food.model;

public class FoodVO implements java.io.Serializable{
	private Integer food_id;
	private String food_name;
	private String food_details;
	private byte[] food_pic;
	private Integer food_price;
	private String food_status;
	
	public Integer getFood_id() {
		return food_id;
	}
	public void setFood_id(Integer food_id) {
		this.food_id = food_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getFood_details() {
		return food_details;
	}
	public void setFood_details(String food_details) {
		this.food_details = food_details;
	}
	public byte[] getFood_pic() {
		return food_pic;
	}
	public void setFood_pic(byte[] food_pic) {
		this.food_pic = food_pic;
	}
	public Integer getFood_price() {
		return food_price;
	}
	public void setFood_price(Integer food_price) {
		this.food_price = food_price;
	}
	public String getFood_status() {
		return food_status;
	}
	public void setFood_status(String food_status) {
		this.food_status = food_status;
	}
	
	
}
