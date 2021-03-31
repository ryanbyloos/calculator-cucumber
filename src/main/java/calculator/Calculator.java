package calculator;

import calculator.exceptions.BadAssignment;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConvertionArgument;
import calculator.operations.Operation;
import Converter.Temperature;
import function.Function;
import visitor.EvaluatorInteger;
import visitor.EvaluatorReal;
import visitor.FunctionValidator;
import Converter.Unit;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public enum Mode {INTEGER,REAL,CONVERSION}

    private HashMap<String,Function> storedFun;

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
        storedFun = new HashMap<>();
    }
    public Calculator(Mode m,int round){
        this(m);
        Operation.CONST_ROUNDED =round;
    }


//    public void print(Expression e) {
//        System.out.println("The result of evaluating expression " + e);
//        if(mode == Mode.INTEGER)
//            System.out.println("is: " + evalInteger(e) + ".");
//        else if( mode == Mode.REAL)
//            System.out.println("is: " + evalReal(e) + ".");
//        System.out.println();
//    }

//    public void printExpressionDetails(Expression e) {
//        print(e);
//        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
//        System.out.print(e.countOps() + " operations");
//        System.out.println(" and " + e.countNbs() + " numbers.");
//        System.out.println();
//    }

    public String eval(Expression e){
        try {
            if (mode == Mode.INTEGER)
                return evalInteger(e).toString();
            else if (mode == Mode.REAL)
                return evalReal(e).toString();
        }catch(ComputeError ce){
            return "Error"+ce.getMessage();
        }

        return null; // TODO handle ERROR CASE
    }

    public void addFunction(String key,Function f) throws BadAssignment{

    }

    /**
     * Return value of function or error message as string
     * @param value value of
     * @param f
     * @return
     * @throws BadAssignment
     */
    public String eval(MyNumber value,Function f) throws BadAssignment {
        String res;
        f.setValue(value);

        FunctionValidator v =  new FunctionValidator();

        if (!v.verify(f,mode)) throw new BadAssignment();

        switch (mode){
            case INTEGER:
                try {
                    res = evalInteger(f.getExpression()).toString();
                }catch (ComputeError ce){
                    return "ERROR : "+ce.getMessage();
                }
                break;
            case REAL:
                try {
                    res = evalReal(f.getExpression()).toString();
                }catch (ComputeError ce){
                    return "ERROR : "+ce.getMessage();
                }
                break;
            default:
                throw new BadAssignment(); // Should not be here
        }

        f.clearValue();
        return res;
    }


    public String convert(Expression e,Unit base, Unit aimed) throws IllegalConvertionArgument {
    public BigDecimal convert(Expression e,Unit base, Unit aimed) throws IllegalConvertionArgument{
        if(base.getType() != aimed.getType()){
            throw new IllegalConvertionArgument();
        }

    //    if(mode == Mode.INTEGER)
    //    {
    //        BigInteger eval = evalInteger(e);
    //        eval = eval.divide(base.getratio(),Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
    //        return "0";
    //    }
        try {
            BigDecimal eval = evalReal(e).getValue();

            eval = eval.divide(base.getratio(), Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
            eval = eval.multiply(aimed.getratio());
            String res = eval.toString() + aimed.getFullName();
            return res;
        }catch (ComputeError ce){
            return "ERROR: "+ce.getMessage();// TODO AVERTIR NICOLO
        }
        BigDecimal eval = evalReal(e);
        eval = eval.divide(base.getratio(),Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
        eval = eval.multiply(aimed.getratio());
        return eval;
    }
    public String converttoString(Expression e,Unit base, Unit aimed) throws IllegalConvertionArgument{
        if(base.getType() != aimed.getType()){
            throw new IllegalConvertionArgument();
        }

        BigDecimal eval = convert(e,base,aimed);
        String res = eval.toString() + aimed.getFullName();
        return res;
    }

    public BigDecimal convert(Expression e, Temperature base, Temperature aimed) {

        BigDecimal eval = evalReal(e);
        eval = eval.subtract(base.getConstant());
        eval = eval.divide(base.getRatio(),Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
        eval = eval.multiply(aimed.getRatio());
        eval = eval.add(aimed.getConstant());

        return eval;
    }

    public String converttoString(Expression e, Temperature base, Temperature aimed) {


        BigDecimal eval = convert(e,base,aimed);
        String res = eval.toString() + " "+aimed.getFullName();
        return res;
    }


    public IntegerNumber evalInteger(Expression e) throws ComputeError{
        // create a new visitor to evaluate expressions
        EvaluatorInteger v = new EvaluatorInteger();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        if(v.getException() != null ) throw v.getException();
        // and return the result of the evaluation at the end of the process
        return new IntegerNumber(v.getResult().toString());
    }

    public RealNumber evalReal(Expression e)  throws ComputeError {
        // create a new visitor to evaluate expressions
        EvaluatorReal v = new EvaluatorReal();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        if(v.getException() != null ) throw v.getException();
        // and return the result of the evaluation at the end of the process
        return new RealNumber(v.getResult().toString());
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
