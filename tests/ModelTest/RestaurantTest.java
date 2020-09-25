package ModelTest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Model.Restaurant;

class RestaurantTest {

	Restaurant restaurant;
	
	private void setupStage1() {
		restaurant = new Restaurant("McDonalds", "Giovanni Mosquera", "94503");
	}
	
	@Test
	public void setNameTest() {
		setupStage1();
		restaurant.setName("CheeseMcBurger");
		assertEquals("Names don't match", "CheeseMcBurger", restaurant.getName());
	}
	
	@Test
	public void getNitTest() {
		setupStage1();
		assertEquals("Nit's don't match", "94503", restaurant.getNit());
	}

}
