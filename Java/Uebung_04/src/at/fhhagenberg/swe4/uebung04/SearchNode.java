package at.fhhagenberg.swe4.uebung04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.fhhagenberg.swe4.uebung04.exceptions.InvalidBoardIndexException;

public class SearchNode implements Comparable<SearchNode> {

	private SearchNode predecessor;
	private Board board;
	private int manhattenDistance;
	private int costsFromStart;
	private Move move;

	/**
	 * Constructor
	 * 
	 * @param board
	 * @throws InvalidBoardIndexException
	 */
	public SearchNode(Board board) throws InvalidBoardIndexException {
		this.board = board;
		manhattenDistance = this.calculateManhattanDistance();
	}

	/**
	 * Constructor fully
	 * 
	 * @param predecessor
	 * @param board
	 * @param costsFormStart
	 * @param move
	 * @throws InvalidBoardIndexException
	 */
	public SearchNode(SearchNode predecessor, Board board, int costsFormStart,
			Move move) throws InvalidBoardIndexException {
		this.predecessor = predecessor;
		this.board = board;
		this.costsFromStart = costsFormStart;
		this.move = move;
		manhattenDistance = this.calculateManhattanDistance();
	}

	/**
	 * Returns a list of Moves form last move to first move
	 * 
	 * @return
	 */
	public List<Move> toMoves() {
		List<Move> moves = new ArrayList<>();
		SearchNode currentNode = this;
		while (currentNode != null) {
			if (currentNode.getMove() != null) {
				moves.add(currentNode.getMove());
			}
			currentNode = currentNode.getPredecessor();
		}
		Collections.reverse(moves);
		return moves;
	}

	/**
	 * Calculate the ManhattanDistance of given Board
	 * 
	 * @return
	 * @throws InvalidBoardIndexException
	 */
	private int calculateManhattanDistance() throws InvalidBoardIndexException {
		int manhattanDistanceSum = 0;
		for (int x = 1; x <= board.getSize(); x++) {
			for (int y = 1; y <= board.getSize(); y++) {
				int value = board.getTile(x, y);
				if (value != 0) {
					int targetX = (value - 1) / board.getSize();
					int targetY = (value - 1) % board.getSize();
					int dx = x - (targetX + 1);
					int dy = y - (targetY + 1);
					manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
				}
			}
		}
		return manhattanDistanceSum;

	}

	/**
	 * Checks if two SearchNodes are Equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof SearchNode))
			return false;
		SearchNode node = (SearchNode) obj;
		if (this.board == null && node.board != null)
			return false;
		return this.board.equals(node.board);
	}

	/**
	 * Compares two SearchNodes They are Equal if estimatedTotalCosts are
	 */
	@Override
	public int compareTo(SearchNode o) {
		return estimatedTotalCosts() - o.estimatedTotalCosts();
	}

	/**
	 * Calculate the HashCode for current instance
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		return result;
	}

	/**
	 * Returns the mahattenDistance or estimatedCostToTarget
	 * 
	 * @return
	 */
	public int estimatedCostsToTarget() {
		return this.manhattenDistance;
	}

	/**
	 * Returns estimatedCostsToTarget + costsFromStart
	 * 
	 * @return
	 */
	public int estimatedTotalCosts() {
		return this.costsFromStart + this.estimatedCostsToTarget();
	}

	public Board getBoard() {
		return this.board;
	}

	public SearchNode getPredecessor() {
		return this.predecessor;
	}

	public void setPredecessor(SearchNode predecessor) {
		this.predecessor = predecessor;
	}

	public int costsFromStart() {
		return this.costsFromStart;
	}

	public void setCostsFromStart(int costsFromStart) {
		this.costsFromStart = costsFromStart;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public Move getMove() {
		return move;
	}
}
