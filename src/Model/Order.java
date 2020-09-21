package Model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private String code;
	private Date dateOrder;
	private String clientCode;
	private String restaurantNit;
	private ArrayList<String> productList;
	public Order (String clientCode, String restaurantNit) {
		for (int c = 0; c < 4; c++) {
			code += String.valueOf(Math.round(Math.random() * 9));
		}
		this.clientCode = clientCode;
		this.restaurantNit = restaurantNit;
	}
	
	/** Getters and Setters */
	public void setCode(String code) {
		this.code = code;
	}
	public void setDate(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	public void addProductToList(String productToAdd) {
		productList.add(productToAdd);
	}
	public String getCode() {
		return code;
	}
	public Date getDateOrder() {
		return dateOrder;
	}
	public String getClientCode() {
		return clientCode;
	}
	public String getRestaurantNit() {
		return restaurantNit;
	}
	public ArrayList<String> getProductList() {
		return productList;
	}
}
