package CustomExceptions;

public class ClientDontExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		String message = "A client with this code dont exists\n";
		return message;
	}
	
}
