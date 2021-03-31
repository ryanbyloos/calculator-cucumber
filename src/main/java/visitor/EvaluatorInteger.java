package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.MyNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.ImpossibleConversionError;
import calculator.operations.Operation;
import calculator.RealNumber;
import calculator.exceptions.BadAssignment;
import function.Variable;
import time.Date;
import time.Time;

import java.math.BigInteger;
import java.util.ArrayList;

public class EvaluatorInteger extends Evaluator {


    @Override
    public void visit(IntegerNumber n) { setComputedValue(n); }

    @Override
    public void visit(RealNumber n) {
        // try to convert else raise an exception
        try {
            setComputedValue(n.toIntegerNumber());
        }catch (ImpossibleConversionError e){
            setException(e);
        }
    }

    @Override
    public void visit(Variable v) {
        if(v.asValue()) // TODO HANDLE VALUE NOT DEFINED
            v.getValue().accept(this);
    }
    @Override
    public void visit(Operation o) {
        ArrayList<IntegerNumber> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add((IntegerNumber) getComputedValue());
        }
        //second loop to accummulate all the evaluated subresults
        IntegerNumber temp = evaluatedArgs.get(0);

        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            try {
                temp = o.op(temp, evaluatedArgs.get(counter));
            }catch (ComputeError e){
                setException(e);
            }
        }
        // store the accumulated result
        setComputedValue(temp);
    }

    @Override
    public void visit(Date date) {

    }

    @Override
    public void visit(Time time) {

    }
}
