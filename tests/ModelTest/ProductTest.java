package ModelTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Model.Product;

class ProductTest {
	
	Product product;
	
	private void setupStage1() {
		product = new Product("1001", "McBurger", "Burgen with extra cheese", 12000, "94503");
	}

	@Test
	public void setNameTest() {
		setupStage1();
		product.setName("McCheese");
		assertEquals("Names don't match", "McCheese", product.getName());
	}
	
	@Test
	public void getCodeTest() {
		setupStage1();
		assertEquals("Codes don't match", "1001", product.getCode());
	}

}
