package ModelTest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Model.Client;

class ClientTest {

	Client client;
	
	private void setupStage1() {
		client = new Client(3, "1006055396", "Giovanni Mosquera", "3225034089", "Cra 1C 2 #60-18 ");
	}
	
	@Test
	public void getIdentificationTypeForExportTest() {
		setupStage1();
		int res = client.getIdentificationTypeForExport();
		assertEquals("Types of indentities don't match", 3, res, 0);
	}
	
	@Test
	public void setIdentificationNumberTest() {
		setupStage1();
		client.setIdentificationNumber("94311890");
		assertEquals("Types of number indentities don't match", "94311890", client.getIdentificationNumber());
	}

}
