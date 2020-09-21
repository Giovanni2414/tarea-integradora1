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
				case 0: exitProgram(); break;
				case 1: registerRestaurant(); break;
				case 2: registerClient(); break;
				case 3: registerProduct(); break;
				case 4: registerNewOrder(); break;
				case 5: showRestaurants(); break;
				case 6: exportDataMenu(); break;
				default: 
					bw.write("Error, Enter a valid option");
					bw.flush();
					break;
			}
		} while (option != EXIT_OPTION);
	}
	
	private void exportDataMenu() throws IOException {
		bw.write(getExportMenuMessage());
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: exportRestaurantData(); break;
		}
	}
	
	private void exportRestaurantData() {
		
	}
	
	private void exitProgram() throws IOException {
		bw.write("Good bye!");
		bw.flush();
	}
	
	private String getMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit program\n";
		message += "(1) Register a new restaurant\n";
		message += "(2) Register a new client\n";
		message += "(3) Register a new Product\n";
		message += "(4) Register a new Order\n";
		message += "(5) Show all restaurants\n";
		message += "(6) Export data\n";
		return message;
	}
	
	private String getExportMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Export restaurant data\n";
		message += "(2) Export products data\n";
		message += "(3) Export products data\n";
		message += "(4) Export orders data\n";
		return message;
	}
	
	private void registerRestaurant() throws IOException {
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
	
	private void registerClient() throws IOException, InvalidOptionException {
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
	
	private void registerNewOrder() throws IOException {
		bw.write("Enter the restaurant code: ");
		bw.flush();
		String cr = br.readLine();
		bw.write("Enter the client code: ");
		bw.flush();
		String cc = br.readLine();
		String messageResponse = index.addOrder(cc, cr);
		bw.write(messageResponse);
		bw.flush();
		if (messageResponse.equalsIgnoreCase("The order has been added successfully\n")) {
			String codeOrder = index.getLastCodeOrder();
			bw.write("\nThe code of this order is: " + codeOrder + "\nNow, you need add the products to the order\n");
			bw.flush();
			boolean repeat = false;
			do {
				bw.write("Enter a product code to add, the product must be of the restaurant code\n");
				bw.flush();
				String cpa = br.readLine();
				bw.write(index.addProductToListOrder(cr, cpa, codeOrder) + "You want add one more product?\n(1) Yes\n(2) No\n");
				bw.flush();
				int tempRepeat = Integer.parseInt(br.readLine());
				if (tempRepeat == 1) {
					repeat = true;
				} else {
					repeat = false;
				}
			} while (repeat);
		}
	}
	
	private void registerProduct() throws IOException {
		bw.write("Enter the product code: ");
		bw.flush();
		String pc = br.readLine();
		bw.write("Enter the product name: ");
		bw.flush();
		String pn = br.readLine();
		bw.write("Enter the product description: ");
		bw.flush();
		String pd = br.readLine();
		bw.write("Enter the product price: ");
		bw.flush();
		double pp = Double.parseDouble(br.readLine());
		bw.write("Enter the restaurant code: ");
		bw.flush();
		String rc = br.readLine();
		bw.write(index.addProduct(pc, pn, pd, pp, rc));
		bw.flush();
	}
	
	private void showRestaurants() throws IOException {
		bw.write(index.getDescriptionAllRestaurants());
		bw.flush();
	}
	
}
