package db;

public class InvalidNumberOfArgsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidNumberOfArgsException() {
		super("Invalid number of arguments");
	}
}
