package calculator.operations;

import calculator.Expression;
import calculator.Notation;
import calculator.exceptions.DivisionByZeroError;
import calculator.exceptions.IllegalConstruction;
import visitor.EvaluatorReal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        EvaluatorReal evaluator = new EvaluatorReal(); //TODO adapt for
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() != null && evaluator.getResult().compareTo(new BigDecimal("0")) == 0 ) { // If equals to 0
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
        EvaluatorReal evaluator = new EvaluatorReal(); //TODO adapt for
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() != null && evaluator.getResult().compareTo(new BigDecimal("0")) == 0) {  // If equals to 0
                throw new DivisionByZeroError();
            } else {
                args = new ArrayList<>(elist);
            }
        }
        symbol = "/";
        neutral = 1;
    }

    public BigInteger op(BigInteger l, BigInteger r) { return l.divide(r); }
    public BigDecimal op(BigDecimal l, BigDecimal r) throws DivisionByZeroError {
        try {
            return l.divide(r, Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
        }catch (ArithmeticException e){
            throw new DivisionByZeroError();
        }
    }
}
