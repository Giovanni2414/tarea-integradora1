package CustomExceptions;

public class InvalidOptionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Error, you has entered an invalid option\n";
	}
	
}
