package function;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import visitor.EvaluatorInteger;
import visitor.FunctionValidator;

import java.math.BigInteger;

/**
 * Class that represents a function with one variable x
 */
public class Function {
    private final Variable var;
    private final Expression e;

    public Function(Variable var,Expression e) throws IllegalConstruction {
        this.var = var;
        this.e = e;

        // check if there are only the same var in e
        FunctionValidator v = new FunctionValidator();

        if(!v.verify(this,null)) throw  new IllegalConstruction();
    }

    public BigInteger compute(MyNumber value, EvaluatorInteger v) throws BadAssignment {
        // set value
        var.assignValue(value);

        // verify if assignation is valid
        FunctionValidator validator =  new FunctionValidator();
        if (!validator.verify(this, Calculator.Mode.INTEGER)) throw new BadAssignment();

        e.accept(v);
        var.clear();
        return v.getResult();
    }

    public void setValue(MyNumber n) throws BadAssignment{
        if (n == null) throw new BadAssignment();
        var.assignValue(n);
    }

    public void clearValue(){ var.clear(); }

    @Override
    public String toString() { return e.toString(); }

    public String toString(MyNumber value) throws BadAssignment {
        if(value == null) throw new BadAssignment();

        var.assignValue(value);
        String tmp = toString();
        var.clear();
        return tmp;
    }

    public Variable getVar() { return var; }

    public Expression getExpression() { return e; }
}
