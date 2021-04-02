package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.DivisionByZeroError;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.UselessComputation;
import time.MyDate;
import visitor.EvaluatorReal;

import java.util.ArrayList;
import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        EvaluatorReal evaluator = new EvaluatorReal();
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() != null && evaluator.getResult().equals(new RealNumber("0"))) { // If equals to 0
                throw new DivisionByZeroError();
            } else {
                args = new ArrayList<>(elist);
            }
        }
        symbol = "/";
        neutral = 1;
    }

    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        EvaluatorReal evaluator = new EvaluatorReal();
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() != null && evaluator.getResult().equals(new RealNumber("0"))) {  // If equals to 0
                throw new DivisionByZeroError();
            } else {
                args = new ArrayList<>(elist);
            }
        }
        symbol = "/";
        neutral = 1;
    }

    public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws DivisionByZeroError {
        return l.divide(r);
    }
    public RealNumber op(RealNumber l, RealNumber r) throws DivisionByZeroError {
        return l.divide(r);
    }


    @Override
    public MyDate op(MyDate l, MyDate r) throws ComputeError {
        throw new UselessComputation() ;
    }
}
