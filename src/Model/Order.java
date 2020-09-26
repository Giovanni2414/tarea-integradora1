package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	
	/**
	 * Version de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo de la orden
	 */
	private String code;
	/**
	 * Fecha de la orden
	 */
	private Date dateOrder;
	/**
	 * Cliente al cual pertenece la orden
	 */
	private String clientCode;
	/**
	 * Codigo del restaurante al cual se le pidio la orden
	 */
	private String restaurantNit;
	/**
	 * Lista con los productos comprados
	 */
	private ArrayList<String> productList;
	/**
	 * Lista con las cantidades compradas
	 */
	private ArrayList<Integer> quantities;
	/**
	 * Estado de la orden
	 */
	private String status;
	
	/**
	 * Constructor de la clase
	 * @param clientCode Codigo del cliente que hizo la orden
	 * @param restaurantNit Código del restaurante al cual se le pidió la orden
	 */
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
			msg += "\n";
		}
		return msg;
	}
	
	/**
	 * Metodo para añadir la cantidad de un producto comprado
	 * <br>Pre: El ARrayList quantities debe estar inicializado
	 * <br>Post: Objeto añadido al arrayList quantities
	 * @param q Cantidad de un producto
	 */
	public void addQuantity(int q) {
		quantities.add(q);
	}
	
	public ArrayList<Integer> getQuantitiesList() {
		return quantities;
	}
	
	/**
	 * Metodo para obtener una pequeña lista con los nombres y productos comprados para exportar al CSV
	 * <br>Pre: ArrayLists de products debe estar inicializado
	 * <br>Post: 
	 * @return Mensaje con una lista de los productos comprados
	 */
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
	
	/**
	 * Metodo para añadir un producto a la orden
	 * <br>Pre: ArrayList productLsit debe estar inicializado
	 * <br>Post: Objeto añadido al arrayList de productos
	 * @param code
	 */
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
