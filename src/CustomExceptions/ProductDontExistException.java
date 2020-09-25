package CustomExceptions;

public class ProductDontExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "A product with this code don't exists";
	}

}
