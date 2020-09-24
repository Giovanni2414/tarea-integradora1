package Model;

import java.io.Serializable;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	public int getIdentificationTypeForExport() {
		int response = 0;
		if (identificationType.equals("Cédula")) {
			response = CEDULA;
		} else if (identificationType.equals("Pasaporte")) {
			response = PASSPORT;
		} else {
			response = TARJETA_IDENTIDAD;
		}
		return response;
	}
	
	public String toString() {
		String message = "-------------------\n";
		message += "Name:" + getName() + " - Type:" + getIdentificationType() + " - Code:" + getIdentificationNumber() + " - Phone:" + getPhone() + " - ";
		message += "Address:" + getAddress() + "\n";
		return message;
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
