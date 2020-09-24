package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	private List<Restaurant> restaurants;
	private List<Product> products;
	private List<Client> clients;
	private List<Order> orders;
	
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
				if (clients.isEmpty()) {
					clients.add(new Client(it, in, n, p, a));
				} else {
					int c = 0;
					while (c < clients.size() && comparatorAddClient(n, clients.get(c).getName()) >= 1) {
						c++;
					}
					clients.add(c, new Client(it, in, n, p, a));
				}
				message = "Client added successfully\n";
			}
		} catch (InvalidOptionException e) {
			message = e.getMessage();
		}
		return message;
	}
	
	private int comparatorAddClient(String name1, String name2) {
		int res = 0;
		String[] name1S = name1.split(" ");
		String[] name2S = name2.split(" ");
		res = String.valueOf(name1S[0].charAt(0)).compareToIgnoreCase(String.valueOf(name2S[0].charAt(0)));
		if (res == 0) {
			res = String.valueOf(name1S[1].charAt(0)).compareToIgnoreCase(String.valueOf(name2S[1].charAt(0)));
		}
		return res;
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
	
	public String editRestaurantData(String code, int option, String data) {
		String msg = "Restaurant and derivated products has been updated";
			for (int c = 0; c < restaurants.size(); c++) {
				if ((restaurants.get(c).getNit()).equals(code)) {
					switch (option) {
						case 1: (restaurants.get(c)).setName(data); break;
						case 2: (restaurants.get(c)).setNameAdmin(data); break;
						case 3: (restaurants.get(c)).setNit(data); 
							for (int i = 0; i < products.size(); i++) {
								if ((products.get(i).getRestaurantNit()).equals(code)) {
									products.get(i).setRestaurantNit(data);
								}
							}
							break;
					}
				}
			}
		return msg;
	}
	
	public String editClientData(String code, int option, String data) {
		for (int c = 0; c < clients.size(); c++) {
			if ((clients.get(c).getIdentificationNumber()).equals(code)) {
				switch (option) {
					case 1: clients.get(c).setIdentificationType(Integer.parseInt(data)); break;
					case 2: clients.get(c).setName(data); break;
					case 3: clients.get(c).setPhone(data); break;
					case 4: clients.get(c).setAddress(data); break;
					case 5: clients.get(c).setIdentificationNumber(data); break;
				}
				//Bubble sort algorithm
				int n = clients.size();
				for (int i = 0; i < n - 1; i++) {
					for (int j = 0; j < n - i - 1; j++) {
						int res = 0;
						String[] name1S = clients.get(j).getName().split(" ");
						String[] name2S = clients.get(j + 1).getName().split(" ");
						res = String.valueOf(name1S[0].charAt(0)).compareToIgnoreCase(String.valueOf(name2S[0].charAt(0)));
						if (res == 0) {
							res = String.valueOf(name1S[1].charAt(0)).compareToIgnoreCase(String.valueOf(name2S[1].charAt(0)));
						}
						if (res >= 1) {
							Client temp = clients.get(j);
							clients.set(j, clients.get(j + 1));
							clients.set(j + 1, temp);
						}
					}
				}
			}
		}
		return "Client data has been updated";
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
		String message = "\n-------------------\n";
		message += "    Restaurants    \n";
		for (int c = 0; c < restaurants.size(); c++) {
			message += restaurants.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getDescriptionAllClients() {
		String message = "\n-------------------\n";
		message += "    Clients    \n";
		for (int c = 0; c < clients.size(); c++) {
			message += clients.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getDescriptionAllProducts() {
		String message = "\n-------------------\n";
		message += "    Clients    \n";
		for (int c = 0; c < products.size(); c++) {
			message += products.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getDescriptionAllOrders() {
		String message = "\n-------------------\n";
		message += "    Orders    \n";
		for (int c = 0; c < orders.size(); c++) {
			message += orders.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String exportRestaurantData(String fileName) throws FileNotFoundException {
		String message = "Clients data saved successfully";
		PrintWriter pw = new PrintWriter("data/" + fileName + ".csv");

	    for(int i=0;i<restaurants.size();i++){
	      Restaurant myRestaurant = restaurants.get(i);
	      pw.println(myRestaurant.getName()+","+myRestaurant.getNit()+","+myRestaurant.getNameAdmin());
	    }

	    pw.close();
		return message;
	}
	
	public String saveData(int objectSave) throws IOException {
		String msg = "";
		try {
			ObjectOutputStream oos;
			switch (objectSave) {
				case 1:
					oos = new ObjectOutputStream(new FileOutputStream("data/restaurants.tr1"));
				    oos.writeObject(restaurants);
				    oos.close();
					break;
				case 2:
					oos = new ObjectOutputStream(new FileOutputStream("data/products.tr1"));
				    oos.writeObject(products);
				    oos.close();
					break;
				case 3:
					oos = new ObjectOutputStream(new FileOutputStream("data/clients.tr1"));
				    oos.writeObject(clients);
				    oos.close();
					break;
				case 4:
					oos = new ObjectOutputStream(new FileOutputStream("data/orders.tr1"));
				    oos.writeObject(orders);
				    oos.close();
					break;
			}
		} catch (IOException e) {
			msg = e.getMessage();
		}
		return msg;
	}

	public String loadData(int objectLoad) throws IOException, ClassNotFoundException {
		String msg = "";
		try {
			File file;
			switch (objectLoad) {
				case 1:
					file = new File("data/restaurants.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    restaurants = (List)ois.readObject();
					    ois.close();
					    msg = "Restaurants data has been loaded succesfully";
					}
					break;
				case 2:
					file = new File("data/clients.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    clients = (List)ois.readObject();
					    ois.close();
					    msg = "Clients data has been loaded succesfully";
					}
					break;
				case 3:
					file = new File("data/products.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    products = (List)ois.readObject();
					    ois.close();
					    msg = "Products data has been loaded succesfully";
					}
					break;
				case 4:
					file = new File("data/orders.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    orders = (List)ois.readObject();
					    ois.close();
					    msg = "Orders data has been loaded succesfully";
					}
					break;
			}
		} catch (IOException e) {
			msg = e.getMessage();
		} catch (ClassNotFoundException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
}
