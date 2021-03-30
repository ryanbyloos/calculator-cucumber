package visitor;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Operation;
import calculator.RealNumber;
import function.Variable;
import time.Time;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EvaluatorReal extends Visitor{
    private BigDecimal computedValue;

    public BigDecimal getResult() { return computedValue; }

    @Override
    public void visit(IntegerNumber n) {
        // convert Integer to decimal number
        computedValue = new BigDecimal(n.toString());
    }

    @Override
    public void visit(RealNumber n) {
        computedValue = n.getValue();
    }
    @Override
    public void visit(Variable v) {
        if(v.asValue()) // TODO HANDLE VALUE NOT DEFINED
            v.getValue().accept(this);
    }
    @Override
    public void visit(Operation o) {
        ArrayList<BigDecimal> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        BigDecimal temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            temp = o.op(temp,evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

    @Override
    public void visit(Time time) {

    }
}
