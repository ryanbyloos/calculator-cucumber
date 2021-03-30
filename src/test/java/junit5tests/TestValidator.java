package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Plus;
import calculator.RealNumber;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import visitor.Validator;

import java.util.ArrayList;

public class TestValidator {
    private Validator v;
    Variable x;

    @BeforeEach
    public void setUp() {
        x = new Variable();
    }

    @Test
    public void testValidatorEmptyOperator(){

        try {
            ArrayList<Expression> el = new ArrayList<>();

            Expression e = new Plus(el);

            Function f = new Function(x, e);
            v = new Validator();
            v.visit(f);
            assertTrue(v.isValid());
        }catch (IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testValidatorWithVariable(){
        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(x);
            el.add(new RealNumber("1.5"));

            Expression e = new Plus(el);

            Function f = new Function(x, e);
            v = new Validator();
            v.visit(f);
            assertTrue(v.isValid());
        }catch (IllegalConstruction exception){
            fail();
        }
    }
}
