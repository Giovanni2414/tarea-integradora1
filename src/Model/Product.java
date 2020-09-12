package Model;

public class Product {
	private String code;
	private String name;
	private String description;
	private double price;
	private String restaurantNit;
	public Product(String code, String name, String description, double price, String restaurantNit) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.restaurantNit = restaurantNit;
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
