package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Operator;
import ch.obermuhlner.math.big.BigDecimalMath;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public abstract class BigFunction extends Operator {
    public MathContext mc = MathContext.DECIMAL128;
    public final BigDecimal PI = BigDecimalMath.pi(mc);
    public final BigDecimal E = BigDecimalMath.e(mc);

    public BigFunction(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public void accept(Visitor v) {
        // ask each of the argument expressions of the current operation to accept the visitor
        for (Expression a : getArgs()) {
            a.accept(v);
        }
        // and then visit the current operation itself
        v.visit(this);
    }

    abstract public BigDecimal op(BigDecimal x);
}
