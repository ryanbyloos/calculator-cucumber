package calculator;

import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConvertionArgument;
import Converter.Temperature;
import function.Function;
import time.MyDate;
import visitor.EvaluatorDate;
import visitor.EvaluatorNumber;
import Converter.Unit;

import java.math.BigDecimal;
import java.util.HashMap;

@SuppressWarnings("JavaDoc")
public class Calculator {
    public enum Mode {INTEGER,REAL}

    private final HashMap<String,Function> storedFun;

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
    public Calculator(Mode m,int precision){
        this(m);
        RealNumber.setPrecision(precision);
    }


    public void print(Expression e) {
        try {
            System.out.println("The result of evaluating expression " + e);
            if (mode == Mode.INTEGER)
                System.out.println("is: " + evalInteger(e) + ".");
            else if (mode == Mode.REAL)
                System.out.println("is: " + evalReal(e) + ".");
            System.out.println();
        }catch (ComputeError ce){
            System.out.println(ce.getMessage());
        }
    }

    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    /**
     * Add a function to calculator memory
     */
    public void addFunction(String key,Function f){
        storedFun.put(key,f);
    }

    /**
     * Evaluates the expression or returns a description of its error
     * @param e expression to evaluate
     * @return a ready to print string for the user
     */
    public String eval(Expression e){
        try {
            switch (mode){
                case INTEGER:
                    return evalInteger(e).toString();
                case REAL:
                    return evalReal(e).toString();
                default:
                    return "Unsupported Mode";
            }
        }catch(ComputeError ce){
            return ce.getMessage();
        }
    }

    /**
     * Evaluate expression un a integer context
     * @param e expression to evaluate
     * @return IntegerNumber that results from the evaluation
     * @throws ComputeError if an error was detected during the evaluation
     */
    public IntegerNumber evalInteger(Expression e) throws ComputeError{
        // create a new visitor to evaluate expressions
        EvaluatorNumber v = new EvaluatorNumber(Mode.INTEGER);
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return new IntegerNumber(v.getResult().toString());
    }

    /**
     * Evaluate expression un a real context
     * @param e expression to evaluate
     * @return RealNumber that results from the evaluation
     * @throws ComputeError if an error was detected during the evaluation
     */
    public RealNumber evalReal(Expression e)  throws ComputeError {
        // create a new visitor to evaluate expressions
        EvaluatorNumber v = new EvaluatorNumber(Mode.REAL);
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return new RealNumber(v.getResult().toString());
    }

    /**
     * Convert an expression to a base unit to another from the same type
     * @param e expression to convert
     * @return BigDecimal that results from the convertion
     * @throws ComputeError if an error was detected during the evaluation, IllegalConvertionArgument if the base unit and the aimed unit types are different
     */
    public BigDecimal convert(Expression e,Unit base, Unit aimed) throws IllegalConvertionArgument,ComputeError{
        if(!base.getType().equals(aimed.getType())){
            throw new IllegalConvertionArgument();
        }

        BigDecimal eval = evalReal(e).getValue();
        eval = eval.divide(base.getratio(), RealNumber.getMc());
        eval = eval.multiply(aimed.getratio());
        return eval;
    }
    /**
     * Get the string from a conversion
     * @param e expression to convert
     * @return String resulting from the conversion
     * @throws IllegalConvertionArgument if the base unit and the aimed unit types are different
     */
    public String convertToString(Expression e,Unit base, Unit aimed) throws IllegalConvertionArgument{
        if(!base.getType().equals(aimed.getType())){
            throw new IllegalConvertionArgument();
        }

        try {
            RealNumber eval = new RealNumber(convert(e, base, aimed));

            return eval +  " " +aimed.getFullName();
        }catch(ComputeError ce){
            return "ERROR : "+ce.getMessage();
        }
    }
    /**
     * Convert an expression to a base temperature unit to another temperature unit
     * @param e expression to convert
     * @return BigDecimal that results from the convertion
     * @throws ComputeError if an error was detected during the evaluation
     */
    public BigDecimal convert(Expression e, Temperature base, Temperature aimed) throws  ComputeError {
        BigDecimal eval = evalReal(e).getValue();
        eval = eval.subtract(base.getConstant());
        eval = eval.divide(base.getRatio(),RealNumber.getMc());
        eval = eval.multiply(aimed.getRatio());
        eval = eval.add(aimed.getConstant());

        return eval;
    }
    /**
     * get The string from a temperature conversion
     * @param e expression to convert
     * @return String resulting from the conversion
     */
    public String convertToString(Expression e, Temperature base, Temperature aimed) {
        try {
            RealNumber eval = new RealNumber(convert(e,base,aimed));
            return eval + " "+aimed.getFullName();
        }catch (ComputeError ce){
            return "ERROR : "+ce.getMessage();
        }
    }

    public HashMap<String, Function> getStoredFun() { return storedFun; }

    /**
     * Eval an expression resulting in a date
     * @param e expression to convert
     * @return MyDate resulting from the evaluation
     * @throws ComputeError if an error was detected during the evaluation
     */
    public MyDate evalDate(Expression e)  throws ComputeError {
        // create a new visitor to evaluate expressions
        EvaluatorDate v = new EvaluatorDate();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return new MyDate(v.getResult().toString());
    }
    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */

    public Mode getMode() { return mode; }
}
