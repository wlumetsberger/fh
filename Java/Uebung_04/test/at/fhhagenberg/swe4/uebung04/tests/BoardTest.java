package at.fhhagenberg.swe4.uebung04.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fhhagenberg.swe4.uebung04.Board;
import at.fhhagenberg.swe4.uebung04.exceptions.BoardException;
import at.fhhagenberg.swe4.uebung04.exceptions.IllegalMoveException;

public class BoardTest extends BaseTest {
	
	@Test
	public void testCloneAndCopy(){
		Board a = this.get3x3Board();
		Board b = a.copy();
		assertTrue(a.equals(b));
		// changing values of a should have no effect for b
		// due to deep copy
		a.moveUp();
		assertFalse(a.equals(b));
	}

	@Test(expected = IllegalMoveException.class)
	public void moveWrongIndexes(){
		Board a = this.get3x3Board();
		a.move(0, 0);
	}
	@Test
	public void moveCorrectIndexes(){
		Board a = this.get3x3Board();
		a.move(1, 1);
	}
	
	@Test(expected = IllegalMoveException.class)
	public void moveDownOnBottom(){
		Board a = this.get3x3Board();
		a.moveDown();
	}
	
	@Test(expected = IllegalMoveException.class)
	public void moveLeftOnLeft(){
		Board a = this.get3x3Board();
		a.moveLeft();
	}
	
	@Test(expected = IllegalMoveException.class)
	public void moveRightOnRight(){
		Board a = this.get3x3Board();
		a.moveRight();
		a.moveRight();
		a.moveRight();
		a.moveRight();
	}
	
	@Test(expected = IllegalMoveException.class)
	public void moveUpOnTop(){
		Board a = this.get3x3Board();
		a.moveUp();
		a.moveUp();
		a.moveUp();
		a.moveUp();
	}
	
	@Test
	public void doCorrectMoveOperations(){
		Board a = this.get3x3Board();
		a.moveRight();
		a.moveUp();
		a.moveDown();
		a.moveLeft();
	}
	
	@Test
	public void getEmptyTileTest(){
		Board a = this.get3x3Board();
		assertEquals(1,a.getEmptyTileColumn());
		assertEquals(3,a.getEmptyTileRow());
	}

	@Test
	public void setPossibleEmptyTileTest(){
		Board a = this.get3x3Board();
		a.setEmptyTile(1, 1);
	}
	
	@Test(expected = BoardException.class)
	public void setImpossibleEmptyTileTest(){
		Board a = this.get3x3Board();
		a.setEmptyTile(0, 0);
	}
	
	@Test
	public void shuffleTest(){
		Board a = this.get3x3Board();
		a.shuffle();
		assertNotEquals(this.get3x3Board(), a);
	}

	@Test
	public void simpleIsValidTest() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 0);

			assertTrue(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 1);
			board.setTile(3, 3, 0);

			assertTrue(!board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest2() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 8);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 0);
			board.setTile(2, 1, 7);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 4);
			board.setTile(3, 1, 3);
			board.setTile(3, 2, 1);
			board.setTile(3, 3, 6);

			assertTrue(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

}
