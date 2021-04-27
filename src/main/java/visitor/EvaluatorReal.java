package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.VariableUnassignedError;
import calculator.operations.Operation;
import calculator.RealNumber;
import calculator.operations.functions.BigFunction;
import function.Variable;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EvaluatorReal extends Evaluator{
    @Override
    public void visit(MyNumber n){
        try {
            setComputedValue(n.convertTo(MyNumber.Type.REAL));
        }catch (ComputeError e){
            setException(e);
        }
    }

    @Override
    public void visit(Variable v) {
        if(!v.asValue()) setException(new VariableUnassignedError());
        else{
            v.getValue().accept(this);
        }
    }
    @Override
    public void visit(Operation o) {
        ArrayList<RealNumber> evaluatedArgs = new ArrayList<>();
        try {
            //first loop to recursively evaluate each subexpression
            for (Expression a : o.args) {
                a.accept(this);
                evaluatedArgs.add((RealNumber) getResult());
            }
            //second loop to accummulate all the evaluated subresults
            RealNumber temp = evaluatedArgs.get(0);
            for (int counter = 1; counter < evaluatedArgs.size(); counter++) {
                temp = o.op(temp, evaluatedArgs.get(counter));

            }
            // store the accumulated result
            setComputedValue(temp);
        }catch (ComputeError e){
            setException(e);
        }
    }

    public void visit(BigFunction bf){
        bf.args.get(0).accept(this);
        BigDecimal d;
        try{
            d = ((RealNumber)this.getResult()).getValue();
            setComputedValue(new RealNumber(bf.op(d).toPlainString()));
        }catch (ComputeError e){
            setException(e);
        }
    }
}
