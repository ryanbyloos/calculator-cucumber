package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;
import visitor.Evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFunction {
    private final String x_var_name = "X";
    private final String y_var_name = "Y";
    private Variable x,y;

    @BeforeEach
    public void set_up(){
        x = new Variable(x_var_name);
        y = new Variable(y_var_name);
    }

    @Test
    public void testFunctionDisplayPlus(){
        try {
            ArrayList<Variable> varList = new ArrayList<>();
            varList.add(x);

            MyNumber secondMember = new MyNumber(2);

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, x,  secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function("add",varList,e);
            // TEST variable as no value
            String infix = "add("+x_var_name+"):( " +x_var_name + " + " + secondMember.getValue() + " )";

            assertEquals(infix,f.toString());


            // TEST variable as value
            MyNumber xValue = new MyNumber(12);

            ArrayList<MyNumber> values = new ArrayList<>();
            values.add(xValue);

            String infix2 = "add("+x_var_name+":"+xValue.getValue()+"):( " + x_var_name + " + " + secondMember.getValue() + " )";
            try {
                assertEquals(infix2, f.toString(values));
            }catch (BadAssignment exception){
                fail();
            }

        }catch(IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testFunctionDisplayPlusMultipleParam(){
        try {
            ArrayList<Variable> varList = new ArrayList<>();
            varList.add(x);
            varList.add(y);


            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, x,  y);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function("add",varList,e);
            // TEST variable as no value
            String infix = "add("+x_var_name+","+y_var_name+"):( " + x_var_name + " + " + y_var_name + " )";

            assertEquals(infix,f.toString());


            // TEST variable as value
            MyNumber xValue = new MyNumber(12);
            MyNumber yValue = new MyNumber(3);

            ArrayList<MyNumber> values = new ArrayList<>();
            values.add(xValue);
            values.add(yValue);

            String infix2 = "add("+x_var_name+":"+xValue+","+y_var_name+":"+yValue+"):( " + x_var_name + " + " + y_var_name + " )";
            try {
                assertEquals(infix2, f.toString(values));
            }catch (BadAssignment exception){
                fail();
            }

        }catch(IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testFunctionDuplicateVariable(){
        ArrayList<Variable> vars = new ArrayList<>();
        vars.add(x);
        vars.add(x);

        ArrayList<Expression> el = new ArrayList<>();
        el.add(x);
        el.add(x);

        Expression e;
        try {
            e = new Times(el);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }
        assertThrows(IllegalConstruction.class,() -> new Function("square",vars,e));
    }


    @Test
    public void testBadAssignment(){
        Function f;
        ArrayList<Variable> varList = new ArrayList<>();
        varList.add(x);

        MyNumber secondMember = new MyNumber(2);

        List<Expression> param = new ArrayList<>();
        Collections.addAll(param, x, secondMember);

        try {
            Expression e = new Plus(param, Notation.INFIX);

            f = new Function("add", varList, e);
        }catch (IllegalConstruction exception){
            fail();
            return;
        }
        // TEST variable as value
        ArrayList<MyNumber> values = new ArrayList<>();

        // test bad assignment to string
        assertThrows(BadAssignment.class, () -> f.toString(values));
        // test bad assignment to string
        Evaluator v = new Evaluator();
        assertThrows(BadAssignment.class, () -> f.compute(values,v));
    }

    @Test
    public void testFunctionCompute(){
        try {
            // Init function
            ArrayList<Variable> varList = new ArrayList<>();
            varList.add(x);

            MyNumber secondMember = new MyNumber(2);

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, x, secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function("", varList, e);

            // TEST variable as value
            MyNumber xValue = new MyNumber(12);

            ArrayList<MyNumber> values = new ArrayList<>();
            values.add(xValue);

            Evaluator v = new Evaluator();
            try {
                assertEquals(14, f.compute(values, v));
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
            // Init function
            varList = new ArrayList<>();
            varList.add(x);

            MyNumber secondMember = new MyNumber(2);

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, y, secondMember);
            e = new Plus(param, Notation.INFIX);
        }catch(IllegalConstruction exception){
            fail();
            return;
        }
        assertThrows(IllegalConstruction.class, () -> new Function("", varList, e));
    }

    @Test
    public void testFunctionCalculator(){
        try {
            ArrayList<Variable> vars = new ArrayList<>();
            vars.add(x);

            Expression two = new MyNumber(2);
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(x);

            Expression e = new Times(el);

            Function f = new Function("2times", vars, e);

            ArrayList<MyNumber> values = new ArrayList<>();
            MyNumber seven = new MyNumber(7);
            values.add(seven);

            Calculator c = new Calculator();
            try {
                assertEquals(14,c.eval(values, f));
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
            ArrayList<Variable> vars = new ArrayList<>();
            vars.add(x);

            Expression two = new MyNumber(2);
            ArrayList<Expression> el = new ArrayList<>();
            el.add(two);
            el.add(x);

            Expression e = new Times(el);

            Function f = new Function("2times", vars, e);

            ArrayList<MyNumber> values = new ArrayList<>();

            Calculator c = new Calculator();

            assertThrows(BadAssignment.class,() -> c.eval(values,f));

        }catch (IllegalConstruction exception){
            fail();
        }
    }
}
