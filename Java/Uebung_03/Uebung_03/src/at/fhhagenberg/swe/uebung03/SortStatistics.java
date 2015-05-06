package at.fhhagenberg.swe.uebung03;

import java.util.Date;

public class SortStatistics {

	private int swaps;
	private int recCalls;
	private int compares;
	private String sortAlgorithm;
	private long startTime;
	private long endTime;
	
	public SortStatistics(String name) {
		this.sortAlgorithm = name;
		swaps = 0;
		recCalls = 0;
		compares = 0;
	}
	public String getSortAlgorithm() {
		return sortAlgorithm;
	}
	public int getCompares() {
		return compares;
	}
	public int getRecCalls() {
		return recCalls;
	}
	public int getSwaps() {
		return swaps;
	}
	
	public void incSwaps(){
		this.swaps++;
	}
	public void incRecCalls(){
		this.recCalls++;
	}
	public void incCompares(){
		this.compares++;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long duration(){
		return this.endTime - this.startTime;
	}
	
	@Override
	public String toString() {
		return "Statistics for: "+ this.sortAlgorithm + "\nSwaps: " + this.swaps +
				"\nCompares: "+ this.compares + "\nRecCalls: "+ this.recCalls +
				"\nDuration: "+ this.duration();
	}
}
