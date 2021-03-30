package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Times;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestMyNumber {

	private final int value =8;
	private MyNumber number;
	
	@BeforeEach
	public void setUp() {
		number = new RealNumber(Integer.toString(value));
	}

	@Test
	public void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new RealNumber(Integer.toString(value)), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new RealNumber(Integer.toString(otherValue)),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value);
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	public void testCountDepth() {
		//test whether a number has zero depth (i.e. no nested expressions)
		assertEquals(Integer.valueOf(0), number.countDepth());
	}

	@Test
	public void testCountOps() {
		//test whether a number contains zero operations
		assertEquals(Integer.valueOf(0), number.countOps());
	}

	@Test
	public void testCountNbs() {
		//test whether a number contains 1 number
		assertEquals(Integer.valueOf(1), number.countNbs());
	}

	@Test
	public void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

	@Test
	public void testCreateRealNumber(){
		MyNumber r = new RealNumber("10.54");
	}

	@Test
	public void testDisplaySmallRealNumber(){
		MyNumber r = new RealNumber("0.001");
		assertEquals("0.001",r.toString());
	}

	@Test
	public void testDisplayVerySmallRealNumber(){
		MyNumber r = new RealNumber("0.000000012");
		assertEquals("1.2E-8",r.toString());
	}

	@Test
	public void testDisplayLargeRealNumber(){
		MyNumber r = new RealNumber("12898948000000");
		assertEquals("1.2898948E13",r.toString());
	}
}
