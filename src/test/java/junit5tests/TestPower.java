package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.operations.Power;
import calculator.operations.Times;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPower {

    private final int value1 = 8;
    private final int value2 = 6;
    private Power op;
    private List<Expression> params;

    @BeforeEach
    public void setUp() {
        params = new ArrayList<>(Arrays.asList(new IntegerNumber(Integer.toString(value1)),new IntegerNumber(Integer.toString(value2))));
        try { op = new Power(params); }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Power(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Times expression should not be the same as a Minus expression
        try {
            assertNotEquals(op, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
        try {
            Power e = new Power(p, Notation.INFIX);
            assertEquals(op, e);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testEquals2() {
        assertDoesNotThrow(() -> op.equals(null)); // Direct way to to test if the null case is handled.
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Power(params));
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(1), op.countDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(1), op.countOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(2), op.countNbs());
    }

    @Test
    public void testPrefix() {
        String prefix = "^ (" + value1 + ", " + value2 + ")";
        assertEquals(prefix, op.toString(Notation.PREFIX));
        op.notation = Notation.PREFIX;
        assertEquals(prefix, op.toString());
    }

    @Test
    public void testInfix() {
        String infix = "( " + value1 + " ^ " + value2 + " )";
        assertEquals(infix, op.toString(Notation.INFIX));
        op.notation = Notation.INFIX;
        assertEquals(infix, op.toString());
    }

    @Test
    public void testPostfix() {
        String postfix = "(" + value1 + ", " + value2 + ") ^";
        assertEquals(postfix, op.toString(Notation.POSTFIX));
        op.notation = Notation.POSTFIX;
        assertEquals(postfix, op.toString());
    }
}
