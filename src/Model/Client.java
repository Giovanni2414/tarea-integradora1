package Model;

public class Client {
	
	private final int CEDULA = 1;
	private final int TARJETA_IDENTIDAD = 2;
	private final int PASSPORT = 3;
	private String identificationType;
	private String identificationNumber;
	private String name;
	private String phone;
	private String address;
	
	public Client(int identificationType, String identificationNumber, String name, String phone, String address) {
		this.identificationNumber = identificationNumber;
		this.name = name;
		this.phone = phone;
		this.address = address;
		switch(identificationType) {
			case CEDULA:
				this.identificationType = "Cédula";
				break;
			case TARJETA_IDENTIDAD:
				this.identificationType = "Tarjeta de identidad";
				break;
			case PASSPORT:
				this.identificationType = "Pasaporte";
				break;
		}
	}
	
	/** Getters and Setters */
	public void setIdentificationType(int identificationType) {
		switch(identificationType) {
			case CEDULA:
				this.identificationType = "Cédula";
				break;
			case TARJETA_IDENTIDAD:
				this.identificationType = "Tarjeta de identidad";
				break;
			case PASSPORT:
				this.identificationType = "Pasaporte";
				break;
		}
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentificationType() {
		return identificationType;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	
}
