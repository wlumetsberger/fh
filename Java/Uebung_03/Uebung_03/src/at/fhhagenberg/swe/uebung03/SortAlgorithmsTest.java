package at.fhhagenberg.swe.uebung03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SortAlgorithmsTest {
	
	/**
	 * Test HeapSort for 5 Values
	 */
	@Test
	public void testHeapSort(){
		SortAlgorithms algo = new SortAlgorithms();
		int[]values = new int[5];
		values[0] = 5;
		values[1] = 1;
		values[2] = 3;
		values[3] = 4;
		values[4] = 2;
		algo.setValues(values);
		algo.heapSort();
		int[] expectedValues = new int[5];
		for(int i=0; i<expectedValues.length; i++){
			expectedValues[i] = i+1;
		}
		assert(Arrays.equals(expectedValues, algo.getValues()));
	}
	
	/**
	 * Test QuickSort for 5 values
	 */
	@Test
	public void testQuickSort(){
		SortAlgorithms algo = new SortAlgorithms();
		int[]values = new int[5];
		values[0] = 5;
		values[1] = 1;
		values[2] = 3;
		values[3] = 4;
		values[4] = 2;
		algo.setValues(values);
		algo.quickSort();
		int[] expectedValues = new int[5];
		for(int i=0; i<expectedValues.length; i++){
			expectedValues[i] = i+1;
		}
		assert(Arrays.equals(expectedValues, algo.getValues()));
	}
	/**
	 * Test QuickSort without Setting an Array
	 */
	@Test
	public void testQuickSortWithEmptyArray(){
		SortAlgorithms algo = new SortAlgorithms();
		algo.quickSort();
	}
	/**
	 * Test HeapSort wihtout Setting an Array
	 */
	@Test
	public void testHeapSortWithEmptyArray(){
		SortAlgorithms algo = new SortAlgorithms();
		algo.heapSort();
	}

	/**
	 * Run Test with 5000 random Numbers and print statistic
	 */
	@Test
	public void testRandomHeapSort(){
		System.out.println("HeapSort With 5000 No");
		SortAlgorithms algo = new SortAlgorithms();
		algo.generateRandomValues(5000);
		algo.quickSort();
		System.out.println(algo.getStatistic());
	}
	/**
	 * Run Test with 5000 random Numbers and print statisitc
	 */
	@Test
	public void testRandomQuickSort(){
		System.out.println("QuickSort With 5000 No");
		SortAlgorithms algo = new SortAlgorithms();
		algo.generateRandomValues(5000);
		algo.heapSort();
		System.out.println(algo.getStatistic());
	}
	/**
	 * Run a competition between Quick and HeapSort
	 * Which Sort has less Compares / Swaps 
	 * The Sort is Processed 100 times with 50000 numbers
	 */
	@Test
	public void runCompetitionHeapQuickSort(){
		System.out.println("COMPETITION");
		SortAlgorithms algorithms = new SortAlgorithms();
		List<SortStatistics> statisticsQuick = new ArrayList<SortStatistics>();
		List<SortStatistics> statisticsHeap = new ArrayList<SortStatistics>();
		for(int i=0; i<10; i++){
			algorithms.generateRandomValues(50000);
			algorithms.heapSort();
			statisticsHeap.add(algorithms.getStatistic());
			algorithms.generateRandomValues(50000);
			algorithms.quickSort();
			statisticsQuick.add(algorithms.getStatistic());
		}
		System.out.println("QuickSort Summary:");
		long duration = 0;
		int swaps = 0 ;
		int compares= 0;
		int recalls = 0;
		for(SortStatistics s : statisticsQuick){
			duration += s.duration();
			swaps += s.getSwaps();
			compares += s.getCompares();
			recalls += s.getRecCalls();
		}
		System.out.println("Duration: "+ duration + " , Swaps: "+ swaps + " , Compares: "+ compares + " , ReCalls: "+ recalls );
		duration = 0;
		swaps = 0;
		compares = 0;
		recalls = 0;
		System.out.println("HeapSort Summary:");
		for(SortStatistics s : statisticsHeap){
			duration += s.duration();
			swaps += s.getSwaps();
			compares += s.getCompares();
			recalls += s.getRecCalls();
		}
		System.out.println("Duration: "+ duration + " , Swaps: "+ swaps + " , Compares: "+ compares + " , ReCalls: "+ recalls );

	}
}