package Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import CustomExceptions.ClientDontExistException;
import CustomExceptions.InvalidOptionException;
import CustomExceptions.OrderDontExistException;
import CustomExceptions.ProductDontExistException;
import CustomExceptions.RestaurantDontExistException;

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
	
	public void startMenu() throws NumberFormatException, IOException, InvalidOptionException, RestaurantDontExistException, ClientDontExistException, ClassNotFoundException, ProductDontExistException, OrderDontExistException {
		final int EXIT_OPTION = 0;
		int option = 9999;
		for (int c = 1; c <= 4; c++) {
				bw.write(index.loadData(c));
				bw.flush();
			
		}
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
				case 0: bw.write("Good bye!"); bw.flush(); break;
				case 1: registerRestaurant(); break;
				case 2: registerClient(); break;
				case 3: registerProduct(); break;
				case 4: registerNewOrder(); break;
				case 5: showDataMenu(); break;
				case 6: saveDataMenu(); break;
				case 7: loadDataMenu(); break;
				case 8: editDataMenu(); break;
				case 9: importCSVMenu(); break;
				case 10: sortedDataMenu(); break;
				case 11: generateCSVOrdersReport(); break;
				default: 
					bw.write("Error, Enter a valid option");
					bw.flush();
					break;
			}
		} while (option != EXIT_OPTION);
	}
	
	public void sortedDataMenu() throws IOException {
		bw.write("Enter an option\n(1) Sorted restaurants by name\n(2) Sorted clients by phone\n(3) Find a client by binary search\nOption: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: bw.write(index.getSortedRestaurants()); break;
			case 2: bw.write(index.getSortedClients()); break;
			case 3: break;
		}
		bw.flush();
	}
	
	private void editDataMenu() throws IOException, RestaurantDontExistException, ClientDontExistException, ProductDontExistException, OrderDontExistException, NumberFormatException, InvalidOptionException {
		String msg = "\nEnter an option\n";
		msg += "(1) Edit restaurant information\n";
		msg += "(2) Edit client information\n";
		msg += "(3) Edit product information\n";
		msg += "(4) Edit order information\nOption: ";
		bw.write(msg);
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: editRestaurant(); break;
			case 2: editClient(); break;
			case 3: editProduct(); break;
			case 4: editOrder(); break;
		}
	}
	
	private void editClient() throws IOException, ClientDontExistException {
		try {
			bw.write(index.getDescriptionAllClients() + "Enter a client code to edit\nCode: ");
			bw.flush();
			String code = br.readLine();
			index.verifyClient(code);
			bw.write("Enter an option\n(1) Edit type identity\n(2) Edit Name\n(3) Edit phone\n(4) Edit address\n(5) Edit identity number\nOption:  ");
			bw.flush();
			int option = Integer.parseInt(br.readLine());
			switch (option) {
				case 1: 
					bw.write("Select:\n(1) Cedula\n(2) Card identity\n(3) Passport\n");
					bw.flush();
					break;
				default: break;
			}
			bw.write("Enter the new data: ");
			bw.flush();
			String data = br.readLine();
			bw.write(index.editClientData(code, option, data));
			bw.flush();
		} catch (ClientDontExistException e) {
			bw.write(e.getMessage());
			bw.flush();
		}
	}
	
	private void editRestaurant() throws IOException, RestaurantDontExistException {
		bw.write(index.getDescriptionAllRestaurants() + "Enter a restaurant code to edit\nCode: ");
		bw.flush();
		String code = br.readLine();
		bw.write("Enter an option\n(1) Edit name\n(2) Edit NIT\n(3) Edit Admin name\nOption: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		bw.write("Enter the new data: ");
		bw.flush();
		String data = br.readLine();
		bw.write(index.editRestaurantData(code, option, data));
		bw.flush();
	}
	
	private void editProduct() throws IOException, ProductDontExistException, RestaurantDontExistException {
		bw.write(index.getDescriptionAllProducts() + "Enter a product code to edit\nCode: ");
		bw.flush();
		String code = br.readLine();
		bw.write("Enter an option to edit\n(1) Edit code\n(2) Edit name\n(3) Edit description"
				+ "\n(4) Edit price\n(5)Edit restaurant nit\nOption: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		bw.write("Enter the new data: ");
		bw.flush();
		String data = br.readLine();
		bw.write(index.editProductData(code, option, data));
		bw.flush();
	}
	
	private void editOrder() throws IOException, ClientDontExistException, RestaurantDontExistException, OrderDontExistException, NumberFormatException, InvalidOptionException {
		bw.write(index.getDescriptionAllOrders() + "Enter a order code to edit\nCode: ");
		bw.flush();
		String code = br.readLine();
		bw.write("Enter an option to edit\n(1) Client code\n(2) Status order\nOption: ");
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		if (option == 2) {
			bw.write("Select\n(1) Solicited\n(2) In process\n(3) Send\n(4) Delivered\n");
			bw.flush();
		}
		bw.write("Enter the new data: ");
		bw.flush();
		String data = br.readLine();
		bw.write(index.editOrder(code, option, data));
		bw.flush();
	}
	
	private void saveDataMenu() throws IOException {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Save restaurant data\n";
		message += "(2) Save clients data\n";
		message += "(3) Save products data\n";
		message += "(4) Save orders data\n";
		message += "(5) Save ALL data\nOption: ";
		bw.write(message);
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: bw.write(index.saveData(1)); bw.flush(); break;
			case 2: bw.write(index.saveData(3)); bw.flush(); break;
			case 3: bw.write(index.saveData(2)); bw.flush();break;
			case 4: bw.write(index.saveData(4)); bw.flush(); break;
			case 5:
				for (int c = 1; c <= 4; c++) {
					bw.write(index.saveData(c));
					bw.flush();
				}
				break;
		}
	}
	
	private void importCSVMenu() throws IOException, NumberFormatException, InvalidOptionException {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Import restaurant data\n";
		message += "(2) Import clients data\n";
		message += "(3) Import products data\nOption: ";
		bw.write(message);
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		bw.write("Enter the file name CSV, don't put the extension, and the archive will be stay in the data folder\n");
		bw.flush();
		String fileName = br.readLine();
		switch (option) {
			case 1: bw.write(index.importDataFromCSV(fileName, option)); break;
			case 2: bw.write(index.importDataFromCSV(fileName, option)); break;
			case 3: bw.write(index.importDataFromCSV(fileName, option)); break;
		}
		bw.flush();
	}
	
	private void showDataMenu() throws IOException {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Show restaurants data\n";
		message += "(2) Show clients data\n";
		message += "(3) Show products data\n";
		message += "(4) Show orders data\nOption: ";
		bw.write(message);
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: bw.write(index.getDescriptionAllRestaurants()); break;
			case 2: bw.write(index.getDescriptionAllClients()); break;
			case 3: bw.write(index.getDescriptionAllProducts()); break;
			case 4: bw.write(index.getDescriptionAllOrders()); break;
		}
		bw.flush();
	}
	
	private void loadDataMenu() throws IOException, NumberFormatException, InvalidOptionException, ClassNotFoundException {
		String message = "\nEnter an option\n";
		message += "(0) Exit menu\n";
		message += "(1) Load restaurants data\n";
		message += "(2) Load clients data\n";
		message += "(3) Load products data\n";
		message += "(4) Load orders data\n";
		message += "(5) Load ALL data\nOption: ";
		bw.write(message);
		bw.flush();
		int option = Integer.parseInt(br.readLine());
		switch (option) {
			case 1: bw.write(index.loadData(1)); bw.flush(); break;
			case 2: bw.write(index.loadData(2)); bw.flush(); break;
			case 3: bw.write(index.loadData(3)); bw.flush(); break;
			case 4: bw.write(index.loadData(4)); bw.flush(); break;
			case 5: 
				for (int c = 1; c <= 4; c++) {
					bw.write(index.loadData(c));
					bw.flush();
				}
				break;
		}
	}
	
	private String getMenuMessage() {
		String message = "\nEnter an option\n";
		message += "(0) Exit program\n";
		message += "(1) Register a new restaurant\n";
		message += "(2) Register a new client\n";
		message += "(3) Register a new Product\n";
		message += "(4) Register a new Order\n";
		message += "(5) Show information\n";
		message += "(6) Save data\n";
		message += "(7) Load data\n";
		message += "(8) Edit data\n";
		message += "(9) Import data from CSV file\n";
		message += "(10) Show sorted information\n";
		message += "(11) Generate a CSV file report with all Orders\n";
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
		bw.write(index.getDescriptionAllRestaurants() + "Enter the restaurant code: ");
		bw.flush();
		String cr = br.readLine();
		bw.write(index.getDescriptionAllClients() + "Enter the client code: ");
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
				bw.write(index.getDescriptionSelectedProducts(cr) + "Enter a product code to add, the product must be of the restaurant code: ");
				bw.flush();
				String cpa = br.readLine();
				bw.write("Enter the quantity to buy: ");
				bw.flush();
				int quantity = Integer.parseInt(br.readLine());
				bw.write(index.addProductToListOrder(cr, cpa, codeOrder, quantity) + "You want add one more product?\n(1) Yes\n(2) No\n");
				bw.flush();
				int tempRepeat = Integer.parseInt(br.readLine());
				if (tempRepeat == 1) {
					repeat = true;
				} else {
					repeat = false;
				}
			} while (repeat);
		}
		bw.write("Order finished successfully");
		bw.flush();
	}
	
	private void registerProduct() throws IOException {
		bw.write(index.getDescriptionAllRestaurants() + "Enter the product code: ");
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
	
	private void generateCSVOrdersReport() throws IOException {
		bw.write("Enter the character with which the data will be separated\nIt can't be ( : ): ");
		bw.flush();
		String separator = br.readLine();
		if (separator.equalsIgnoreCase(":")) {
			bw.write("You can't use this separator");
			bw.flush();
		} else {
			bw.write("Enter the name for the CSV file (do not put the extension, it will put itself): ");
		bw.flush();
		bw.write("Please, be patient, we are creating for you a beautiful report :) ...\n");
		String fileName = br.readLine();
		String response = index.createReportCSVOrders(fileName, separator);
		bw.write(response);
		bw.flush();
		}
		
	}
	
}
