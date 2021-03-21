package visitor;

import calculator.MyNumber;
import calculator.Operation;
import function.Function;
import function.Variable;

import java.util.ArrayList;

public class Validator extends Visitor{
    private boolean valid = true;
    private final ArrayList<Variable> vars;

    public Validator(Function f){
        vars = f.getVars();
        f.getExpression().accept(this);
    }

    public boolean isValid(){ return valid; }

    @Override
    public void visit(MyNumber n) {

    }

    @Override
    public void visit(Variable v) {
        if (vars == null || !vars.contains(v)) valid = false;
    }

    @Override
    public void visit(Operation o) {

    }
}
