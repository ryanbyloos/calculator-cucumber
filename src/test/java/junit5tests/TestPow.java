package junit5tests;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.Pow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestPow {

    private final int value1 = 8;
    private final int value2 = 6;
    private Pow op;
    private List<Expression> params;

    @BeforeEach
    public void setUp() {
        try {
            params = new ArrayList<>(Arrays.asList(new IntegerNumber(Integer.toString(value1)),new IntegerNumber(Integer.toString(value2))));
            op = new Pow(params);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    public void testPow() throws IllegalConstruction {

        params = List.of(new IntegerNumber("2"),new IntegerNumber("3"));
        Pow op2 = new Pow(params);
        Calculator c1 = new Calculator(Calculator.Mode.INTEGER);
        assertEquals("8",c1.eval(op2));

        Calculator c2 = new Calculator(Calculator.Mode.REAL);
        assertEquals("8",c2.eval(op2));
    }

    @Test
    public void testPowRealExpo() throws IllegalConstruction {

        params = List.of(new IntegerNumber("2"),new RealNumber("2.3"));
        Pow op2 = new Pow(params);

        Calculator c2 = new Calculator(Calculator.Mode.REAL);
        assertEquals("2.3 is not an integer",c2.eval(op2));
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
