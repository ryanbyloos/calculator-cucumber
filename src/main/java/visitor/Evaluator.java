package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import function.Function;
import function.Variable;

import java.util.ArrayList;

public class Evaluator extends Visitor {

    private int computedValue;

    public Integer getResult() { return computedValue; }

    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }

    @Override
    public void visit(Variable v) {
        //TODO
    }

    public void visit(Operation o) {
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            temp = o.op(temp,evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

    @Override
    public void visit(Function f) {
        //TODO
    }

}
