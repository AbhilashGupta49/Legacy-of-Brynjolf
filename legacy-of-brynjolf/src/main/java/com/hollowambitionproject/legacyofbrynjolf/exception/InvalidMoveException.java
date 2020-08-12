package com.hollowambitionproject.legacyofbrynjolf.exception;

/**
 * The Class InvalidMoveException. Thrown when an invalid move is detected in
 * the user input.
 */
public class InvalidMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6026655549085826942L;

	/**
	 * Instantiates a new invalid move exception.
	 *
	 * @param message the message
	 */
	public InvalidMoveException(String message) {
		super(message);
	}

}
