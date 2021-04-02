package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.exceptions.VariableUnassignedError;
import calculator.operations.Operation;
import calculator.RealNumber;
import function.Variable;
import time.MyDate;

import java.util.ArrayList;

public class EvaluatorInteger extends Evaluator {


    @Override
    public void visit(IntegerNumber n) { setComputedValue(n); }

    @Override
    public void visit(RealNumber n) {
        // try to convert else raise an exception
        try {
            setComputedValue(n.toIntegerNumber());
        }catch (NotAnIntegerNumber e){
            setException(e);
        }
    }

    @Override
    public void visit(Variable v) {
        if(!v.asValue()) setException(new VariableUnassignedError());
        else v.getValue().accept(this);
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

        for(int counter=1; counter<evaluatedArgs.size(); counter++) {
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
    public void visit(MyDate date) {
        setException(new ComputeError("Unsupported date in integer mode"));
    }


}
