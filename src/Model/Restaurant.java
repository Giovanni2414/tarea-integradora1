package Model;

import java.io.Serializable;

public class Restaurant implements Comparable<Restaurant>, Serializable {
	
	/**
	 * Version de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre del restaurante
	 */
	private String name;
	/**
	 * Nombre del administrador del restaurante
	 */
	private String nameAdmin;
	/**
	 * Identificador del restaurante
	 */
	private String nit;
	
	/**
	 * Constructor de la clase Restaurant
	 * @param name Nombre del restaurante, String
	 * @param nameAdmin Nombre del administrador, String
	 * @param nit Identificador del restaurante, String
	 */
	public Restaurant(String name, String nameAdmin, String nit) {
		this.name = name;
		this.nameAdmin = nameAdmin;
		this.nit = nit;
	}
	
	/**
	 * Obtener la información del objeto con el Override de toString
	 * <br>Pre:
	 * <br>Post:
	 * @return Retorna un mensaje con la informacion de la clase
	 */
	public String toString() {
		String message = "-------------------\n";
		message += "Name:" + getName() + " - Name admin: " + getNameAdmin() + " - Nit: " + getNit() + "\n";
		return message;
	}
	
	@Override
	public int compareTo(Restaurant otherRestaurant) {
		int response = name.compareToIgnoreCase(otherRestaurant.getName());
		return response;
	}
	
	/*Getters and Setters*/
	public void setName(String name) {
		this.name = name;
	}
	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getName() {
		return name;
	}
	public String getNameAdmin() {
		return nameAdmin;
	}
	public String getNit() {
		return nit;
	}
	
}
