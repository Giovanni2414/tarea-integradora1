package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private Date dateOrder;
	private String clientCode;
	private String restaurantNit;
	private ArrayList<String> productList;
	private ArrayList<Integer> quantities;
	private String status;
	
	public Order (String clientCode, String restaurantNit) {
		code = "";
		for (int c = 0; c < 5; c++) {
			code += String.valueOf(Math.round(Math.random() * 9));
		}
		this.clientCode = clientCode;
		this.restaurantNit = restaurantNit;
		productList = new ArrayList<>();
		quantities = new ArrayList<>();
		dateOrder = new Date();
		status = "Solicited";
	}
	
	@Override
	public String toString() {
		String msg = "-------------------\nCode: " + getCode() + " - Date: " + getDateOrder() + " - Restaurant ID: " + getRestaurantNit() + "- Status: " + status + " - Codes of shopped products: ";
		for (int c = 0; c < productList.size(); c++) {
			if ((c+1) == productList.size()) {
				msg += productList.get(c) + " x " + quantities.get(c);
			} else {
				msg += productList.get(c) + " x " + quantities.get(c) + ",";
			}
		}
		return msg;
	}
	
	public void addQuantity(int q) {
		quantities.add(q);
	}
	
	public ArrayList<Integer> getQuantitiesList() {
		return quantities;
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
	public String getStatusString() {
		return status;
	}
	
	public int getStatus() {
		int res = 0;
		if (status.equals("Solicited")) {
			res = 1;
		} else if (status.equals("In process")) {
			res = 2;
		} else if (status.equals("Send")) {
			res = 3;
		} else {
			res = 4;
		}
		return res;
	}
	
	public void setStatus(int intToConvert) {
		switch (intToConvert) {
		case 1: status = "Solicited"; break;
		case 2: status = "In process"; break;
		case 3: status = "Send"; break;
		case 4: status = "Delivered"; break;
		}
	}
	
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
	public String getDateOrder() {
		return dateOrder.toString();
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
