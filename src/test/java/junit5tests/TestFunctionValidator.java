package junit5tests;

import calculator.*;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Plus;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import visitor.FunctionValidator;

import java.util.ArrayList;

public class TestFunctionValidator {
    private FunctionValidator v;
    Variable var1,var2;

    @BeforeEach
    public void setUp() {
        var1 = new Variable();
        var2 = new Variable();
    }

    @Test
    public void testValidator(){

        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(new IntegerNumber("1"));
            el.add(var1);

            Expression e = new Plus(el);

            Function f = new Function(var1, e);
            v = new FunctionValidator();
            assertTrue(v.verify(f, Calculator.Mode.INTEGER));
        }catch (IllegalConstruction exception){
            fail();
        }
    }

    // Test convert real to int if this is possible without information loss
    // no need to test for int to real because every int is a real
    @Test
    public void testValidatorCannotConvertRealToInt(){
        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(var1);
            el.add(new RealNumber("1.5"));

            Expression e = new Plus(el);

            Function f = new Function(var1, e);
            v = new FunctionValidator();
            assertFalse(v.verify(f, Calculator.Mode.INTEGER));
        }catch (IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testValidatorConvertRealToInt(){
        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(var1);
            el.add(new RealNumber("1"));

            Expression e = new Plus(el);

            Function f = new Function(var1, e);
            v = new FunctionValidator();
            assertTrue(v.verify(f, Calculator.Mode.INTEGER));
        }catch (IllegalConstruction exception){
            fail();
        }
    }
}
