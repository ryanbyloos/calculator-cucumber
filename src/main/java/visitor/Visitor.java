package visitor;

import calculator.MyNumber;
import calculator.Operation;
import function.Function;
import function.Variable;

/* Visitor design pattern
 */
public abstract class Visitor {
    public abstract void visit(MyNumber n);
    public abstract void visit(Variable v);
    public abstract void visit(Operation o);
}
