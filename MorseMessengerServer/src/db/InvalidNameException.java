package db;

/**
 * @author Ghennadi Procopciuc
 */

public class InvalidNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidNameException(String message) {
		super(message);
	}
}
