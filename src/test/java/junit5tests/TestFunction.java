package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Divides;
import calculator.operations.Plus;
import calculator.operations.Times;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;
import visitor.EvaluatorInteger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFunction {
    private Variable var1,var2;

    @BeforeEach
    public void setUp(){
        var1 = new Variable();
        var2 = new Variable();
    }

    @Test
    public void testFunctionDisplayPlus(){
        try {
            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1,  secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function(var1,e);
            // TEST variable as no value
            String infix = "( " + var1.varName + " + " + secondMember.toString() + " )";

            assertEquals(infix,f.toString());


            // TEST variable as value
            MyNumber xValue = new IntegerNumber("12");

            try {
                assertEquals(infix, f.toString(xValue));
            }catch (BadAssignment exception){
                fail();
            }

        }catch(IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testFunctionVariableDifferent(){
        ArrayList<Expression> el = new ArrayList<>();
        el.add(new IntegerNumber("2"));
        el.add(var2);

        Expression e;
        try {
            e = new Times(el);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }
        assertThrows(IllegalConstruction.class,() -> new Function(var1,e));
    }


    @Test
    public void testBadAssignment(){
        Function f;

        MyNumber secondMember = new IntegerNumber("2");

        List<Expression> param = new ArrayList<>();
        Collections.addAll(param, var1, secondMember);

        try {
            Expression e = new Plus(param, Notation.INFIX);

            f = new Function(var1, e);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }

        // test bad assignment to string
        assertThrows(BadAssignment.class, () -> f.toString(null));
        // test bad assignment to string
        EvaluatorInteger v = new EvaluatorInteger();
        assertThrows(BadAssignment.class, () -> f.compute(new RealNumber("1.5"),v));
    }

    @Test
    public void testFunctionCompute(){
        try {

            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function(var1, e);

            // TEST variable as value
            MyNumber xValue = new IntegerNumber("12");

            EvaluatorInteger v = new EvaluatorInteger();
            try {
                assertEquals(new BigInteger("14"), f.compute(xValue, v));
            }catch(BadAssignment exception){
                fail();
            }
        }catch(IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testVarNotInParamFunction(){
        Expression e;
        ArrayList<Variable> varList;
        try{

            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var2, secondMember);
            e = new Plus(param, Notation.INFIX);
        }catch(IllegalConstruction exception){
            fail();
            return;
        }
        assertThrows(IllegalConstruction.class, () -> new Function(var1, e));
    }

    @Test
    public void testFunctionCalculator(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Times(el);

            Function f = new Function(var1, e);

            MyNumber seven = new IntegerNumber("7");

            Calculator c = new Calculator(Calculator.Mode.INTEGER);
            try {
                assertEquals("14",c.eval(seven, f));
            }catch (BadAssignment exception){
                fail();
            }

        }catch (IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testFunctionCalculatorBadAssignment(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Times(el);

            Function f = new Function(var1, e);

            Calculator c = new Calculator(Calculator.Mode.INTEGER);

            assertThrows(BadAssignment.class,() -> c.eval(new RealNumber("23.5"),f));

        }catch (IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testFunctionDivideResOne(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Divides(el);

            Function f = new Function(var1, e);

            Calculator c = new Calculator(Calculator.Mode.REAL);

            assertEquals("1",c.eval(new IntegerNumber("2") ,f));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void testFunctionDivideVarZero(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Divides(el);

            Function f = new Function(var1, e);

            RealNumber zero = new RealNumber("0.0");

            Calculator c = new Calculator(Calculator.Mode.REAL);

            assertEquals("ERROR : Division By Zero Error",c.eval(zero ,f));
        }catch (Exception e){
            fail();
        }
    }
}
