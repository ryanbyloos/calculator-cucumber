package junit5tests;

import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.Divides;
import calculator.Expression;
import calculator.IntegerNumber;
import function.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestVariable {

    private Variable var;

    @BeforeEach
    public void setUp() {
        var = new Variable();
    }


    @Test
    public void testCountDepth() {
        //test whether a number has zero depth (i.e. no nested expressions)
        assertEquals(Integer.valueOf(0), var.countDepth());
    }

    @Test
    public void testCountOps() {
        //test whether a number contains zero operations
        assertEquals(Integer.valueOf(0), var.countOps());
    }

    @Test
    public void testCountNbs() {
        //test whether a number contains 1 number
        assertEquals(Integer.valueOf(1), var.countNbs());
    }

    @Test
    public void testToString() {
        assertEquals("X", var.toString());
    }
    @Test
    public void testToStringWithAssignment() {
        try {
            int i = 2;
            IntegerNumber n = new IntegerNumber(Integer.toString(i));
            var.assignValue(n);
            assertEquals("X"+":"+i, var.completeString());
        }catch (NotAnIntegerNumber e){
            fail();
        }
    }

    @Test
    public void testVariableFirstDivide(){
        try {
            ArrayList<Expression> l = new ArrayList<>();
            l.add(new Variable());
            l.add(new IntegerNumber("12"));
            assertDoesNotThrow(() -> new Divides(l));
        }catch (NotAnIntegerNumber e){
            fail();
        }
    }

    @Test
    public void testVariableSecondDivide(){
        try {
            ArrayList<Expression> l = new ArrayList<>();
            l.add(new IntegerNumber("12"));
            l.add(new Variable());
            assertDoesNotThrow(() -> new Divides(l));
        }catch (NotAnIntegerNumber e){
            fail();
        }
    }
}
