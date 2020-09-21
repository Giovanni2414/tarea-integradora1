package Model;

public class Restaurant implements Comparable<Restaurant> {
	
	private String name;
	private String nameAdmin;
	private String nit;
	
	public Restaurant(String name, String nameAdmin, String nit) {
		this.name = name;
		this.nameAdmin = nameAdmin;
		this.nit = nit;
	}
	
	public String toString() {
		String message = "-------------------\n";
		message += "Name:" + getName() + "\n";
		message += "Name admin:" + getNameAdmin() + "\n";
		message += "Nit:" + getNit() + "\n";
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
