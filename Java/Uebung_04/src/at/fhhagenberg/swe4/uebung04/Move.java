package at.fhhagenberg.swe4.uebung04;

public class Move {
	private int row;
	private int col;

	// define an enum of possible Move directions
	public enum Directions {
		LEFT, RIGHT, UP, DOWN
	};

	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
