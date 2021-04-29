package function;

import calculator.Expression;
import visitor.Visitor;

/**
 *
 */
public class Variable implements Expression {
    public final String varName = "X";
    private Expression value;

    /**
     * Set the value
     * @param v value to assign
     */
    public void assignValue(Expression v){ value = v; }

    /**
     * Set value to null
     */
    public void clear(){
        value = null;
    }

    /**
     * Return true is this variable as an assigned value
     * @return
     */
    public boolean asValue(){
        return value != null;
    }

    @Override
    public String toString() { return varName; }

    public String completeString(){
        if (value == null) return varName;
        return varName+":"+ value;
    }

    @Override
    public void accept(Visitor v) {
        if(asValue()) value.accept(v);
        v.visit(this);
    }

    @Override
    final public Integer countDepth() {
        if(value == null) return 0;
        return value.countDepth();
    }

    final public Integer countOps() {
        if(value == null) return 0;
        return value.countOps();
    }

    final public Integer countNbs() {
        if(value == null) return 0;
        return value.countNbs();
    }

    public Expression getValue() { return value; }
}
