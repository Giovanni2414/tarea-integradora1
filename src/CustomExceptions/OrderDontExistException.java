package CustomExceptions;

public class OrderDontExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "An order with this code don't exits";
	}

}
