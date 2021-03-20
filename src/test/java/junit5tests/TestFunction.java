package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import function.Function;
import function.Variable;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFunction {

    @Test
    public void testFunctionDisplayPlus(){
//        Function f = new Function();

        try {
            Variable x = new Variable("X");

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

            String infix2 = "add(X):( " + xValue.getValue() + " + " + secondMember.getValue() + " )";
            assertEquals(infix2,f.toString(values));

        }catch(IllegalConstruction exception) {
            System.out.println("cannot create operations without parameters");
        }
    }

    @Test
    public void testFunctionDisplay(){
        //TODO
    }

    @Test
    public void testBadAssignement(){
        //TODO
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

            assertEquals(14,f.compute(values));
        }catch(IllegalConstruction exception){
            System.out.println("cannot create operations without parameters");
        }
    }
}
