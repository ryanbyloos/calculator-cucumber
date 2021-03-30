package function;

import calculator.*;
import visitor.EvaluatorInteger;
import visitor.EvaluatorReal;
import visitor.Validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Function {
    private final Variable var;
    private final Expression e;

    public Function(Variable var,Expression e) throws  IllegalConstruction{
        this.var = var;
        this.e = e;

        // check if there are only the same var in e
        Validator v = new Validator();
        v.visit(this);
        if(!v.isValid()) throw  new IllegalConstruction();
    }

    public BigInteger compute(MyNumber value, EvaluatorInteger v) throws  BadAssignment{
        // set value
        var.assignValue(value);

        // verify if assignation is valid
        Validator validator =  new Validator(Calculator.Mode.INTEGER);
        validator.visit(this);
        if (!validator.isValid()) throw new BadAssignment();

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
