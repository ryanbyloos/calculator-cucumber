package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import ch.obermuhlner.math.big.BigDecimalMath;
import visitor.EvaluatorReal;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public abstract class BigFunction implements Expression {

    public List<Expression> args;

    public MathContext mc = MathContext.DECIMAL128;
    public final BigDecimal PI = BigDecimalMath.pi(mc);
    public final BigDecimal E = BigDecimalMath.e(mc);

    @Override
    public void accept(Visitor v) {
        ((EvaluatorReal)v).visit(this);
    }

    @Override
    public Integer countDepth() {
        return null;
    }

    @Override
    public Integer countOps() {
        return null;
    }

    @Override
    public Integer countNbs() {
        return null;
    }

    public /*constructor*/ BigFunction(List<Expression> elist)
            throws IllegalConstruction {
        if (elist == null) {
            throw new IllegalConstruction();
        } else {
            args = new ArrayList<>(elist);
        }
    }

    abstract public BigDecimal op(BigDecimal x);
}
