package at.fhhagenberg.swe.uebung03;

public class SortAlgorithms {
	// Member variables for Statistics of Sorting and value array to sort
	private SortStatistics statistic;
	private int[] values;
	
	/**
	 * Constructor
	 */
	public SortAlgorithms() {
		super();
	}
	/**
	 * Constructor with given array
	 * @param values
	 */
	public SortAlgorithms(int[] values){
		this.values = values;
	}
	/**
	 * reinitalize values array with amount of random values
	 * @param amount
	 */
	public void generateRandomValues(int amount){
		values = new int[amount];
		for(int i=0; i<values.length; i++){
			values[i] = ((Double)(Math.random()*100)).intValue();
		}
	}
	/**
	 * Method to swap values of an int array
	 * @param values
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}

	/**
	 * Private helper Method heap sort
	 * @param values
	 * @param i
	 * @param n
	 */
	private void sink(int i, int n) {
		while(i <= (n / 2) - 1) {
			statistic.incCompares();
			// calculate Index of left child
			int kindIndex = ((i+1) * 2) - 1; 
	 
			//determin if left child exists
			if(kindIndex + 1 <= n -1) {
				statistic.incCompares();
				if(values[kindIndex] < values[kindIndex+1]) {
					statistic.incCompares();
					kindIndex++; 
				}
			}
	 
			// check if element has to sink
			if(values[i] < values[kindIndex]) {
				statistic.incCompares();
				statistic.incSwaps();
				swap(i,kindIndex); 
				// repeat with new position
				i = kindIndex;
			} else break;
		}
	}
	/**
	 * generate MaxHeap for Heapsort
	 * @param values
	 */
	private void generateMaxHeap() {
		// start from middle backward
		for(int i = (values.length / 2) - 1; i >= 0 ; i--) {
			sink(i, values.length);
		}
	}
	/**
	 * HeapSort 
	 * @param values
	 */
	public void heapSort() {
		if(this.values == null){
			return;
		}
		this.statistic = new SortStatistics("HeapSort");
		this.statistic.setStartTime(System.nanoTime());
		generateMaxHeap();
		// sorting
		for(int i = values.length -1; i > 0; i--) {
			statistic.incSwaps();
			swap(i, 0);
			sink(0, i);
		}
		this.statistic.setEndTime(System.nanoTime());
	}

	
	public void quickSort() {
		if(this.values == null){
			return;
		}
		this.statistic = new SortStatistics("QuickSort");
		this.statistic.setStartTime(System.nanoTime());
		doQuickSort(0, values.length - 1);
		this.statistic.setEndTime(System.nanoTime());
	}

	private void doQuickSort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = values[low + (high - low) / 2];
		// Divide into two lists
		while (i <= j) {
			statistic.incCompares();
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (values[i] < pivot) {
				statistic.incCompares();
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (values[j] > pivot) {
				statistic.incCompares();
				j--;
			}

			if (i <= j) {
				statistic.incCompares();
				statistic.incSwaps();
				swap(i, j);
				i++;
				j--;
			}
		}
		// Recursion is here
		if (low < j){
			statistic.incCompares();
			statistic.incRecCalls();
		    doQuickSort(low, j);
		}
		if (i < high){
			statistic.incCompares();
			statistic.incRecCalls();
			doQuickSort(i, high);
		}

	}
	
	//////////////////////////////////////
	///// GETTER & SETTER 
	//////////////////////////////////////
	public SortStatistics getStatistic() {
		return statistic;
	}
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}

	
}
