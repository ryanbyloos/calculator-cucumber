package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import ch.obermuhlner.math.big.BigDecimalMath;
import visitor.EvaluatorNumber;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public abstract class BigFunction implements Expression {

    private List<Expression> args;

    public MathContext mc = MathContext.DECIMAL128;
    public final BigDecimal PI = BigDecimalMath.pi(mc);
    public final BigDecimal E = BigDecimalMath.e(mc);

    @Override
    public void accept(Visitor v) {
        // ask each of the argument expressions of the current operation to accept the visitor
        for (Expression a : args) {
            a.accept(v);
        }
        // and then visit the current operation itself
        v.visit(this);
    }

    final public Integer countDepth() {
        // use of Java 8 functional programming capabilities
        return 1 + args.stream()
                .mapToInt(Expression::countDepth)
                .max()
                .getAsInt();
    }

    final public Integer countOps() {
        // use of Java 8 functional programming capabilities
        return 1 + args.stream()
                .mapToInt(Expression::countOps)
                .reduce(Integer::sum)
                .getAsInt();
    }

    final public Integer countNbs() {
        // use of Java 8 functional programming capabilities
        return args.stream()
                .mapToInt(Expression::countNbs)
                .reduce(Integer::sum)
                .getAsInt();
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

    public List<Expression> getArgs() {
        return args;
    }
    public void setArgs(int i,Expression e){
        args.set(i,e);
    }
}
