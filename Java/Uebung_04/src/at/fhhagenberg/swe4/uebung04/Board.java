package at.fhhagenberg.swe4.uebung04;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import at.fhhagenberg.swe4.uebung04.exceptions.IllegalMoveException;
import at.fhhagenberg.swe4.uebung04.exceptions.InvalidBoardIndexException;
import at.fhhagenberg.swe4.uebung04.exceptions.InvalidTileNumberException;

public class Board implements Comparable<Board> {

	private int field[][];
	private int size;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	public Board(int size) {
		this.size = size;
		this.field = new int[size][size];
		this.initializeBoard();
	}

	/**
	 * Fill a Board with Values The Board is Solved with the values filled in.
	 * To create a puzzled board do initializeBoard and than Shuffle them
	 */
	private void initializeBoard() {
		Integer count = 1;
		for (int i = 0; i < this.field.length; i++) {
			for (int j = 0; j < this.field.length; j++) {
				this.field[i][j] = count++;
			}
		}
		this.field[field.length - 1][field.length - 1] = 0;
	}

	/**
	 * Clones one Board
	 */
	@Override
	protected Object clone() {
		Board b = new Board(this.size);
		for (int i = 0; i < b.size; i++) {
			for (int j = 0; j < b.size; j++) {
				b.field[i][j] = this.field[i][j];
			}
		}
		return b;
	}

	/**
	 * Generate HashCode for Board
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(field);
		result = prime * result + size;
		return result;
	}

	/**
	 * checks if one Board equlas another Board
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		if (this.compareTo(other) != 0) {
			return false;
		}
		// it is important here to use deepEquals
		return Arrays.deepEquals(this.field, other.field);
	}

	/**
	 * Compare Size Component of Board <1 if this board is lower than others 0
	 * if both size is equal >1 if this board is greather than others
	 */
	@Override
	public int compareTo(Board o) {
		return this.size - o.size;
	}

	/**
	 * Writes the Board to String
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Board: \n");
		System.out.println("Board: \n");
		for (int[] row : this.field) {
			for (int itm : row) {
				builder.append(" | ");
				builder.append(itm);
			}
			builder.append(" | ");
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * Method to check the given row/col is available in the board
	 * 
	 * @param i
	 * @param j
	 * @throws InvalidBoardIndexException
	 */
	private void checkIndex(int i, int j) throws InvalidBoardIndexException {
		if (i < 0 || j < 0 || j >= size || i >= size) {
			throw new InvalidBoardIndexException("Index out of Range");
		}
	}

	/**
	 * Private helper which is used to correct the given Index e.g. row 1/1 is
	 * internally 0/0
	 * 
	 * @param idx
	 * @return
	 */
	private int correctIndex(int idx) {
		return idx - 1;
	}

	/**
	 * Return number of Tile to a given Position
	 * 
	 * @param i
	 * @param j
	 * @return
	 * @throws InvalidBoardIndexException
	 */
	public int getTile(int i, int j) throws InvalidBoardIndexException {
		this.checkIndex(this.correctIndex(i), this.correctIndex(j));
		return this.field[this.correctIndex(i)][this.correctIndex(j)];

	}

	/**
	 * set Tile with given Number to given Position
	 * 
	 * @param i
	 * @param j
	 * @param number
	 * @throws InvalidBoardIndexException
	 * @throws InvalidTileNumberException
	 */
	public void setTile(int i, int j, int number)
			throws InvalidBoardIndexException, InvalidTileNumberException {
		if (number < 0 || number > size * size) {
			throw new InvalidTileNumberException("Number is not valid");
		}
		this.checkIndex(this.correctIndex(i), this.correctIndex(j));
		this.field[this.correctIndex(i)][this.correctIndex(j)] = number;

	}

	/**
	 * Sets Empty Tile to given Possition
	 * 
	 * @param i
	 * @param j
	 * @throws InvalidBoardIndexException
	 * @throws InvalidTileNumberException
	 */
	public void setEmptyTile(int i, int j) throws InvalidBoardIndexException,
			InvalidTileNumberException {
		this.setTile(i, j, 0);
	}

	/**
	 * Returns Row where Tile is Empty
	 * 
	 * @return
	 */
	public int getEmptyTileRow() {
		for (int i = 0; i < this.field.length; i++) {
			for (int j = 0; j < this.field.length; j++) {
				if (this.field[i][j] == 0) {
					return i + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Returns Column where Tile is Empty
	 * 
	 * @return
	 */
	public int getEmptyTileColumn() {
		for (int i = 0; i < this.field.length; i++) {
			for (int j = 0; j < this.field.length; j++) {
				if (this.field[i][j] == 0) {
					return j + 1;
				}
			}
		}
		return 0;
	}

	/**
	 * Check if current Board is Valid
	 * 
	 * @return
	 */
	public boolean isValid() {
		for (int k = 0; k < (size * size); k++) {
			boolean found = false;
			for (int i = 0; i < this.field.length && !found; i++) {
				for (int j = 0; j < this.field.length; j++) {
					if (this.field[i][j] == k) {
						found = true;
						break;
					}
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Do a DeepCopy of the current Board returns a clone of our Board
	 * 
	 * @return
	 */
	public Board copy() {
		return (Board) this.clone();
	}

	/**
	 * Take the generated Board, and do a random amount of possible Moves, to
	 * generate a random solvable board
	 */
	public void shuffle() {
		Board original = this.copy();
		Random generator = new Random();
		for (int i = 0; i < generator.nextInt(100); i++) {
			try {
				switch (generator.nextInt(4)) {
				case 0:
					moveUp();
					break;
				case 1:
					moveLeft();
					break;
				case 2:
					moveRight();
					break;
				case 3:
					moveDown();
					break;
				}
			} catch (IllegalMoveException ex) {
				// nothing todo here
			}
		}
		while (original == this) {
			this.shuffle();
		}
	}

	/**
	 * Moves empty Taile to given row/col
	 * 
	 * @param row
	 * @param col
	 * @throws IllegalMoveException
	 */
	public void move(int row, int col) throws IllegalMoveException {
		try {
			this.checkIndex(this.correctIndex(row), this.correctIndex(col));

			int curRow = (this.getEmptyTileRow());
			int curCol = (this.getEmptyTileColumn());

			int tile = this.getTile(row, col);
			this.setEmptyTile(row, col);
			this.setTile(curRow, curCol, tile);

		} catch (InvalidBoardIndexException | InvalidTileNumberException ex) {
			throw new IllegalMoveException("Cannot Move to Position: " + row
					+ "," + col);
		}
	}

	/**
	 * move empty Tile Left
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveLeft() throws IllegalMoveException {
		this.move(this.getEmptyTileRow(), this.getEmptyTileColumn() - 1);
	}

	/**
	 * move empty Tile right
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveRight() throws IllegalMoveException {
		this.move(this.getEmptyTileRow(), this.getEmptyTileColumn() + 1);
	}

	/**
	 * move empty Tile down
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveDown() throws IllegalMoveException {
		this.move(this.getEmptyTileRow() + 1, this.getEmptyTileColumn());
	}

	/**
	 * move empty Tile up
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveUp() throws IllegalMoveException {
		this.move(this.getEmptyTileRow() - 1, this.getEmptyTileColumn());
	}

	/**
	 * execute a set of Moves
	 * 
	 * @param moves
	 * @throws IllegalMoveException
	 */
	public void makeMoves(List<Move> moves) throws IllegalMoveException {
		for (Move m : moves) {
			this.move(m.getRow(), m.getCol());
		}
	}

	public int[][] getField() {
		return field;
	}

	public void setField(int[][] field) {
		this.field = field;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
