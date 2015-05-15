package at.fhhagenberg.swe4.uebung04.tests;

import at.fhhagenberg.swe4.uebung04.Board;

public abstract class BaseTest {

	/**
	 * Test Helper returns a 3x3 Board looks like
	 * 1 2 3
	 * 4 5 6
	 * 0 7 8
	 * @return
	 */
	protected Board get3x3Board(){
		Board board =  new Board(3);
		board.setTile(1, 1, 1);
		board.setTile(1, 2, 2);
		board.setTile(1, 3, 3);
		board.setTile(2, 1, 4);
		board.setTile(2, 2, 5);
		board.setTile(2, 3, 6);
		board.setTile(3, 1, 0);
		board.setTile(3, 2, 7);
		board.setTile(3, 3, 8);
		return board;
	}
	
	
}
