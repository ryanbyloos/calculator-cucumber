package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Operator;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 * Main class for all the main functions that are used
 */
public abstract class BigFunction extends Operator {
    public MathContext mc = MathContext.DECIMAL128;

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
