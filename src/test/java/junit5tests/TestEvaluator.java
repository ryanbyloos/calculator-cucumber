package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

public class TestEvaluator {
    private Calculator calc;
    private int value1, value2;
    private Expression op;

    @BeforeEach
    public void setUp() {
        calc = new Calculator(Calculator.Mode.REAL);
        value1 = 8;
        value2 = 6;
    }

    @Test
    public void testEvaluatorMyNumber() {
        try {
            assertEquals(Integer.toString(value1),
                    calc.eval(new IntegerNumber(Integer.toString(value1))));
        }catch (NotAnIntegerNumber e){
            fail();
        }
    }

    @Test
    public void testEvaluatorDivides() {
        try { op = new Divides(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
            BigDecimal expected = new BigDecimal("1.33333");
            Operation.CONST_ROUNDED = 5;
            expected.divide(new BigDecimal(6), 2, RoundingMode.HALF_UP);
            assertEquals( expected.toString() ,
                        calc.eval(op) );
            }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorPlus() {
        try { op = new Plus(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
            BigInteger expected = new BigInteger("14");
            assertEquals( expected.toString(),
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try { op = new Minus(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
            BigInteger expected = new BigInteger("2");
            assertEquals( expected.toString(),
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {
        try { op = new Times(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
            BigInteger expected = new BigInteger("48");
            assertEquals( expected.toString(),
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

}
