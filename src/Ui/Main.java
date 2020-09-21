package Ui;

import java.io.IOException;

import CustomExceptions.InvalidOptionException;

public class Main {
	
	public static void main (String[]args) throws NumberFormatException, IOException, InvalidOptionException {
		Menu m = new Menu();
		m.startMenu();
	}
	
}
