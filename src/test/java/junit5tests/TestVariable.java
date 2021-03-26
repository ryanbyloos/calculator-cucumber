package junit5tests;

import calculator.IntegerNumber;
import function.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVariable {

    private final String name = "X";
    private Variable var;

    @BeforeEach
    public void setUp() {
        var = new Variable(name);
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
        assertEquals(name, var.toString());
    }
    @Test
    public void testToStringWithAssignment() {
        int i = 2;
        IntegerNumber n = new IntegerNumber(Integer.toString(i));
        var.assignValue(n);
        assertEquals(name+":"+i, var.completeString());
    }
}
