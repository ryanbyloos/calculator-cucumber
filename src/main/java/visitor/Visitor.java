package visitor;

import calculator.MyNumber;
import calculator.operations.Operation;
import calculator.operations.functions.BigFunction;
import function.Variable;

/* Visitor design pattern
 */
public abstract class Visitor {
    public abstract void visit(Variable v);
    public abstract void visit(Operation o);
    public abstract void visit(BigFunction f);
    public abstract void visit(MyNumber n);
}
