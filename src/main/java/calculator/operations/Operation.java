package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import visitor.Printer;
import visitor.Visitor;

import java.util.List;

public abstract class Operation extends Operator {
    protected String symbol;
    protected int neutral; // the neutral element of the operation (e.g. 1 for *, 0 for +)
    public Notation notation = Notation.INFIX; //by default, expressions are rendered as strings using infix notation

    public /*constructor*/ Operation(List<Expression> elist, Notation n)
            throws IllegalConstruction {
        super(elist);
        notation = n;
    }

    public Operation(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    abstract public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws ComputeError;
    abstract public RealNumber op(RealNumber l, RealNumber r) throws ComputeError;
    // the operation itself is specified in the subclasses

    @Override
    final public String toString() {
        return toString(notation);
    }

    final public String toString(Notation n) {
        Printer p = new Printer(n);
        return p.getEval(this);
    }
    public String getSymbol() {
        return symbol;
    }

    public void accept(Visitor v) {
        // ask each of the argument expressions of the current operation to accept the visitor
        for (Expression a : getArgs()) {
            a.accept(v);
        }
        // and then visit the current operation itself
        v.visit(this);
    }
}
