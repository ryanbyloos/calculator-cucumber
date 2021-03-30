package visitor;

import calculator.IntegerNumber;
import calculator.operations.Operation;
import calculator.RealNumber;
import function.Variable;

/* Visitor design pattern
 */
public abstract class Visitor {
    public abstract void visit(IntegerNumber n);
    public abstract void visit(RealNumber n);
    public abstract void visit(Variable v);
    public abstract void visit(Operation o);
}
