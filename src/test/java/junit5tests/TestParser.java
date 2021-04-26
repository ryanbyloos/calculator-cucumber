package junit5tests;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Times;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestParser {

    @Test
    public void testParser(){
        Parser p = new Parser("2");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("2",c.eval(e1));
    }

    @Test
    public void testParserRealCannotBeConvert(){
        Parser p = new Parser("2.8");

        Calculator c = new Calculator(Calculator.Mode.INTEGER);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("Error : 2.8 is not an integer",c.eval(e1));
    }

    @Test
    public void testParserAddReal(){
        Parser p = new Parser("2.3+2.7");

        Calculator c = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression(c);
        c.print(e1);
        assertEquals("5",c.eval(e1));
    }

    @Test
    public void testParserOpPriority(){
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
}
