package function;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import visitor.Evaluator;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private String name; // name of the function
    private ArrayList<Variable> vars; // list of variables
    private Expression e;

    private boolean isAssign = false;

    public Function(String name,ArrayList<Variable> vars,Expression e){
        this.name = name;
        this.vars = vars;
        this.e = e;
    }

    public int compute(List<MyNumber> values) throws  IllegalConstruction{
        Evaluator v = new Evaluator();

        set(values);
        e.accept(v);
        clear();
        return v.getResult();
    }

    /**
     * Set value to variable
     * @param values
     */
    private void set(List<MyNumber> values) throws IllegalConstruction{
        if(values.size() == vars.size()){
            for(int i = 0 ; i < values.size() ; i++){
                vars.get(i).assignValue(values.get(i));
            }
            isAssign = true;
        }else throw new IllegalConstruction();
    }

    /**
     * Clear variable
     */
    private void clear(){
        for(Variable v : vars) v.clear();
        isAssign = false;
    }

    public void accept(Visitor v){
        e.accept(v);
    }

    @Override
    public String toString() {
        String tmp = "";
        if (vars.size() >= 0){
            tmp = vars.get(0).getVarName();
            for(int i = 1 ; i < vars.size() ; i++)
                tmp+=","+vars.get(i).getVarName();
        }
        return name+"("+tmp+")"+":"+e.toString();
    }

    public String toString(List<MyNumber> values) throws  IllegalConstruction {
        set(values);
        String tmp = toString();
        clear();
        return tmp;
    }
}
