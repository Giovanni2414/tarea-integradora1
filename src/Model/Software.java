package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import CustomExceptions.ClientDontExistException;
import CustomExceptions.RestaurantDontExistException;
import CustomExceptions.InvalidOptionException;

public class Software {
	
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Product> products;
	private List<Client> clients;
	private ArrayList<Order> orders;
	
	public Software() {
		restaurants = new ArrayList<>();
		products = new ArrayList<>();
		clients = new LinkedList<>();
		orders = new ArrayList<>();
	}
	
	public void addRestaurant(String n, String nit, String adN) {
		restaurants.add(new Restaurant(n, adN, nit));
		Collections.sort(restaurants);
	}
	
	public String addProduct(String cd, String n, String d, double p, String rn) {
		String message = "";
		boolean find = false;
		for (int c = 0; c < restaurants.size() && !find; c++) {
			if (rn.equalsIgnoreCase(restaurants.get(c).getNit())) {
				products.add(new Product(cd, n, d, p, rn));
				find = true;
				message = "The product has been added\n";
			}
		}
		if (!find) {
			message = "The product can't be added, the restaurant with this NIT do not exists\n";
		}
		return message;
	}
	
	public String addClient(int it, String in, String n, String p, String a) throws InvalidOptionException{
		String message = "";
		try {
			if (it < 1 || it > 3) {
				throw new InvalidOptionException();
			} else {
				clients.add(new Client(it, in, n, p, a));
				message = "Client added successfully\n";
			}
		} catch (InvalidOptionException e) {
			message = e.getMessage();
		}
		return message;
	}
	
	public String addOrder(String cc, String rn) {
		String message = "";
		try {
			verifyClient(cc);
			verifyRestaurant(rn);
			orders.add(new Order(cc, rn));
			message = "The order has been added successfully\n";
		} catch (ClientDontExistException e) {
			message = e.getMessage();
		} catch (RestaurantDontExistException e) {
			message = e.getMessage();
		}
		return message;
	}
	
	public String addProductToListOrder(String cr, String cp, String orderC) {
		String message = "The product can't be added at the order, the code of the restaurant or product is bad\n";
		for (int i = 0; i < products.size(); i++) {
			if ((products.get(i).getCode()).equalsIgnoreCase(cp) && (products.get(i).getRestaurantNit()).equalsIgnoreCase(cr)) {
				for (int c = 0; c < orders.size(); c++) {
					if (orderC.equalsIgnoreCase(orders.get(c).getCode())) {
						orders.get(c).addProduct(cp);
						message = "Product added successfully to the order\n";
					}
				}
			}
		}
		return message;
	}
	
	public String getLastCodeOrder() {
		String response = orders.get(orders.size() - 1).getCode();
		return response;
	}
	
	public void verifyClient(String cc) throws ClientDontExistException {
		boolean clientFind = false;
		for (int c = 0; c < clients.size() && !clientFind; c++) {
			if ((clients.get(c).getIdentificationNumber()).equalsIgnoreCase(cc)) {
				clientFind = true;
			}
		}
		if (!clientFind) {
			throw new ClientDontExistException();
		}
	}
	
	public void verifyRestaurant(String rn) throws RestaurantDontExistException {
		boolean restaurantFind = false;
		for (int c = 0; c < restaurants.size() && !restaurantFind; c++) {
			if ((restaurants.get(c).getNit()).equalsIgnoreCase(rn)) {
				restaurantFind = true;
			}
		}
		if (!restaurantFind) {
			throw new RestaurantDontExistException();
		}
	}
	
	public String getClientListSortByNumber() {
		String message = "Client list order by Number\n";
		ArrayList<Client> clientsCopy = new ArrayList<>();
		for (int c = 0; c < clients.size(); c++) {
			clientsCopy.add(clients.get(c));
		}
		numberClientComparator comparator = new numberClientComparator();
		Collections.sort(clientsCopy, comparator);
		for (int c = 0; c < clientsCopy.size(); c++) {
			message += clientsCopy.get(c).getIdentificationType() + " - ";
			message += clientsCopy.get(c).getIdentificationNumber() + " - ";
			message += clientsCopy.get(c).getName() + " - ";
			message += clientsCopy.get(c).getPhone() + " - ";
			message += clientsCopy.get(c).getAddress() + "\n";
		}
		return message;
	}
	
	public class numberClientComparator implements Comparator<Client> {
		
		@Override
		public int compare(Client c1, Client c2) {
			int response = 0;
			long number1 = Long.parseLong(c1.getPhone());
			long number2 = Long.parseLong(c2.getPhone());
			if (number1 < number2) {
				response = -1;
			} else if (number1 > number2) {
				response = 1;
			}
			return response;
		}
		
	}
	
	public String getDescriptionAllRestaurants() {
		String message = "-------------------\n";
		message += "    Restaurants    \n";
		for (int c = 0; c < restaurants.size(); c++) {
			message += restaurants.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getDescriptionAllClients() {
		String message = "-------------------\n";
		message += "    Clients    \n";
		for (int c = 0; c < clients.size(); c++) {
			message += clients.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String exportRestaurantData(String fileName) throws FileNotFoundException {
		String message = "Data was exported successfully";
		PrintWriter pw = new PrintWriter("data/" + fileName + ".csv");

	    for(int i=0;i<restaurants.size();i++){
	      Restaurant myRestaurant = restaurants.get(i);
	      pw.println(myRestaurant.getName()+","+myRestaurant.getNit()+","+myRestaurant.getNameAdmin());
	    }

	    pw.close();
		return message;
	}
	
	public String exportClientData(String fileName) throws FileNotFoundException {
		String message = "Data was exported successfully";
		PrintWriter pw = new PrintWriter("data/" + fileName + ".csv");

	    for(int i=0;i<clients.size();i++){
	      Client myClient = clients.get(i);
	      pw.println(myClient.getName()+","+myClient.getIdentificationTypeForExport()+","+myClient.getIdentificationNumber()+","+myClient.getPhone()+","+myClient.getAddress());
	    }

	    pw.close();
		return message;
	}
	
	public String exportProductData(String fileName) throws FileNotFoundException {
		String message = "Data was exported successfully";
		PrintWriter pw = new PrintWriter("data/" + fileName + ".csv");

	    for(int i=0;i<products.size();i++){
	      Product myProduct = products.get(i);
	      pw.println(myProduct.getName()+","+myProduct.getPrice()+","+myProduct.getCode()+","+myProduct.getDescription()+","+myProduct.getRestaurantNit());
	    }

	    pw.close();
		return message;
	}
	
	public String exportOrderData(String fileName) throws FileNotFoundException {
		String message = "Data was exported successfully";
		PrintWriter pw = new PrintWriter("data/" + fileName + ".csv");

	    for(int i=0;i<orders.size();i++){
	      Order myOrder = orders.get(i);
	      pw.println(myOrder.getClientCode()+","+myOrder.getCode()+","+myOrder.getRestaurantNit()+","+myOrder.getDateOrder()+","+myOrder.getListProductsToExport());
	    }

	    pw.close();
		return message;
	}
	
	public String importRestaurantData(String fileName) throws IOException {
		String msg = "Data was imported succesfully";
	    BufferedReader br = new BufferedReader(new FileReader("data/" + fileName + ".csv"));
	    String line = br.readLine();
	    while(line!=null){
	      String[] parts = line.split(",");
	      addRestaurant(parts[0], parts[1], parts[2]);
	      line = br.readLine();
	    }
	    br.close();
	    return msg;
	}
	
	public String importClientData(String fileName) throws IOException, NumberFormatException, InvalidOptionException {
		String msg = "Data was imported succesfully";
	    BufferedReader br = new BufferedReader(new FileReader("data/" + fileName + ".csv"));
	    String line = br.readLine();
	    while(line!=null){
	      String[] parts = line.split(",");
	      addClient(Integer.parseInt(parts[1]), parts[2], parts[0], parts[3], parts[4]);
	      line = br.readLine();
	    }
	    br.close();
	    return msg;
	}
	
}
