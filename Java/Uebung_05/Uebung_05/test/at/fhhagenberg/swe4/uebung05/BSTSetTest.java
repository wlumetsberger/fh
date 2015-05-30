package at.fhhagenberg.swe4.uebung05;

import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Iterator;
import org.junit.Ignore;
import org.junit.Test;
import at.fhhagenberg.swe4.uebung05.collections.BSTSet;


public class BSTSetTest extends SortedTreeSetTestBase {
  
  @Override
  protected <T> BSTSet<T> createSet(Comparator<T> comparator) {  
    return new BSTSet<T>(comparator);
  }

}