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

    private final String name; // name of the function
    private final ArrayList<Variable> vars; // list of variables
    private final Expression e;

    public Function(String name,ArrayList<Variable> vars,Expression e) throws  IllegalConstruction{
        // If find duplicate throw an IllegalConstruction
        for(int i = 0 ; i < vars.size() ; i++){
            for(int j = i+1 ; j < vars.size() ; j++){
                if(vars.get(i).equals(vars.get(j))) throw new IllegalConstruction();
            }
        }

        this.name = name;
        this.vars = vars;
        this.e = e;

        Validator v = new Validator(this);
        if(!v.isValid()) throw  new IllegalConstruction();
    }

    public BigInteger compute(List<MyNumber> values, EvaluatorInteger v) throws  BadAssignment{
        // set value
        set(values);

        // verify if assignation is valid
        Validator validator =  new Validator(this, Calculator.Mode.INTEGER);
        if (!validator.isValid()) throw new BadAssignment();

        e.accept(v);
        clear();
        return v.getResult();
    }

    public BigDecimal compute(List<MyNumber> values, EvaluatorReal v) throws  BadAssignment{
        // set value
        set(values);

        // verify if assignation is valid
        Validator validator =  new Validator(this, Calculator.Mode.REAL);
        if (!validator.isValid()) throw new BadAssignment();

        e.accept(v);
        clear();
        return v.getResult();
    }

    /**
     * Set value to variable
     * @param values
     */
    private void set(List<MyNumber> values) throws BadAssignment {
        if(values.size() == vars.size()){
            for(int i = 0 ; i < values.size() ; i++){
                vars.get(i).assignValue(values.get(i));
            }
        }else throw new BadAssignment();
    }

    /**
     * Clear variable
     */
    private void clear(){ for(Variable v : vars) v.clear(); }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        if (vars.size() > 0){
            tmp = new StringBuilder(vars.get(0).completeString());
            for(int i = 1 ; i < vars.size() ; i++)
                tmp.append(",").append(vars.get(i).completeString());
        }
        return name+"("+tmp+")"+":"+e.toString();
    }

    public String toString(List<MyNumber> values) throws BadAssignment {
        set(values);
        String tmp = toString();
        clear();
        return tmp;
    }

    public ArrayList<Variable> getVars() { return vars; }

    public Expression getExpression() { return e; }
}
