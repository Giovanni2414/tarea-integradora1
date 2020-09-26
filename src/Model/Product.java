package Model;

import java.io.Serializable;

public class Product implements Serializable {
	
	/**
	 * Version de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo del producto
	 */
	private String code;
	/**
	 * Nombre del producto
	 */
	private String name;
	/**
	 * Descripcion del producto
	 */
	private String description;
	/**
	 * Precio del producto
	 */
	private double price;
	/**
	 * Identificador del restaurante al que pertenece
	 */
	private String restaurantNit;
	
	/**
	 * Constructor de la clase Product
	 * @param code Codigo del producto
	 * @param name Nombre del producto
	 * @param description Descripcion del producto
	 * @param price Precio del producto 
	 * @param restaurantNit Restaurante codigo al cual pertenece el producto
	 */
	public Product(String code, String name, String description, double price, String restaurantNit) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.restaurantNit = restaurantNit;
	}
	
	@Override
	public String toString() {
		String msg = "-------------------\n";
		msg += "Code:" + getCode() + " - Name:" + getName() + " - Description:" + getDescription() + " - Price:" + getPrice() + "-Restaurant Code:" + getRestaurantNit() + "\n";
		return msg;
	}
	
	/**Getters and Setters*/
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public String getRestaurantNit() {
		return restaurantNit;
	}
}
