package visitor;

import calculator.*;
import function.Function;
import function.Variable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Validator extends Visitor{
    private boolean valid = true;
    private Variable var;

    Calculator.Mode mode;

    /**
     * Verify if there are creation error
     */
    public Validator(){ mode = null; }

    /**
     * Verify if there are computation error
     * (ex : Divide by zero )
     * @param m
     */
    public Validator(Calculator.Mode m){ mode = m; }

    public boolean isValid(){ return valid; }

    public void visit(Function f){
        var = f.getVar();
        f.getExpression().accept(this);
    }

    @Override
    public void visit(IntegerNumber n) { }
    @Override
    public void visit(RealNumber n) {
        if ( mode == Calculator.Mode.INTEGER ){
            valid = false; // TODO if can be cast to integer
        }
    }

    @Override
    public void visit(Variable v) { if (var == null || var != v) valid = false; }

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
