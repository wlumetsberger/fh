package at.fhhagenberg.swe.uebung03;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HammingNumbers {

	/**
	 * Helper Method to return lowest value which is greater than valueHolder
	 * if valueHolder is bigger than one,two,three return null
	 * @param one
	 * @param two
	 * @param three
	 * @param valueHolder
	 * @return
	 */
	private static BigInteger determinMin(BigInteger one, BigInteger two, BigInteger three, BigInteger valueHolder){
		if(one.compareTo(two)<=0 && one.compareTo(three) <= 0 && one.compareTo(valueHolder)>0){
			return one;
		}
		if(two.compareTo(one) <= 0 && two.compareTo(three) <=0 && two.compareTo(valueHolder)>0){
			return two;
		}
		if(three.compareTo(one) <= 0 && three.compareTo(two) <= 0 && three.compareTo(valueHolder) > 0){
			return three;
		}
		return null;
		
	}
	/**
	 * calculates amount of hammingnumbers.
	 * @param amount
	 * @return
	 */
	public static List<BigInteger> calculateHammingNumbers(int amount){
		if(amount < 1){
			throw new IllegalArgumentException("Amount must be equal or greather 1");
		}
		long startTime = System.currentTimeMillis();
		List<BigInteger> result = new ArrayList<BigInteger>();
		
		BigInteger second = new BigInteger("2");
		BigInteger third = new BigInteger("3");
		BigInteger five = new BigInteger("5");
		BigInteger valueHolder = BigInteger.ONE;
		
		int count=0;
		int multCount1 = 0;
		int multCount2 = 0;
		int multCount3 = 0;
		
		// add first entry
		result.add(valueHolder);
		
		while(count < amount-1){
			BigInteger multValue2 = result.get(multCount1).multiply(second);
			BigInteger multValue3 = result.get(multCount2).multiply(third);
			BigInteger multValue5 = result.get(multCount3).multiply(five);
			BigInteger value = determinMin(multValue2, multValue3, multValue5, valueHolder);
			
			if(value != null){
				if(value.equals(multValue2)){
					multCount1++;
				}else if(value.equals(multValue3)){
					multCount2 ++;
				}else if(value.equals(multValue5)){
					multCount3 ++;
				}
			
				result.add(value);
				valueHolder = value;
				count ++;
			}else{
				if(valueHolder.equals(multValue2)){
					multCount1++;
				}
				if(valueHolder.equals(multValue3)){
					multCount2++;	
				}			
				if(valueHolder.equals(multValue5)){
					multCount3++;
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Duration in Millis: "+ (endTime - startTime));
		return result;
	}
 }
