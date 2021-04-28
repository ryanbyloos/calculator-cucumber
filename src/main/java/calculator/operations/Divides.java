package calculator.operations;

import calculator.*;
import calculator.exceptions.*;
import visitor.EvaluatorNumber;

import java.util.ArrayList;
import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        setUp(elist);
    }

    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        setUp(elist);
    }

    public void setUp(List<Expression> elist) throws  IllegalConstruction{
        EvaluatorNumber evaluator = new EvaluatorNumber(Calculator.Mode.REAL);
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            try{
                if (evaluator.getResult() != null && evaluator.getResult().equals(new RealNumber("0"))) {  // If equals to 0
                    throw new DivisionByZeroError();
                }
            }catch (VariableUnassignedError ignored) { } //because variable can be initialise later
//            args = new ArrayList<>(elist);
        }
        symbol = "/";
        neutral = 1;
    }

    @Override
    public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws DivisionByZeroError {
        return l.divide(r);
    }
    @Override
    public RealNumber op(RealNumber l, RealNumber r) throws DivisionByZeroError {
        return l.divide(r);
    }
}
