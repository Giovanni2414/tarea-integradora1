package Ui;

import java.io.IOException;

import CustomExceptions.ClientDontExistException;
import CustomExceptions.InvalidOptionException;
import CustomExceptions.OrderDontExistException;
import CustomExceptions.ProductDontExistException;
import CustomExceptions.RestaurantDontExistException;

public class Main {
	
	public static void main (String[]args) throws NumberFormatException, IOException, InvalidOptionException, RestaurantDontExistException, ClientDontExistException, ClassNotFoundException, ProductDontExistException, OrderDontExistException {
		Menu m = new Menu();
		m.startMenu();
	}
	
}
