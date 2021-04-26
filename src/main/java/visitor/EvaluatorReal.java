package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.MyNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.NotARealNumber;
import calculator.exceptions.VariableUnassignedError;
import calculator.operations.Operation;
import calculator.RealNumber;
import calculator.operations.functions.BigFunction;
import function.Variable;
import time.MyDate;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EvaluatorReal extends Evaluator{

    @Override
    public void visit(IntegerNumber n) {
        // convert Integer to decimal number
        setComputedValue(n.toRealNumber());
    }

    @Override
    public void visit(RealNumber n) { setComputedValue(n); }
    @Override
    public void visit(Variable v) {
        if(!v.asValue()) setException(new VariableUnassignedError());
        else v.getValue().accept(this);
    }
    @Override
    public void visit(Operation o) {
        ArrayList<RealNumber> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add((RealNumber) getComputedValue());
        }
        //second loop to accummulate all the evaluated subresults
        RealNumber temp = evaluatedArgs.get(0);
        for(int counter=1; counter<evaluatedArgs.size(); counter++) {
            try{
                temp = o.op(temp,evaluatedArgs.get(counter));
            }catch (ComputeError e){
                setException(e);
            }
        }
        // store the accumulated result
        setComputedValue(temp);
    }

    @Override
    public void visit(MyDate date) {
        setException(new ComputeError("Unsupported date in real mode"));
    }

    public void visit(BigFunction bf){
        bf.args.get(0).accept(this);
        BigDecimal d = ((RealNumber)this.getComputedValue()).getValue();

        try {
            setComputedValue(new RealNumber(bf.op(d).toPlainString()));
        }catch (NotARealNumber e){
            setException(e);
        }
    }
}
