package function;

import calculator.Expression;
import calculator.MyNumber;
import visitor.Visitor;

public class Variable implements Expression {
    private final String varName;
    private MyNumber value;

    public Variable(String name){
        this.varName = name;
    }

    /**
     * Set the value
     * @param num
     */
    public void assignValue(MyNumber num){
        value = num;
    }

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
    public String toString() {
        return varName;
    }

    public String completeString(){
        if (value == null) return varName;
        return varName+":"+value.toString();
    }

    @Override
    public void accept(Visitor v) {
        if(asValue()) value.accept(v);
        v.visit(this);
    }

    @Override
    public Integer countDepth() { return 0; }

    @Override
    public Integer countOps() { return 0; }

    @Override
    public Integer countNbs() { return 1; }

    public int getValue(){
        return value.getValue();
    }

    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof Variable)) {
            return false;
        }
        return this.varName.equals(((Variable) o).varName);
    }
}
