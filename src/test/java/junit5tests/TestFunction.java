package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import function.Function;
import function.Variable;
import io.cucumber.java.an.E;
import org.junit.jupiter.api.*;
import visitor.Evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFunction {

    @Test
    public void testFunctionDisplayPlus(){
        try {
            String varName = "X";
            Variable x = new Variable(varName);

            ArrayList<Variable> varList = new ArrayList<>();
            varList.add(x);

            MyNumber secondMember = new MyNumber(2);

            List<Expression> param = new ArrayList<>();
            Collections.addAll(param, x,  secondMember);
            Expression e = new Plus(param, Notation.INFIX);

            Function f = new Function("add",varList,e);
            // TEST variable as no value
            String infix = "add(X):( " + x.getVarName() + " + " + secondMember.getValue() + " )";

            assertEquals(infix,f.toString());


            // TEST variable as value
            MyNumber xValue = new MyNumber(12);

            ArrayList<MyNumber> values = new ArrayList<>();
            values.add(xValue);

            String infix2 = "add(X:12):( " + varName + " + " + secondMember.getValue() + " )";
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
    public void testFunctionDisplay(){
        //TODO
    }

    @Test
    public void testBadAssignment(){
        Function f;

        Variable x = new Variable("X");

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
            Calculator c = new Calculator();

            // Init function
            Variable x = new Variable("X");
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
            Variable x = new Variable("X");
            varList = new ArrayList<>();
            varList.add(x);


            Variable y = new Variable("Y");

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
}
