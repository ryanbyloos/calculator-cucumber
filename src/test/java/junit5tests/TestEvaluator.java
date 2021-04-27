package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.*;
import org.junit.jupiter.api.*;

import calculator.*;
import time.MyDate;
import visitor.EvaluatorDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        try {
            op = new Divides(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
            BigDecimal expected = new BigDecimal("1.33333");
            RealNumber.setPrecision(6);
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
    @Test
    public void testEvaluatorDate1() {
        try {         List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1998,3,3)), new MyDate(0,1,1)));
            Expression exp = new Plus(params2);
            EvaluatorDate evaluator = new EvaluatorDate();
            exp.accept(evaluator);
            assertEquals(new MyDate(LocalDate.of(1998,4,4)),evaluator.getResult());
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }
    @Test
    public void testEvaluatorDate2() {
        try {         List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1998,3,3)), new MyDate(0,1,1)));
            Expression exp = new Minus(params2);
            EvaluatorDate evaluator = new EvaluatorDate();
            exp.accept(evaluator);
            assertEquals(new MyDate(LocalDate.of(1998,2,2)),evaluator.getResult());
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }
    @Test
    public void testEvaluatorDate3() {
        try {         List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1999,12,6)), new MyDate(1998,3,3)));
            Expression exp = new Minus(params2);
            EvaluatorDate evaluator = new EvaluatorDate();
            exp.accept(evaluator);
            assertEquals(new MyDate(LocalDate.of(1,9,3)),evaluator.getResult());
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testUnsupportedDateInteger(){
        MyNumber d = new MyDate(LocalDate.of(0, 1, 1));

        Calculator c = new Calculator(Calculator.Mode.INTEGER);
        assertThrows(ComputeError.class,() -> c.evalInteger(d));
        assertEquals("Unsupported date in integer mode",c.eval(d));

    }

    @Test
    public void testUnsupportedDateReal(){
        MyNumber d = new MyDate(LocalDate.of(0, 1, 1));

        Calculator c = new Calculator(Calculator.Mode.REAL);
        assertThrows(ComputeError.class,() -> c.evalInteger(d));
        assertEquals("Unsupported date in real mode",c.eval(d));

    }

}
