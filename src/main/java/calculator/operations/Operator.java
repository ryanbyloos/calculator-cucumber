package calculator.operations;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that applies mathematical operations on expressions
 */
public abstract class Operator implements Expression
{
    private final List<Expression> args;

    /**
     * Initialize an operator with an expression list
     *
     * It is not allowed to create operator that have a null list of arguments.
     * Note that it is allowed to have an EMPTY list of arguments.
     * @param elist List of operator's arguments
     * @throws IllegalConstruction
     */
    public /*constructor*/ Operator(List<Expression> elist)
            throws IllegalConstruction {
        if (elist == null) {
            throw new IllegalConstruction();
        } else {
            args = new ArrayList<>(elist);
        }
    }

    /**
     * Add more arguments to the existing list of arguments args
     * @param params list of expressions to add
     */
    public void addMoreParams(List<Expression> params) {
        args.addAll(params);
    }

    @Override
    public abstract  void accept(Visitor v);
    @Override
    final public Integer countDepth() {
        // use of Java 8 functional programming capabilities
        return 1 + args.stream()
                .mapToInt(Expression::countDepth)
                .max()
                .getAsInt();
    }
    @Override
    final public Integer countOps() {
        // use of Java 8 functional programming capabilities
        return 1 + args.stream()
                .mapToInt(Expression::countOps)
                .reduce(Integer::sum)
                .getAsInt();
    }
    @Override
    final public Integer countNbs() {
        // use of Java 8 functional programming capabilities
        return args.stream()
                .mapToInt(Expression::countNbs)
                .reduce(Integer::sum)
                .getAsInt();
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

    public List<Expression> getArgs() {
        return args;
    }
    public void setArgs(int i,Expression e){
        args.set(i,e);
    }
}
