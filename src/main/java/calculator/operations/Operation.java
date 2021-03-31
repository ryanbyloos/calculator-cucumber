package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import visitor.Printer;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class Operation implements Expression {
    public static int CONST_ROUNDED = 10;
    public List<Expression> args;
    protected String symbol;
    protected int neutral; // the neutral element of the operation (e.g. 1 for *, 0 for +)
    public Notation notation = Notation.INFIX; //by default, expressions are rendered as strings using infix notation

    // It is not allowed to create operation that have a null list of arguments.
    // Note that it is allowed to have an EMPTY list of arguments. // TODO
    public /*constructor*/ Operation(List<Expression> elist)
            throws IllegalConstruction {
        if (elist == null) {
            throw new IllegalConstruction();
        } else {
            args = new ArrayList<>(elist);
        }
    }

    public List<Expression> getArgs() {
        return args;
    }

    public /*constructor*/ Operation(List<Expression> elist, Notation n)
            throws IllegalConstruction {
        this(elist);
        notation = n;
    }

    abstract public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws ComputeError;
    abstract public RealNumber op(RealNumber l, RealNumber r) throws ComputeError;
    // the operation itself is specified in the subclasses

    // add more arguments to the existing list of arguments args
    public void addMoreParams(List<Expression> params) {
        args.addAll(params);
    }

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

    @Override
    final public String toString() {
        return toString(notation);
    }

    final public String toString(Notation n) {
        Printer p = new Printer(n);
        return p.getEval(this);
    }

    //Two Operation expressions are equal if their list of arguments is equal and they are the same operation
    @Override
    public boolean equals(Object o) {
        if (o == null) return false; // No object should be equal to null

        if (this == o) return true; // If it's the same object, they're obviously equal

        if (getClass() != o.getClass())
            return false; // getClass() instead of instanceof() because an addition is not the same as a multiplication

        Operation other = (Operation) o;
        return this.args.equals(other.getArgs());
    }

    public String getSymbol() {
        return symbol;
    }
}
