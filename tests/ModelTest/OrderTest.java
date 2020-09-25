package ModelTest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Model.Order;

class OrderTest {
	
	Order order;

	private void setupStage1() {
		order = new Order("1006055396", "1006");
	}
	
	@Test
	public void getStatusTest() {
		setupStage1();
		int res = order.getStatus();
		assertEquals("Status order don't match int", 1, res, 0);
	}
	
	@Test
	public void setStatusTest() {
		setupStage1();
		order.setStatus(4);
		assertEquals("Status order don't match int", "Delivered", order.getStatusString());
	}

}
