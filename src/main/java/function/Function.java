package function;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Operation;
import time.MyDate;
import visitor.Visitor;

/**
 * Class that represents a function with one variable x
 */
public class Function {

    /**
     * Verify if a function contain only one variable.
     */
    private class FunctionValidator extends Visitor {
        private boolean valid = false;
        private Variable var;

        /**
         * Verify if a function is correct
         * @param f function to verify
         * @return true if the variables are correctly assigned
         */
        public boolean verify(Function f){
            valid = true;
            var = f.getVar();
            f.getExpression().accept(this);
            return valid;
        }

        @Override
        // Can't fail because an integer can also be considered as a real
        public void visit(IntegerNumber n) { }
        @Override
        public void visit(RealNumber n) { }

        @Override
        public void visit(Variable v) {
            // If first variable encountered
            if (var == null)
                var = v;
                // If variable is already set they must be equals
            else if(var != v)
                valid = false;
        }

        @Override
        public void visit(Operation o) {
            for(int i = 1 ; i < o.getArgs().size() ; i++) {
                o.getArgs().get(i).accept(this);
            }
        }

        @Override
        public void visit(MyDate date) { }

        public Variable getVar() { return var; }
    }



    private final Variable var;
    private final Expression e;

    public Function(Expression e) throws IllegalConstruction {
        this.e = e;

        // check if there are only the same var in e
        FunctionValidator v = new FunctionValidator();

        if(!v.verify(this)) throw  new IllegalConstruction();
        this.var = v.getVar();
    }

    public void setValue(MyNumber n) throws BadAssignment{
        if (n == null) throw new BadAssignment();
        var.assignValue(n);
    }

    public void clearValue(){ var.clear(); }

    @Override
    public String toString() { return e.toString(); }

    public String toString(MyNumber value) throws BadAssignment {
        if(value == null) throw new BadAssignment();

        var.assignValue(value);
        String tmp = toString();
        var.clear();
        return tmp;
    }

    public Variable getVar() { return var; }

    public Expression getExpression() { return e; }
}
