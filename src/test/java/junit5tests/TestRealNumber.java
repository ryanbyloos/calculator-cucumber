package junit5tests;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotARealNumber;
import calculator.operations.Times;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;
import visitor.EvaluatorNumber;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestRealNumber {
    private final int value =8;
    private MyNumber number;

    @BeforeEach
    public void setUp() {
        RealNumber.setPrecision(10);
        try {
            number = new RealNumber(Integer.toString(value));
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testEquals() {
        try {
            // Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
            assertEquals(new RealNumber(Integer.toString(value)), number);
            // Two MyNumbers containing a distinct value should not be equal:
            int otherValue = 7;
            assertNotEquals(new RealNumber(Integer.toString(otherValue)),number);
            assertEquals(number, number); // Identity check (for coverage, as this should always be true)
            assertNotEquals(number, value);
            assertNotEquals(new Times(new ArrayList<>()), number);
        }
        catch (IllegalConstruction e) {fail();}
    }

    @Test
    public void testEqualsBetweenRealNumberAndIntegerNumberAnd() {
        try {
            String value = "5";
            MyNumber realNum = new RealNumber(value);
            MyNumber intNum  = new IntegerNumber(value);
            assertEquals(realNum,intNum);
        }
        catch (IllegalConstruction e) {fail();}
    }
    @Test
    public void testEqualsBetweenRealNumberAndIntegerNumberAnd2() {
        try {
            String value1 = "5.6";
            String value2 = "5";
            MyNumber realNum = new RealNumber(value1);
            MyNumber intNum  = new IntegerNumber(value2);
            assertNotEquals(realNum,intNum);
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
        try {
            MyNumber r = new RealNumber("10.54");
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testCreateRealNumber2(){
        assertThrows(NotARealNumber.class,() -> new RealNumber("fwehokju3u{}[e"));
    }

    @Test
    public void testDisplaySmallRealNumber(){
        try {
            MyNumber r = new RealNumber("0.001");
            assertEquals("0.001", r.toString());
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testDisplayVerySmallRealNumber(){
        try {
            MyNumber r = new RealNumber("0.000000012");
            assertEquals("1.2E-8", r.toString());
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testDisplayLargeRealNumber(){
        try {
            RealNumber.setPrecision(10);
            MyNumber r = new RealNumber("12898948000000");
            assertEquals("1.2898948E13", r.toString());
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testDisplayLargeRealNumber2(){
        try {
            MyNumber r = new RealNumber("602200000000000000000000");
            assertEquals("6.022E23", r.toString());
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testDisplayLargeRealNumber3(){
        try {
            MyNumber n1 = new RealNumber("6.022");
            MyNumber n2 = new RealNumber("1E23");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(n1);
            el.add(n2);
            Times t = new Times(el);
            Evaluator v = new EvaluatorNumber(Calculator.Mode.REAL);
            t.accept(v);
            assertEquals("6.022E23", v.getResult().toString());
        }catch (IllegalConstruction e){
            fail();
        }
    }

    @Test
    public void testPrecision1(){
        try {
            RealNumber.setPrecision(3);
            MyNumber n1 = new RealNumber("6.122");
            assertEquals("6.12",n1.toString());
        }catch (IllegalConstruction e){
            fail();
        }
    }

    @Test
    public void testPrecision2(){
        try {
            RealNumber.setPrecision(2);
            MyNumber n1 = new RealNumber("36.122");
            assertEquals("36",n1.toString());
        }catch (IllegalConstruction e){
            fail();
        }
    }

    @Test
    public void testPrecision3(){
        try {
            RealNumber.setPrecision(4);
            MyNumber n1 = new RealNumber("36.122");
            assertEquals("36.12",n1.toString());
        }catch (IllegalConstruction e){
            fail();
        }
    }

    @Test
    public void testPrecision4(){
        try {
            RealNumber.setPrecision(1);
            MyNumber n1 = new RealNumber("600");
            assertEquals("600",n1.toString());
        }catch (IllegalConstruction e){
            fail();
        }
    }

}
