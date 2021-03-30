package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.ImpossibleConversionError;
import calculator.operations.Operation;
import calculator.RealNumber;
import calculator.exceptions.BadAssignment;
import function.Variable;
import time.Time;

import java.math.BigInteger;
import java.util.ArrayList;

public class EvaluatorInteger extends Visitor {
    private BigInteger computedValue;
    private ComputeError exception; // if the expression throw an exception

    public BigInteger getResult() { return computedValue; }
    @Override
    public void visit(IntegerNumber n) {
        computedValue = n.getValue();
    }

    @Override
    public void visit(RealNumber n) {
        // try to convert else raise an exception
        try {
            computedValue = n.toBigInteger();
        }catch (ArithmeticException e){
            exception = new ImpossibleConversionError();
        }
    }

    @Override
    public void visit(Variable v) {
        if(v.asValue()) // TODO HANDLE VALUE NOT DEFINED
            v.getValue().accept(this);
    }
    @Override
    public void visit(Operation o) {
        ArrayList<BigInteger> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        BigInteger temp = evaluatedArgs.get(0);

        // TODO VERIFY IF LIST IS GOOD
        // CONVERT

        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            try {
                temp = o.op(temp, evaluatedArgs.get(counter));
            }catch (ComputeError e){
                exception = e;
            }
        }
        // store the accumulated result
        computedValue = temp;
    }

    @Override
    public void visit(Time time) {

    }

    public ComputeError getException() { return exception; }
}
