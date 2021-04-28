package calculator.operations;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Operator implements Expression {
    private List<Expression> args;

    // It is not allowed to create operation that have a null list of arguments.
    // Note that it is allowed to have an EMPTY list of arguments.
    public /*constructor*/ Operator(List<Expression> elist)
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
    public void setArgs(int i,Expression e){
        args.set(i,e);
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

    // add more arguments to the existing list of arguments args
    public void addMoreParams(List<Expression> params) {
        args.addAll(params);
    }

    public abstract  void accept(Visitor v);

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

}
