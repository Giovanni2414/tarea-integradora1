package CustomExceptions;

public class SameStatusException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The status order is the same than you try to change";
	}
	
}
