package function;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Function implements Expression{

    private String name; // name of the function
    private ArrayList<Variable> vars; // list of variables
    private Expression e;

    private boolean isAssign = false;

    public Function(String name,ArrayList<Variable> vars,Expression e){
        this.name = name;
        this.vars = vars;
        this.e = e;
    }

    /**
     * Set value to variable
     * @param values
     */
    public void set(List<MyNumber> values) throws IllegalConstruction{
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
    public void clear(){
        for(Variable v : vars) v.clear();
        isAssign = false;
    }

    public void accept(Visitor v){
        e.accept(v);
    }

    @Override
    public Integer countDepth() { return e.countDepth(); }

    @Override
    public Integer countOps() { return e.countOps(); }

    @Override
    public Integer countNbs() { return e.countNbs(); }

    @Override
    public String toString() {
        return e.toString();
    }
}
