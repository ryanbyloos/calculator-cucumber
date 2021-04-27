package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.exceptions.ComputeError;
import calculator.operations.ComputeDate;
import calculator.operations.Operation;
import calculator.RealNumber;
import function.Variable;
import time.MyDate;

import java.util.ArrayList;

public class EvaluatorDate extends Evaluator{


    @Override
    public void visit(IntegerNumber n) {
        // convert Integer to decimal number
        //setComputedValue(n.toRealNumber());
    }

    @Override
    public void visit(RealNumber n) {}
    @Override
    public void visit(Variable v) {}
    @Override
    public void visit(Operation o)  {
        ArrayList<MyDate> evaluatedArgs = new ArrayList<>();
        try {
            //first loop to recursively evaluate each subexpression
            for(Expression a:o.args) {
                a.accept(this);
                evaluatedArgs.add((MyDate) getResult());
            }
            //second loop to accummulate all the evaluated subresults
            MyDate temp = evaluatedArgs.get(0);
            int max = evaluatedArgs.size();
            for(int counter=1; counter<max; counter++) {
                if (o instanceof ComputeDate)
                    temp = ((ComputeDate)o).op(temp,evaluatedArgs.get(counter));
                else {
                    setException(new ComputeError("Unsupported Operator for date"));
                }
            }
            // store the accumulated result
            setComputedValue(temp);
        }catch (ComputeError e){
            setException(e);
        }
    }

    @Override
    public void visit(MyDate date) {
        setComputedValue(date);
    }

}
