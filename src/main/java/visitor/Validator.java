package visitor;

import calculator.*;
import function.Function;
import function.Variable;

import java.util.ArrayList;

public class Validator extends Visitor{
    private boolean valid = true;
    private final ArrayList<Variable> vars;

    Calculator.Mode mode;

    public Validator(Function f){
        vars = f.getVars();
        f.getExpression().accept(this);
    }

    public Validator(Calculator.Mode m, Expression e){
        mode = m;
        e.accept(this);
        vars = new ArrayList<>();
    }

    public boolean isValid(){ return valid; }

    @Override
    public void visit(IntegerNumber n) { }
    @Override
    public void visit(RealNumber n) {
        if ( mode == Calculator.Mode.INTEGER ){
            valid = false; // TODO if can be cast to integer
        }
    }

    @Override
    public void visit(Variable v) {
        if (vars == null || !vars.contains(v)) valid = false;
    }

    @Override
    public void visit(Operation o) {

    }
}
