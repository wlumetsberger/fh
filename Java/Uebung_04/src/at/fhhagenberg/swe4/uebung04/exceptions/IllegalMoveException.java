package at.fhhagenberg.swe4.uebung04.exceptions;

public class IllegalMoveException extends BoardException {

	private static final long serialVersionUID = -6369162959610231907L;

	public IllegalMoveException(String message) {
		super(message);
	}
}
