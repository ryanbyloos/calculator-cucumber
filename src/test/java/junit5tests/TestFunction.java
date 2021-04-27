package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.*;
import calculator.operations.Divides;
import calculator.operations.Plus;
import calculator.operations.Times;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;
import visitor.EvaluatorInteger;

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

            Function f = new Function(e);
            // TEST variable as no value
            String infix = "( " + var1.varName + " + " + secondMember + " )";

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
    public void testUnassigned(){
        Function f;
        try {
            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            f = new Function(e);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }

        // test bad assignment to string
        assertThrows(BadAssignment.class, () -> f.toString(null));

        try {
            f.setValue(new RealNumber("1.5"));

            // test bad assignment to string
            EvaluatorInteger ev = new EvaluatorInteger();
            f.getExpression().accept(ev);
            assertThrows(NotAnIntegerNumber.class,() -> ev.getResult());
        }catch (BadAssignment | NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testBadAssignment(){
        Function f;
        try {
            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            f = new Function(e);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }

        // test bad assignment to string
        assertThrows(BadAssignment.class, () -> f.toString(null));

        try {
            f.setValue(new RealNumber("1.5"));

            // test bad assignment to string
            EvaluatorInteger ev = new EvaluatorInteger();
            f.getExpression().accept(ev);
            assertThrows(NotAnIntegerNumber.class,() -> ev.getResult());
        }catch (BadAssignment | NotARealNumber e){
            fail();
        }
    }

    @Test
    public void testFunctionCompute(){
        try {
            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function(e);

            // TEST variable as value
            MyNumber xValue = new IntegerNumber("12");

            EvaluatorInteger v = new EvaluatorInteger();

            try {
                f.setValue(xValue);
                EvaluatorInteger ev = new EvaluatorInteger();
                f.getExpression().accept(ev);
                assertEquals(new IntegerNumber("14"), ev.getResult());
            }catch(BadAssignment exception){
                fail();
            }
        }catch(IllegalConstruction exception){
            fail();
        }
    }

    @Test
    public void testFunctionCalculator(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Times(el);

            Function f = new Function(e);

            MyNumber seven = new IntegerNumber("7");

            Calculator c = new Calculator(Calculator.Mode.INTEGER);

            f.setValue(seven);
            assertEquals("14",c.eval(f));
        }catch (IllegalConstruction | BadAssignment exception){
            fail();
        }
    }

    @Test
    public void testFunctionCalculatorRealInsteadInteger(){
        try {
            Expression two = new IntegerNumber("2");
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(var1);

            Expression e = new Times(el);

            Function f = new Function(e);

            Calculator c = new Calculator(Calculator.Mode.INTEGER);
            c.addFunction("fun",f);

            RealNumber x = new RealNumber("23.5");
            f.setValue(x);
            assertThrows(NotAnIntegerNumber.class,() -> c.evalInteger(f.getExpression()));
            f.clearValue();

            f.setValue(x);
            assertEquals("23.5 is not an integer", c.eval(f));
            f.clearValue();

        }catch (IllegalConstruction | BadAssignment exception){
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

            Function f = new Function(e);

            Calculator c = new Calculator(Calculator.Mode.REAL);

            f.setValue(new IntegerNumber("2"));
            assertEquals("1",c.eval(f));
        }catch (IllegalConstruction | BadAssignment e){
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

            Function f = new Function(e);

            RealNumber zero = new RealNumber("0.0");

            Calculator c = new Calculator(Calculator.Mode.REAL);

            // test if eval throw exception
            f.setValue(zero);
            assertThrows(DivisionByZeroError.class, () -> c.evalReal(f.getExpression()));
            f.clearValue();

            // test if correctly return error message
            f.setValue(zero);
            assertEquals("Division By Zero Error",c.eval(f));
        }catch (Exception e){
            fail();
        }
    }

    @Test
    // test if function constructor replace variable
    public void testValidatorTwoVariable(){
        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(var1);
            el.add(var2);

            Expression e = new Plus(el);

            Function f = new Function(e);

            Calculator c = new Calculator(Calculator.Mode.REAL);

            f.setValue(new RealNumber("2.6"));
            assertEquals("5.2",c.eval(f));

        }catch (IllegalConstruction | BadAssignment exception){
            fail();
        }
    }
}
