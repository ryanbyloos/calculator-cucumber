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

/**
 * Class that represents an operation with two members
 */
public abstract class Operation extends Operator
{
    protected String symbol;
    protected int neutral; // the neutral element of the operation (e.g. 1 for *, 0 for +)
    public Notation notation = Notation.INFIX; //by default, expressions are rendered as strings using infix notation

    /**
     * Initialize an operation with an expression list and a notation type
     * @param elist
     * @param n Notation choisie
     * @throws IllegalConstruction
     */
    public /*constructor*/ Operation(List<Expression> elist, Notation n)
            throws IllegalConstruction {
        super(elist);
        notation = n;
    }

    public Operation(List<Expression> elist) throws IllegalConstruction {
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

    @Override
    final public String toString() {
        return toString(notation);
    }

    final public String toString(Notation n) {
        Printer p = new Printer(n);
        return p.getEval(this);
    }

    // the operation itself is specified in the subclasses
    /**
     * Method that describes the operation for two integer numbers
     * @param l first Integer
     * @param r second Integer
     * @return The result of the operation
     * @throws ComputeError If a computation error has been detected
     */
    abstract public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws ComputeError;
    /**
     * Method that describes the operation for two real numbers
     * @param l first Real
     * @param r second Real
     * @return The result of the operation
     * @throws ComputeError If a computation error has been detected
     */
    abstract public RealNumber op(RealNumber l, RealNumber r) throws ComputeError;

    public String getSymbol() {
        return symbol;
    }
}
