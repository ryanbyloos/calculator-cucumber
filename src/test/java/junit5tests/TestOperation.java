package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Operation;
import calculator.operations.Plus;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOperation {

	private Operation o;
	private Operation o2;

	@BeforeEach
	public void setUp() throws Exception {
		List<Expression> params1 =
				new ArrayList<>(Arrays.asList(new IntegerNumber("3"), new IntegerNumber("4"), new IntegerNumber("5")));
		List<Expression> params2 =
				new ArrayList<>(Arrays.asList(new IntegerNumber("5"), new IntegerNumber("4")));
		List<Expression> params3 =
				new ArrayList<>(Arrays.asList(new Plus(params1), new Minus(params2), new IntegerNumber("7")));
		o = new Divides(params3);
		o2 = new Divides(params3);
	}

	@Test
	public void testEquals() {
		assertEquals(o,o2);
	}

	@Test
	public void testCountDepth() {
		assertEquals(Integer.valueOf(2), o.countDepth());
	}

	@Test
	public void testCountOps() {
		assertEquals(Integer.valueOf(3), o.countOps());
	}

	@Test
	public void testCountNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

}
