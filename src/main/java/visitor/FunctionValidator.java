package visitor;

import calculator.*;
import calculator.operations.Operation;
import function.Function;
import function.Variable;
import time.Date;
import time.Time;

/**
 * Verify if a function contain only is variable.
 */
public class FunctionValidator extends Visitor{
    private boolean valid = false;
    private Variable var;
    private Calculator.Mode mode;

    /**
     * Verify if a function is correct
     * @param f function to verify
     * @return true if the variables are correctly assigned
     */
    public boolean verify(Function f,Calculator.Mode m){
        mode = m;
        valid = true;
        var = f.getVar();
        f.getExpression().accept(this);
        return valid;
    }

    @Override
    // Can't fail because an integer can also be considered as a real
    public void visit(IntegerNumber n) { }
    @Override
    public void visit(RealNumber n) {
        if ( mode == Calculator.Mode.INTEGER ){
            // Try to convert to int
            try{
                n.toBigInteger();
            }catch(ArithmeticException e){
                valid = false;
            }
        }
    }

    @Override
    public void visit(Variable v) { if (var == null || var != v) valid = false; }

    @Override
    public void visit(Operation o) {
        for(int i = 1 ; i < o.getArgs().size() ; i++) {
            o.getArgs().get(i).accept(this);
        }
    }

    @Override
    public void visit(Date date) {

    }

    @Override
    public void visit(Time time) { }
}
