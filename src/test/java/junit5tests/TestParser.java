package junit5tests;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.InvalidSyntax;
import calculator.operations.Times;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestParser {

    @Test
    public void testParser() throws IllegalConstruction {
        Parser p = new Parser("2");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("2",c.eval(e1));
    }

    @Test
    public void testParserRealCannotBeConvert() throws IllegalConstruction {
        Parser p = new Parser("2.8");

        Calculator c = new Calculator(Calculator.Mode.INTEGER);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("2.8 is not an integer",c.eval(e1));
    }

    @Test
    public void testParserAddReal() throws IllegalConstruction {
        Parser p = new Parser("2.3+2.7");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("5",c.eval(e1));
    }

    @Test
    public void testParserOpPriority() throws IllegalConstruction {
        Parser p = new Parser("2+3*2+2.8");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("10.8",c.eval(e1));
    }

    @Test
    public void testParserFun() throws IllegalConstruction {
        Parser p = new Parser("two(3)");

        Calculator c = new Calculator(Calculator.Mode.REAL);

        // ADD FUNCTION TO CALCULATOR
        Variable v = new Variable();
        Expression two = new IntegerNumber("2");
        ArrayList<Expression> el = new ArrayList<>();
        el.add(two);
        el.add(v);

        Expression e = new Times(el);
        Function f = new Function(e);
        c.addFunction("two",f);

        // compute res
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("6",c.eval(e1));
    }

    @Test
    public void testParserFunInExpre() throws IllegalConstruction {
        Parser p = new Parser("two(3)*2+3");

        Calculator c = new Calculator(Calculator.Mode.REAL);

        // ADD FUNCTION TO CALCULATOR
        Variable v = new Variable();
        Expression two = new IntegerNumber("2");
        ArrayList<Expression> el = new ArrayList<>();
        el.add(two);
        el.add(v);

        Expression e = new Times(el);
        Function f = new Function(e);
        c.addFunction("two",f);

        // compute res
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("15",c.eval(e1));
    }

    // TEST parenthesis

    @Test
    public void testParserWithoutParenthesis() throws IllegalConstruction {
        Parser p = new Parser("2*2+3");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("7",c.eval(e1));
    }

    @Test
    public void testParserWithParenthesis() throws IllegalConstruction {
        Parser p = new Parser("2*(2+3)");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("10",c.eval(e1));
    }

    // TODO test function does not exists

    @Test
    public void testParserSqrt() throws IllegalConstruction {
        Parser p = new Parser("sqrt(4)");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("2",c.eval(e1));
    }
    @Test
    public void testParserCos() throws IllegalConstruction {
        Parser p = new Parser("cos(0)");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("1",c.eval(e1));
    }

    @Test
    public void testParserFunDoesNotExist() throws IllegalConstruction {
        Parser p = new Parser("funqwe(0)");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        assertThrows(InvalidSyntax.class, () -> p.getExpression(c));
//        Expression e1 = p.getExpression(c);
//        c.print(e1);
//        assertEquals("1",c.eval(e1));
    }
}
