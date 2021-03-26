package visitor;

import calculator.*;
import function.Function;
import function.Variable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Validator extends Visitor{
    private boolean valid = true;
    private final ArrayList<Variable> vars;

    Calculator.Mode mode;

    /**
     * Verify if there are creation error
     * @param f
     */
    public Validator(Function f){
        mode = null;
        vars = f.getVars();
        f.getExpression().accept(this);
    }

    /**
     * Verify if there are computation error
     * (ex : Divide by zero )
     * @param f
     * @param m
     */
    public Validator(Function f,Calculator.Mode m){
        mode = m;
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
        if (mode!= null && o instanceof  Divides) {
            for(int i = 1 ; i < o.getArgs().size() ; i++){
                if(mode == Calculator.Mode.INTEGER){
                    EvaluatorInteger eval = new EvaluatorInteger();
                    o.getArgs().get(i).accept(eval);
                    if (eval.getResult().compareTo(new BigInteger("0")) == 0){
                        valid = false;
                    }
                }else if (mode == Calculator.Mode.REAL){
                    EvaluatorReal eval = new EvaluatorReal();
                    o.getArgs().get(i).accept(eval);
                    if (eval.getResult().compareTo(new BigDecimal("0")) == 0){
                        valid = false;
                    }
                }
            }
        }

        for(int i = 1 ; i < o.getArgs().size() ; i++) {
            o.getArgs().get(i).accept(this);
        }
    }
}
