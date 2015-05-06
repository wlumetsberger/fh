package at.fhhagenberg.swe.uebung03;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

public class HammingNumbersTest {

	@Test
	public void calculate10Numbers() {
		List<BigInteger> numbers = HammingNumbers.calculateHammingNumbers(10);
		for(BigInteger number : numbers){
			System.out.print(number + " | ");
		}
		assertEquals(new BigInteger("12"), numbers.get(numbers.size()-1));
		assertEquals(10,numbers.size());
		
	}
	
	@Test
	public void calculate10000Numbers(){
		List<BigInteger> numbers = HammingNumbers.calculateHammingNumbers(10000);
		BigInteger test = new BigInteger("288325195312500000");
		assertEquals(test,numbers.get(numbers.size()-1));
		assertEquals(10000,numbers.size());
	}
	
	@Test
	public void illegalParameter(){
		try{
			List<BigInteger> numbers = HammingNumbers.calculateHammingNumbers(0);
			fail("Called with wrong Parameter should Throw Illegal Argument Exception");
		}catch(IllegalArgumentException ex){
			assert(true);
		}
	}

}
