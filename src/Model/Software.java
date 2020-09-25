package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import CustomExceptions.InvalidChangeStatusException;
import CustomExceptions.RestaurantDontExistException;
import CustomExceptions.SameStatusException;
import CustomExceptions.InvalidOptionException;
import CustomExceptions.OrderDontExistException;
import CustomExceptions.ProductDontExistException;

public class Software {
	
	public List<Restaurant> restaurants;
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
	
	public String editProductData(String code, int option, String data) throws ProductDontExistException, RestaurantDontExistException {
		String msg = "Product data has been updated";
		try {
			verifyProduct(code);
			for (int c = 0; c < products.size(); c++) {
				if ((products.get(c).getCode()).equals(code)) {
					switch (option) {
						case 1: products.get(c).setCode(data); break;
						case 2: products.get(c).setName(data); break;
						case 3: products.get(c).setDescription(data); break;
						case 4: products.get(c).setPrice(Double.parseDouble(data)); break;
						case 5: verifyRestaurant(data); products.get(c).setRestaurantNit(data); break;
					}
				}
			}
		} catch (ProductDontExistException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public void bubbleSortClients() {
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
	
	public String editClientData(String code, int option, String data) {
		String msg = "Client data has been updated";
		try {
			verifyClient(code);
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
					bubbleSortClients();
					break;
				}
			}
		} catch (ClientDontExistException e) {
			msg = e.getMessage();
		}
		
		return msg;
	}
	
	public String editOrder(String code, int option, String data) throws ClientDontExistException, NumberFormatException, InvalidOptionException {
		String msg = "";
		try {
			verifyOrder(code);
			for (int c = 0; c < orders.size(); c++) {
				if ((orders.get(c).getCode()).equals(code)) {
					switch (option) {
						case 1:
							verifyClient(data);
							orders.get(c).setClientCode(data);
							break;
						case 2:
							int compareStatus = orders.get(c).getStatus();
							confirmStatusChange(Integer.parseInt(data), compareStatus);
							orders.get(c).setStatus(Integer.parseInt(data));
							break;
					}
				}
			}
		} catch (OrderDontExistException e) {
			msg = e.getMessage();
		} catch (InvalidChangeStatusException e) {
			msg = e.getMessage();
		} catch (SameStatusException e) {
			msg = e.getMessage();
		} catch (ClientDontExistException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public void confirmStatusChange(int c1, int c2) throws SameStatusException, InvalidChangeStatusException, InvalidOptionException {
		if (c1 < 1 || c1 > 4) {
			throw new InvalidOptionException();
		}
		if (c1 < c2) {
			throw new InvalidChangeStatusException();
		} else if (c1 == c2) {
			throw new SameStatusException();
		}
	}
	
	public String addProductToListOrder(String cr, String cp, String orderC, int quantity) {
		String message = "The product can't be added at the order, the code of the restaurant or product is bad\n";
		for (int i = 0; i < products.size(); i++) {
			if ((products.get(i).getCode()).equalsIgnoreCase(cp) && (products.get(i).getRestaurantNit()).equalsIgnoreCase(cr)) {
				for (int c = 0; c < orders.size(); c++) {
					if (orderC.equalsIgnoreCase(orders.get(c).getCode())) {
						orders.get(c).addProduct(cp);
						orders.get(c).addQuantity(quantity);
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
	
	public void verifyOrder(String code) throws OrderDontExistException {
		boolean finded = false;
		for (int c = 0; c < orders.size() && !finded; c++) {
			if ((orders.get(c).getCode()).equalsIgnoreCase(code)) {
				finded = true;
			}
		}
		if (!finded) {
			throw new OrderDontExistException();
		}
	}
	
	public void verifyProduct(String code) throws ProductDontExistException {
		boolean finded = false;
		for (int c = 0; c < products.size() && !finded; c++) {
			if ((products.get(c).getCode()).equalsIgnoreCase(code)) {
				finded = true;
			}
		}
		if (!finded) {
			throw new ProductDontExistException();
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
		message += "    Products    \n";
		for (int c = 0; c < products.size(); c++) {
			message += products.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getDescriptionSelectedProducts(String restaurantNit) {
		String message = "\n-------------------\n";
		message += "    Products    \n";
		for (int c = 0; c < products.size(); c++) {
			if ((products.get(c).getRestaurantNit()).equals(restaurantNit)) {
				message += products.get(c);
			}
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
				    msg = "Restaurants data saved successfully\n";
					break;
				case 2:
					oos = new ObjectOutputStream(new FileOutputStream("data/products.tr1"));
				    oos.writeObject(products);
				    oos.close();
				    msg = "Products data saved successfully\n";
					break;
				case 3:
					oos = new ObjectOutputStream(new FileOutputStream("data/clients.tr1"));
				    oos.writeObject(clients);
				    msg = "Clients data saved successfully\n";
				    oos.close();
					break;
				case 4:
					oos = new ObjectOutputStream(new FileOutputStream("data/orders.tr1"));
				    oos.writeObject(orders);
				    msg = "Orders data saved successfully\n";
				    oos.close();
					break;
			}
		} catch (IOException e) {
			msg = e.getMessage();
		}
		return msg;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
					    msg = "Restaurants data has been loaded succesfully\n";
					}  else {
						msg = "Restaurants can't be loaded, file with restaurants don't exists\n";
					}
					break;
				case 2:
					file = new File("data/clients.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    clients = (List)ois.readObject();
					    ois.close();
					    msg = "Clients data has been loaded succesfully\n";
					}  else {
						msg = "Clients can't be loaded, file with clients don't exists\n";
					}
					break;
				case 3:
					file = new File("data/products.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    products = (List)ois.readObject();
					    ois.close();
					    msg = "Products data has been loaded succesfully\n";
					}  else {
						msg = "Products can't be loaded, file with products don't exists\n";
					}
					break;
				case 4:
					file = new File("data/orders.tr1");
					if(file.exists()){
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					    orders = (List) ois.readObject();
					    ois.close();
					    msg = "Orders data has been loaded succesfully\n";
					}  else {
						msg = "Orders can't be loaded, file with orders don't exists\n";
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
	
	public String importDataFromCSV(String fileName, int classImport) throws IOException, NumberFormatException, InvalidOptionException {
		String msg = "Data imported successfully";
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/" + fileName + ".csv"));
			String line = br.readLine();
			switch (classImport) {
				case 1:
					while(line!=null){
					      String[] parts = line.split(",");
					      addRestaurant(parts[0], parts[1], parts[2]);
					      line = br.readLine();
					}
					break;
				case 2:
					while(line!=null){
					      String[] parts = line.split(",");
					      addClient(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
					      line = br.readLine();
					}
					break;
				case 3:
					while(line!=null){
					      String[] parts = line.split(",");
					      addProduct(parts[2], parts[0], parts[3], Double.parseDouble(parts[1]), parts[4]);
					      line = br.readLine();
					}
					break;
			}
			br.close();
		} catch (IOException e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public String getSortedRestaurants() {
		List<Restaurant> arrayCopy = new ArrayList<>();
		for (int c = 0; c < restaurants.size(); c++) {
			arrayCopy.add(restaurants.get(c));
		}
		Collections.sort(arrayCopy);
		String message = "\n-------------------\n";
		message += "    Restaurants    \n";
		for (int c = 0; c < arrayCopy.size(); c++) {
			message += arrayCopy.get(c);
		}
		message += "-------------------\n";
		return message;
	}
	
	public String getSortedClients() {
		List<Client> arrayCopy = new ArrayList<>();
		for (int c = 0; c < clients.size(); c++) {
			arrayCopy.add(clients.get(c));
		}
		numberClientComparator c = new numberClientComparator();
		Collections.sort(arrayCopy, c);
		String message = "\n-------------------\n";
		message += "    Clients    \n";
		for (int c1 = 0; c1 < arrayCopy.size(); c1++) {
			message += arrayCopy.get(c1);
		}
		message += "-------------------\n";
		return message;
	}
	
	public class objectReportCSV implements Comparable<objectReportCSV> {
		
		String name;
		String nameadmin;
		String rnit;
		String cname;
		String cphone;
		String caddress;
		String ciden;
		String cidennumber;
		String orderProducts;
		String date;
		
		public objectReportCSV (String name, String nameadmin, String rnit, String cname, String cphone, String caddress, String ciden, String cidennumber, String orderProducts, String date) {
			this.name = name;
			this.nameadmin = nameadmin;
			this.rnit = rnit;
			this.cname = cname;
			this.cphone = cphone;
			this.caddress = caddress;
			this.ciden = ciden;
			this.cidennumber = cidennumber;
			this.orderProducts = orderProducts;
			this.date = date;
		}

		@Override
		public int compareTo(objectReportCSV b1) {
			int res = 0;
			if (rnit.compareToIgnoreCase((b1.rnit)) != 0) {
				res = rnit.compareToIgnoreCase((b1.rnit));
			} else if (rnit.compareToIgnoreCase((b1.rnit)) != 0) {
				if (rnit.compareToIgnoreCase((b1.rnit)) > 1) {
					res = -1;
				} else {
					res = 1;
				}
			}
			return res;
		}
		
	}
	
	public String createReportCSVOrders(String fileName, String separator) throws FileNotFoundException {
		String message = "Report was created successfully in path reports/" + fileName + ".csv";
		ArrayList<objectReportCSV> objectReport = new ArrayList<>();
		try {
			PrintWriter pw = new PrintWriter("reports/" + fileName + ".csv");
		    for(int i=0;i<orders.size();i++){
		      Order myOrder = orders.get(i);
		      Restaurant myRestaurant = null;
		      Client myClient = null;
		      for (int c = 0; c < restaurants.size(); c++) {
		    	  if ((restaurants.get(c).getNit()).equals(myOrder.getRestaurantNit())) {
		    		  myRestaurant = restaurants.get(c);
		    	  }
		      }
		      for (int c = 0; c < clients.size(); c++) {
		    	  if ((clients.get(c).getIdentificationNumber()).equals(myOrder.getClientCode())) {
		    		  myClient = clients.get(c);
		    	  }
		      }
		      ArrayList<String> myOrder2 = myOrder.getProductList();
		      ArrayList<Integer> myOrderQuantities = myOrder.getQuantitiesList();
		      String orderProducts = "(";
		      for (int b = 0; b < myOrder2.size(); b++) {
		    	  for (int v = 0; v < products.size(); v++) {
		    		  if ((myOrder2.get(b)).equals(products.get(v).getCode())) {
		    			  orderProducts += (products.get(v).getName()) + " x " + myOrderQuantities.get(b) + ":";
		    		  }
		    	  }
		      }
		      orderProducts += ")";
		      objectReport.add(new objectReportCSV(myRestaurant.getName(), myRestaurant.getNameAdmin(),myRestaurant.getNit(),myClient.getName(),myClient.getPhone(),myClient.getAddress(),myClient.getIdentificationType(),myClient.getIdentificationNumber(), orderProducts, myOrder.getDateOrder()));
		    }
		    Collections.sort(objectReport);
		    pw.println("Name Restaurant" + separator + "Name restaurant admin" + separator + "Restaurant nit" + separator + "Client name" + separator + "Client phone" + separator + "Client address" + separator + "Client type identification" + separator + "Client identification number" + separator + "Product names x quantity" + separator + "Date order");
		    for (int c = 0; c < objectReport.size(); c++) {
		    	pw.println(objectReport.get(c).name + separator + objectReport.get(c).nameadmin + separator + objectReport.get(c).rnit + separator + objectReport.get(c).cname + separator + objectReport.get(c).cphone + separator + objectReport.get(c).caddress + separator + objectReport.get(c).ciden + separator + objectReport.get(c).cidennumber + separator + objectReport.get(c).orderProducts + separator + objectReport.get(c).date);
		    }
		    pw.close();
		} catch (FileNotFoundException e) {
			message = e.getMessage();
		}
		return message;
	}
	
}
