package Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {
	
	private String code;
	private String dateOrder;
	private String clientCode;
	private String restaurantNit;
	private ArrayList<String> productList;
	
	public Order (String clientCode, String restaurantNit) {
		code = "";
		for (int c = 0; c < 5; c++) {
			code += String.valueOf(Math.round(Math.random() * 9));
		}
		this.clientCode = clientCode;
		this.restaurantNit = restaurantNit;
		productList = new ArrayList<>();
		int day = Calendar.DATE;
		int month = Calendar.MONTH;
		int year = Calendar.YEAR;
		dateOrder = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
	}
	
	public String getListProductsToExport() {
		String msg = "";
		for (int c = 0; c < productList.size(); c++) {
			if ((c+1) == productList.size()) {
				msg += productList.get(c);
			} else {
				msg += productList.get(c) + ":";
			}
		}
		return msg;
	}
	
	public void addProduct(String code) {
		productList.add(code);
	}
	
	/** Getters and Setters */
	public void setCode(String code) {
		this.code = code;
	}
	public void setDate(String dateOrder) {
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
	public String getDateOrder() {
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
