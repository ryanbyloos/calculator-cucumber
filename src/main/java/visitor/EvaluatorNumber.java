package visitor;

import calculator.*;
import calculator.exceptions.ComputeError;
import calculator.exceptions.VariableUnassignedError;
import calculator.operations.Operation;
import calculator.operations.functions.BigFunction;
import function.Variable;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EvaluatorNumber extends Evaluator{
    Calculator.Mode mode;
    public EvaluatorNumber(Calculator.Mode m){
        mode = m;
    }

    @Override
    public void visit(MyNumber n){
        try {
            setComputedValue(n.convertTo(mode));
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
        ArrayList<MyNumber> evaluatedArgs = new ArrayList<>();
        try {
            //first loop to recursively evaluate each subexpression
            for (Expression a : o.getArgs()) {
                a.accept(this);
                evaluatedArgs.add(getResult());
            }
            //second loop to accummulate all the evaluated subresults
            MyNumber temp = evaluatedArgs.get(0);
            for (int counter = 1; counter < evaluatedArgs.size(); counter++) {
                switch (mode){
                    case INTEGER:
                        temp = o.op((IntegerNumber) temp,(IntegerNumber) evaluatedArgs.get(counter));
                        break;
                    case REAL:
                        temp = o.op((RealNumber) temp,(RealNumber) evaluatedArgs.get(counter));
                        break;
                }
            }
            // store the accumulated result
            setComputedValue(temp);
        }catch (ComputeError e){
            setException(e);
        }
    }

    @Override
    public void visit(BigFunction bf){
        bf.getArgs().get(0).accept(this);
        BigDecimal d;
        try{
            d = ((RealNumber)this.getResult()).getValue();
            setComputedValue(new RealNumber(bf.op(d).toPlainString()));
        }catch (ComputeError e){
            setException(e);
        }
    }
}
