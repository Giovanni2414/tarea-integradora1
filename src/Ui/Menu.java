package Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import CustomExceptions.InvalidOptionException;
import java.io.BufferedWriter;
import Model.Software;

public class Menu {

	Software index;
	BufferedReader br;
	BufferedWriter bw;
	
	public Menu () throws NumberFormatException, IOException {
		index = new Software();
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	public void startMenu() throws NumberFormatException, IOException, InvalidOptionException {
		final int EXIT_OPTION = 0;
		int option = 9999;
		do {
			bw.write(getMenuMessage());
			bw.flush();
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				bw.write("Error, invalid type character");
				bw.flush();
			}
			switch (option) {
				case 1: registerRestaurant(); break;
				case 2: registerClient(); break;
				default: 
					bw.write("Error, Enter a valid option");
					bw.flush();
					break;
			}
		} while (option != EXIT_OPTION);
	}
	
	public String getMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit program\n";
		message += "(1) Register a new restaurant\n";
		message += "(2) Register a new client\n";
		return message;
	}
	
	public void registerRestaurant() throws IOException {
		bw.write("Enter the name of the restaurant: ");
		bw.flush();
		String name = br.readLine();
		bw.write("Enter the nit of the restaurant: ");
		bw.flush();
		String nit = br.readLine();
		bw.write("Enter the name of the admin of the restaurant: ");
		bw.flush();
		String adN = br.readLine();
		index.addRestaurant(name, nit, adN);
		bw.write("Restaurant added successfully\n");
		bw.flush();
	}
	
	public void registerClient() throws IOException, InvalidOptionException {
		bw.write("Enter the type of identification\n(1) Cédula\n(2) Identity card\n(3) Passport\n");
		bw.flush();
		int it = Integer.parseInt(br.readLine());
		bw.write("Enter the number identification: ");
		bw.flush();
		String in = br.readLine();
		bw.write("Enter the client name: ");
		bw.flush();
		String name = br.readLine();
		bw.write("Enter the client phone: ");
		bw.flush();
		String phone = br.readLine();
		bw.write("Enter the client address: ");
		bw.flush();
		String address = br.readLine();
		bw.write(index.addClient(it, in, name, phone, address));
		bw.flush();
	}
	
}
