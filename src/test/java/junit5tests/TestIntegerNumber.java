package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotARealNumber;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.Times;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestIntegerNumber {

	private final int value = 8;
	private MyNumber number;
	@BeforeEach
	public void setUp() {
		try {
			number = new IntegerNumber(Integer.toString(value));
		}catch (NotAnIntegerNumber e){
			fail();
		}
	}
	@Test
	public void testEquals() {
		try {
			// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
			assertEquals(new IntegerNumber(Integer.toString(value)), number);
			// Two MyNumbers containing a distinct value should not be equal:
			int otherValue = 7;
			assertNotEquals(new IntegerNumber(Integer.toString(otherValue)),number);
			assertEquals(number, number); // Identity check (for coverage, as this should always be true)
			assertNotEquals(number, value);
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	public void testEqualsBetweenIntegerNumberAndRealNumber() {
		try {
			String value = "5";
			MyNumber realNum = new RealNumber(value);
			MyNumber intNum  = new IntegerNumber(value);
			assertEquals(intNum,realNum);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	public void testEqualsBetweenIntegerNumberAndRealNumber2() {
		try {
			String value1 = "5.6";
			String value2 = "5";

			MyNumber realNum = new RealNumber(value1);
			MyNumber intNum  = new IntegerNumber(value2);
			assertNotEquals(intNum,realNum);
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
	public void testCreateIntegerNumber(){
		try {
			MyNumber r = new IntegerNumber("10");
		}catch (NotAnIntegerNumber e){
			fail();
		}
	}
	@Test
	public void testCreateIntegerNumber2(){
		assertThrows(NotAnIntegerNumber.class,() -> new IntegerNumber("10.3"));
	}
	@Test
	public void testCreateIntegerNumber3(){
		assertThrows(NotAnIntegerNumber.class,() -> new IntegerNumber("fwehokju3u{}[e"));
	}


	@Test
	public void testDisplayBigIntegerNumber(){
		try {
			MyNumber r = new IntegerNumber("1000000000000000");
			assertEquals("1E15", r.toString());
		}catch (NotAnIntegerNumber e){
			fail();
		}
	}
	@Test
	public void testDisplayBigNegativeIntegerNumber(){
		try {
			MyNumber r = new IntegerNumber("-1000000000000000");
			assertEquals("-1E15", r.toString());
		}catch (NotAnIntegerNumber e){
			fail();
		}
	}

	@Test
	public void testDisplayBigIntegerNumber2(){
		try {
			MyNumber r = new IntegerNumber("6700000000000000");
			assertEquals("67E14", r.toString());
		}catch (NotAnIntegerNumber e){
			fail();
		}
	}
}
