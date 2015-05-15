package at.fhhagenberg.swe4.uebung04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import at.fhhagenberg.swe4.uebung04.exceptions.IllegalMoveException;
import at.fhhagenberg.swe4.uebung04.exceptions.InvalidBoardIndexException;
import at.fhhagenberg.swe4.uebung04.exceptions.NoSolutionException;

public class SlidingPuzzle {

	/**
	 * Method to Solve a given Board
	 * 
	 * @param board
	 * @return
	 * @throws NoSolutionException
	 * @throws InvalidBoardIndexException
	 */
	public List<Move> solve(Board board) throws NoSolutionException,
			InvalidBoardIndexException {
		PriorityQueue<SearchNode> toProcess = new PriorityQueue<SearchNode>();
		HashSet<SearchNode> closedNodes = new HashSet<SearchNode>();
		SearchNode currentNode = new SearchNode(board);
		toProcess.add(currentNode);

		while (!toProcess.isEmpty()) {
			currentNode = toProcess.poll();
			List<SearchNode> possibleMoves = this.getPossibleMoves(currentNode);
			for (SearchNode possibleNode : possibleMoves) {
				// estimatedCostsToTarget == 0 --> solution
				if (possibleNode.estimatedCostsToTarget() == 0) {
					return possibleNode.toMoves();
				}

				if (!closedNodes.contains(possibleNode)) {
					if (possibleNode.estimatedTotalCosts() <= currentNode
							.estimatedTotalCosts()) {
						closedNodes.add(possibleNode);
						toProcess.remove(possibleNode);
					}
					toProcess.add(possibleNode);
				}

			}
			closedNodes.add(currentNode);
		}
		throw new NoSolutionException();
	}

	/**
	 * Return all Possible Moves for a given Node
	 * 
	 * @param node
	 * @return
	 * @throws InvalidBoardIndexException
	 */
	public List<SearchNode> getPossibleMoves(SearchNode node)
			throws InvalidBoardIndexException {
		List<SearchNode> moves = new ArrayList<SearchNode>();
		for (Move.Directions direction : Move.Directions.values()) {
			Board board = node.getBoard().copy();
			try {
				switch (direction) {
				case UP:
					board.moveUp();
					break;
				case LEFT:
					board.moveLeft();
					break;
				case RIGHT:
					board.moveRight();
					break;
				case DOWN:
					board.moveDown();
					break;
				}
				moves.add(new SearchNode(node, board,
						node.costsFromStart() + 1, new Move(board
								.getEmptyTileRow(), board.getEmptyTileColumn())));
			} catch (IllegalMoveException ex) {
				// nothing todo here

			}
		}
		return moves;
	}

	/**
	 * Do Moves on a given Board, print out the Board each Step
	 * 
	 * @param board
	 * @param moves
	 */
	public void printMoves(Board board, List<Move> moves) {
		for (Move m : moves) {
			try {
				board.move(m.getRow(), m.getCol());
			} catch (IllegalMoveException ex) {
				ex.printStackTrace();
			}
			System.out.println("Board: \n " + board);
		}

	}
}
