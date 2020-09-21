package CustomExceptions;

public class RestaurantDontExistException extends Exception {
 
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		String message = "A restaurant with this code dont exists\n";
		return message;
	}

}
