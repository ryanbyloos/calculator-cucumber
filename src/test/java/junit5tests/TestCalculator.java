package junit5tests;

import calculator.Calculator;
import calculator.Expression;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.functions.SquareRoot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestCalculator {
    @Test
    public void test() throws IllegalConstruction {
        List<Expression> s = List.of(new RealNumber("4"));
        Expression e = new SquareRoot(s);

        Calculator c = new Calculator(Calculator.Mode.REAL);

        assertEquals("2",c.eval(e));
    }
}
