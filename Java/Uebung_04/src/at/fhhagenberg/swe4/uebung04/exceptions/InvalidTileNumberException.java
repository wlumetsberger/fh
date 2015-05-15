package at.fhhagenberg.swe4.uebung04.exceptions;

public class InvalidTileNumberException extends BoardException {
	private static final long serialVersionUID = -6531568752782734945L;

	public InvalidTileNumberException(String message) {
		super(message);
	}
}
