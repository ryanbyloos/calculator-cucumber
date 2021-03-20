package function;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Function implements Expression {

    private String name; // name of the function
    private ArrayList<Variable> vars; // list of variables
    private Expression e;


    public Function(ArrayList<Variable> vars,Expression e){
        this.vars = vars;
        this.e = e;
    }


    public void assignValueToVariable(List<MyNumber> values){
        if(values.size() == vars.size()){
            for(int i = 0 ; i < values.size() ; i++){
                vars.get(i).assignValue(values.get(i));
            }
        }
    }

    public void clear(){
        for(Variable v : vars){
            v.clear();
        }
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public void accept(Visitor v) {
        e.accept(v);
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
}
