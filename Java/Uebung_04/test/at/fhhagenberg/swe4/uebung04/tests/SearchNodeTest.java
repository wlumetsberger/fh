package at.fhhagenberg.swe4.uebung04.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fhhagenberg.swe4.uebung04.Board;
import at.fhhagenberg.swe4.uebung04.SearchNode;
import at.fhhagenberg.swe4.uebung04.exceptions.BoardException;


public class SearchNodeTest extends BaseTest{
	
	@Test
	public void equalsSearchNode(){
		SearchNode a = new SearchNode(this.get3x3Board());
		SearchNode b = new SearchNode(this.get3x3Board());
		assertEquals(a,b);
	}
	@Test
	public void equalsCompareToNode(){
		SearchNode a = new SearchNode(this.get3x3Board());
		SearchNode b = new SearchNode(this.get3x3Board());
		assertTrue(a.compareTo(b) == 0);
	}
	
	@Test
	public void simpleNodeTest() {
		try {
			Board board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 0);
			SearchNode node = new SearchNode(board);
			assertEquals(0, node.estimatedCostsToTarget());

			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 0);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 5);
			node = new SearchNode(board);
			assertEquals(2, node.estimatedCostsToTarget());

			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 0);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 2);
			node = new SearchNode(board);
			assertEquals(3, node.estimatedCostsToTarget());
		} catch (BoardException e) {
			fail("Unexpeced BoardException.");
		}
	}

}
