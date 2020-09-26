package ModelTest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import CustomExceptions.InvalidOptionException;
import Model.Software;

class SoftwareTest {
	
	Software software;
	
	private void setupStage1() {
		software = new Software();
	}
	
	private void setupStage2() throws InvalidOptionException {
		software = new Software();
		software.addClient(1, "1006055396", "Giovanni Mosquera", "4880416", "Cra 1C 2 #60.18");
	}

	@Test
	public void addRestaurantTest() {
		setupStage1();
		software.addRestaurant("McDonalds", "94503", "Giovanni Mosquera");
		assertEquals("New restaurant didn't added", 1, software.restaurants.size());
	}
	
	@Test
	public void editClientDataTest() throws InvalidOptionException, FileNotFoundException, IOException {
		setupStage2();
		String rs = software.editClientData("1006055396", 2, "Synyster Gates");
		assertEquals("Edit client data code don't works", "Client data has been updated", rs);
	}

}
