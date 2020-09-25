package CustomExceptions;

public class InvalidChangeStatusException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "You can't put a stade of order more less than the actual";
	}
	
}
