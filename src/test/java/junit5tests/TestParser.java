package junit5tests;

import static org.junit.jupiter.api.Assertions.*;

import calculator.Calculator;
import calculator.Expression;
import calculator.Parser;
import org.junit.jupiter.api.*;

public class TestParser {

    @Test
    public void testParser(){
        Parser p = new Parser("2");

        Calculator c1 = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression();
        c1.print(e1);
        assertEquals("2",c1.eval(e1));
    }

    @Test
    public void testParserRealCannotBeConvert(){
        Parser p = new Parser("2.8");

        Calculator c1 = new Calculator(Calculator.Mode.INTEGER);
        Expression e1 = p.getExpression();
        c1.print(e1);
        assertEquals("Error : 2.8 is not an integer",c1.eval(e1));
    }

    @Test
    public void testParserAddReal(){
        Parser p = new Parser("2.3+2.7");

        Calculator c1 = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression();
        c1.print(e1);
        assertEquals("5",c1.eval(e1));
    }

    @Test
    public void testParserOpPriority(){
        Parser p = new Parser("2+3*2+2.8");

        Calculator c1 = new Calculator(Calculator.Mode.REAL);
        Expression e1 = p.getExpression();
        c1.print(e1);
        assertEquals("10.8",c1.eval(e1));
    }
}
