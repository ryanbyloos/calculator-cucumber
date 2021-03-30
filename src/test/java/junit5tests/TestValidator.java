package junit5tests;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.operations.Plus;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import visitor.Validator;

import java.util.ArrayList;

public class TestValidator {

    private Validator v;
    String x_var_name,y_var_name;
    Variable x,y;
    ArrayList<Variable> vars;

    @BeforeEach
    public void setUp() {

        x_var_name = "X";
        y_var_name = "Y";

        x = new Variable(x_var_name);
        y = new Variable(y_var_name);

        vars = new ArrayList<>();
        vars.add(x);
        vars.add(y);
    }

    @Test
    public void testValidatorEmptyOperator(){

        try {
            ArrayList<Expression> el = new ArrayList<>();

            Expression e = new Plus(el);

            Function f = new Function("", vars, e);
            v = new Validator(f);
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
            el.add(y);

            Expression e = new Plus(el);

            Function f = new Function("", vars, e);
            v = new Validator(f);
            assertTrue(v.isValid());
        }catch (IllegalConstruction exception){
            fail();
        }
    }
}
