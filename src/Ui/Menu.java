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
				bw.write("Option: ");
				bw.flush();
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
				case 5: showDataMenu(); break;
				case 6: exportDataMenu(); break;
				case 7: importDataMenu(); break;
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
		bw.write("Option: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: exportRestaurantData(); break;
			case 2: exportProductData(); break;
			case 3: exportClientData(); break;
			case 4: exportOrderData(); break;
		}
	}
	
	private void showDataMenu() throws IOException {
		bw.write(getInformationMenuMessage());
		bw.flush();
		bw.write("Option: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: showRestaurants(); break;
			case 2: showClients(); break;
			case 3:  break;
			case 4:  break;
		}
	}
	
	private void importDataMenu() throws IOException, NumberFormatException, InvalidOptionException {
		bw.write(getImportMenuMessage());
		bw.flush();
		bw.write("Option: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: importRestaurantData(); break;
			case 2: importClientData(); break;
			case 3:  break;
			case 4:  break;
		}
	}
	
	private void exportRestaurantData() throws IOException {
		bw.write("File name to export restaurant data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.exportRestaurantData(nameFile));
		bw.flush();
	}
	
	private void exportProductData() throws IOException {
		bw.write("File name to export products data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.exportProductData(nameFile));
		bw.flush();
	}
	
	private void exportClientData() throws IOException {
		bw.write("File name to export clients data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.exportClientData(nameFile));
		bw.flush();
	}
	
	private void exportOrderData() throws IOException {
		bw.write("File name to export orders data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.exportOrderData(nameFile));
		bw.flush();
	}
	
	private void importRestaurantData() throws IOException {
		bw.write("File name to import restaurants data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.importRestaurantData(nameFile));
		bw.flush();
	}
	
	private void importClientData() throws IOException, NumberFormatException, InvalidOptionException {
		bw.write("File name to import restaurants data (Do not put the extension, that archive will be a .csv): ");
		bw.flush();
		String nameFile = br.readLine();
		bw.write(index.importClientData(nameFile));
		bw.flush();
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
		message += "(5) Show information\n";
		message += "(6) Export data\n";
		message += "(7) Import data\n";
		return message;
	}
	
	private String getExportMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Export restaurant data\n";
		message += "(2) Export products data\n";
		message += "(3) Export clients data\n";
		message += "(4) Export orders data\n";
		return message;
	}
	
	private String getImportMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Import restaurant data\n";
		message += "(2) Import clients data\n";
		message += "(3) Import products data\n";
		message += "(4) Import orders data\n";
		return message;
	}
	
	private String getInformationMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Show restaurants data\n";
		message += "(2) Show clients data\n";
		message += "(3) Show products data\n";
		message += "(4) Show orders data\n";
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
	
	private void showClients() throws IOException {
		bw.write(index.getDescriptionAllClients());
		bw.flush();
	}
	
}
