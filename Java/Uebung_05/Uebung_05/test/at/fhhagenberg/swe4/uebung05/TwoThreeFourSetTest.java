package at.fhhagenberg.swe4.uebung05;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

import at.fhhagenberg.swe4.uebung05.collections.TwoThreeFourTreeNode;
import at.fhhagenberg.swe4.uebung05.collections.TwoThreeFourTreeSet;
import at.fhhagenberg.swe4.uebung05.interfaces.SortedSet;
import at.fhhagenberg.swe4.uebung05.interfaces.SortedTreeSet;


public class TwoThreeFourSetTest extends SortedTreeSetTestBase {
  
  @Override
  protected <T> TwoThreeFourTreeSet<T> createSet(Comparator<T> comparator) {  
    return new TwoThreeFourTreeSet<T>(comparator);
  }

  @Test
  public void testHeight() {
    final int NELEMS = 10000;
    SortedTreeSet<Integer> set = createSet();

    for (int i=1; i<=NELEMS; i++) {
      set.add(i);
      int h = set.height();
      int n = set.size();
      assertTrue("height(set) <= ld(size(set))+1", h < Math.log((double)n)/Math.log(2.0)+1);
    }
  }
  
  @Test
  public void testFixHeight(){
	  SortedTreeSet<String> set1 = createSet();
	  set1.add("Test1");
	  set1.add("Test2");
	  set1.add("Test3");
	  
	  assertTrue(set1.height() == 0);
	  set1.add("Test4");
	  assertTrue(set1.height() == 1);
  }
  
  @Test
  public void testGetFirstChildWithoutChild(){
	  TwoThreeFourTreeNode<Integer> node = new TwoThreeFourTreeNode<Integer>();
	  node.addItem(3);
	  node.addItem(2);
	  node.addItem(1);
	  
	 assertTrue(node.getFirstChild() == null);
  }
  @Test
  public void testGetLastChildWithoutChild(){
	  TwoThreeFourTreeNode<Integer> node = new TwoThreeFourTreeNode<Integer>();
	  node.addItem(3);
	  node.addItem(2);
	  node.addItem(1);
	  
	 assertTrue(node.getLastChild() == null);
  }
  @Test
  public void testAddItem(){
	  TwoThreeFourTreeNode<Integer> node = new TwoThreeFourTreeNode<Integer>();
	  node.addItem(3);
	  node.addItem(2);
	  node.addItem(1);
	 assertTrue(node.getItem(0).equals(1));
  }
  @Test
  public void testGetItemIndex(){
	  TwoThreeFourTreeNode<Integer> node = new TwoThreeFourTreeNode<Integer>();
	  node.addItem(3);
	  node.addItem(2);
	  node.addItem(1);
	 assertTrue(node.getItem(new Integer(3)).equals(3));
  }
}
