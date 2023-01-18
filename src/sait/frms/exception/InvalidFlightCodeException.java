package sait.frms.exception;

public class InvalidFlightCodeException extends Exception{
	
	private String wrongFlightCode;
	/**
	 * the InvalidFlightCodeException is used
	 * as an Exception to warn against invalid flight code 
	 * 
	 * @author Jeramie
	 */
	public InvalidFlightCodeException() {
		super("Invalid Code Pattern");
	}
	
	/**
	 * @author Jeramie
	 * @return String wrongFlightCode
	 */
	public String getWrongFlightCode() {
		return wrongFlightCode;
	}
}
