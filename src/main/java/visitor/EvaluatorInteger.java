package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.MyNumber;
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
    public void visit(MyNumber n){
//        if ( n instanceof RealNumber){
//            // try to convert else raise an exception
//            try {
//                setComputedValue(((RealNumber)n).toIntegerNumber());
//            }catch (NotAnIntegerNumber e){
//                setException(e);
//            }
//        }else {
//            setComputedValue(n);
//        }
        try {
            setComputedValue(n.convertTo(MyNumber.Type.INTEGER));
        }catch (ComputeError e){
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
        try {
            //first loop to recursively evaluate each subexpression
            for(Expression a:o.args) {
                a.accept(this);
                evaluatedArgs.add((IntegerNumber) getResult());
            }
            //second loop to accummulate all the evaluated subresults
            IntegerNumber temp = evaluatedArgs.get(0);

            for(int counter=1; counter<evaluatedArgs.size(); counter++) {
                temp = o.op(temp, evaluatedArgs.get(counter));
            }
            // store the accumulated result
            setComputedValue(temp);
        }catch (ComputeError e){
            setException(e);
        }
    }
}
