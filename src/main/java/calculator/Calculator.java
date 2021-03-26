package calculator;

import function.Function;
import visitor.EvaluatorInteger;
import visitor.EvaluatorReal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Calculator {
    public enum Mode {INTEGER,REAL}

    private final Mode mode;
    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    public Calculator(Mode m){
        mode = m;
    }
    public Calculator(Mode m,int round){
        mode = m;
        Operation.CONST_ROUNDED =round;
    }


    public void print(Expression e) {
        System.out.println("The result of evaluating expression " + e);
        if(mode == Mode.INTEGER)
            System.out.println("is: " + evalInteger(e) + ".");
        else if( mode == Mode.REAL)
            System.out.println("is: " + evalReal(e) + ".");
        System.out.println();
    }

    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    public String eval(Expression e){
        if(mode == Mode.INTEGER)
            return evalInteger(e).toString();
        else if(mode == Mode.REAL)
            return evalReal(e).toString();

        return null; // TODO handle ERROR CASE
    }

    public BigInteger evalInteger(Expression e) {
        // create a new visitor to evaluate expressions
        EvaluatorInteger v = new EvaluatorInteger();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public BigDecimal evalReal(Expression e) {
        // create a new visitor to evaluate expressions
        EvaluatorReal v = new EvaluatorReal();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public BigDecimal eval(List<MyNumber> values,Function f) throws BadAssignment{
        // create a new visitor to evaluate expressions
        EvaluatorReal v = new EvaluatorReal();
        // and ask the expression to accept this visitor to start the evaluation process
        f.compute(values,v);
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }



    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
