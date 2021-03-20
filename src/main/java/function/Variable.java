package function;

import calculator.Expression;
import calculator.MyNumber;
import visitor.Visitor;

public class Variable implements Expression {
    private String varName;
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
        if(this.asValue())
            return value.toString();
        return varName;
    }

    @Override
    public void accept(Visitor v) {
        if(asValue()) value.accept(v);
        v.visit(this);
    }

    @Override
    public Integer countDepth() {
        //TODO
        return null;
    }

    @Override
    public Integer countOps() {
        //TODO
        return null;
    }

    @Override
    public Integer countNbs() {
        //TODO
        return null;
    }

    public String getVarName() {
        return varName;
    }

    public MyNumber getValue() {
        return value;
    }
}
