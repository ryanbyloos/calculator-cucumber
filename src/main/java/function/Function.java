package function;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.operations.Operation;
import calculator.operations.functions.BigFunction;
import visitor.Visitor;

/**
 * Class that represents a function with one variable x
 */
public class Function implements Expression{

    /**
     * Verify if a function contain only one variable.
     */
    private static class FunctionValidator extends Visitor {
        private Variable var;

        /**
         * Verify if a function is correct
         * @param f function to verify
         */
        public void verify(Function f){
            var = f.getVar();
            f.getExpression().accept(this);
        }

        @Override
        public void visit(Variable v) {
            System.out.println("VAR");
            // If first variable encountered
            if (var == null)
                var = v;
        }

        @Override
        public void visit(Operation o) {
            for(int i = 0 ; i < o.getArgs().size() ; i++) {
                Expression e = o.getArgs().get(i);

                System.out.println("CLASS OF CHILD "+e.getClass());
                e.accept(this);

                System.out.println("Var   : "+System.identityHashCode(var));
                System.out.println("Start : "+System.identityHashCode(o.getArgs().get(i)));
                // replace erroneous variable
                if ( var!=null && e instanceof Variable ){
//                    o.setArgs(i,var);
                    o.getArgs().set(i,var);
                }
                System.out.println("End : "+System.identityHashCode(o.getArgs().get(i)));
            }
        }

        @Override
        public void visit(BigFunction bf) {
            for(int i = 0 ; i < bf.getArgs().size() ; i++) {
                Expression e = bf.getArgs().get(i);

                System.out.println("CLASS OF CHILD "+e.getClass());
                e.accept(this);

                System.out.println("Var   : "+System.identityHashCode(var));
                System.out.println("Start : "+System.identityHashCode(bf.getArgs().get(i)));
                // replace erroneous variable
                if ( var!=null && e instanceof Variable ){
//                    o.setArgs(i,var);
                    bf.getArgs().set(i,var);
                }
                System.out.println("End : "+System.identityHashCode(bf.getArgs().get(i)));
            }
        }

        @Override
        public void visit(MyNumber n) { }

        public Variable getVar() { return var; }
    }



    private final Variable var;
    private final Expression e;

    public Function(Expression e){
        this.e = e;
        // check if there are only the same var in e
        FunctionValidator v = new FunctionValidator();
        System.out.println("Start VERIFY");
        v.verify(this);
        System.out.println("END VERIFY");
        this.var = v.getVar();
    }

    public void setValue(Expression n) throws BadAssignment{
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

    @Override
    public void accept(Visitor v) {
        e.accept(v);
    }

    @Override
    public Integer countDepth() {
        return e.countDepth();
    }

    @Override
    public Integer countOps() {
        return e.countOps();
    }

    @Override
    public Integer countNbs() {
        return e.countNbs();
    }
}
