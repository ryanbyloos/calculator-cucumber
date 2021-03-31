package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.ImpossibleConversionError;
import calculator.exceptions.NotARealNumber;
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
        Expression e;
        try {
            ArrayList<Expression> el = new ArrayList<>();
            el.add(new IntegerNumber("2"));
            el.add(var2);
            e = new Times(el);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }
        assertThrows(IllegalConstruction.class,() -> new Function(var1,e));
    }

    @Test
    public void testUnassigned(){
        Function f;
        try {
            MyNumber secondMember = new IntegerNumber("2");

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, var1, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            f = new Function(var1, e);
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
            assertEquals(ImpossibleConversionError.class,ev.getException().getClass());

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

            f = new Function(var1, e);
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
            assertEquals(ImpossibleConversionError.class,ev.getException().getClass());

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

            Function f = new Function(var1, e);

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

            c.addFunction("fun",f);
            assertEquals("14",c.eval(seven, "fun"));
        }catch (IllegalConstruction | BadAssignment exception){
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
            c.addFunction("fun",f);

            assertThrows(BadAssignment.class,() -> c.eval(new RealNumber("23.5"),"fun"));

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

            Function f = new Function(var1, e);

            Calculator c = new Calculator(Calculator.Mode.REAL);
            c.addFunction("fun",f);

            assertEquals("1",c.eval(new IntegerNumber("2") ,"fun"));

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
            c.addFunction("fun",f);

            assertEquals("ERROR : Division By Zero Error",c.eval(zero ,"fun"));
        }catch (Exception e){
            fail();
        }
    }
}
