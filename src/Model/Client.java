package Model;

import java.io.Serializable;

public class Client implements Serializable {
	
	/**
	 * Version de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante para el tipo de identificacion cedula
	 */
	private final int CEDULA = 1;
	/**
	 * Constante para el tipo de identificacion tarjeta de identidad
	 */
	private final int TARJETA_IDENTIDAD = 2;
	/**
	 * Constante para el tipo de identificacion pasaporte
	 */
	private final int PASSPORT = 3;
	/**
	 * tipo de identificacion en String
	 */
	private String identificationType;
	/**
	 * Numero de identificacion
	 */
	private String identificationNumber;
	/**
	 * Nombre del cliente
	 */
	private String name;
	/**
	 * Telefono del cliente
	 */
	private String phone;
	/**
	 * Direccion del cliente
	 */
	private String address;
	
	/**
	 * Constructor de la clase
	 * @param identificationType Tipo de identificacion en entero
	 * @param identificationNumber Numero de indentificacion, String
	 * @param name Nombre del cliente, String
	 * @param phone Telefono del cliente, String
	 * @param address Direccion de cliente, String
	 */
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
	
	/**
	 * Obtener el tipo de identificacion en formato String
	 * <br>Pre: Variable identificationType con algún valor String
	 * <br>Post:
	 * @return Un entero que hace referencia a un tipo de identificacion
	 */
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
	
	@Override
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
