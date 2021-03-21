package function;

import calculator.*;
import visitor.Evaluator;
import visitor.Validator;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private final String name; // name of the function
    private final ArrayList<Variable> vars; // list of variables
    private final Expression e;

    private boolean isAssign = false;

    public Function(String name,ArrayList<Variable> vars,Expression e) throws  IllegalConstruction{
        this.name = name;
        this.vars = vars;
        this.e = e;

        Validator v = new Validator(this);
        if(!v.isValid()) throw  new IllegalConstruction();
    }

    public int compute(List<MyNumber> values,Evaluator v) throws  BadAssignment{
        set(values);
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
            isAssign = true;
        }else throw new BadAssignment();
    }

    /**
     * Clear variable
     */
    private void clear(){
        for(Variable v : vars) v.clear();
        isAssign = false;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        if (vars.size() >= 0){
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
