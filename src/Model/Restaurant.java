package Model;

public class Restaurant {
	private String name;
	private String nameAdmin;
	private String nit;
	public Restaurant(String name, String nameAdmin, String nit) {
		this.name = name;
		this.nameAdmin = nameAdmin;
		this.nit = nit;
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
