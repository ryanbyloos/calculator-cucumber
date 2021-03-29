package visitor;

import calculator.IntegerNumber;
import calculator.MyNumber;
import calculator.Operation;
import calculator.RealNumber;
import function.Variable;
import time.Time;

/* Visitor design pattern
 */
public abstract class Visitor {
    public abstract void visit(IntegerNumber n);
    public abstract void visit(RealNumber n);
    public abstract void visit(Variable v);
    public abstract void visit(Operation o);
    public abstract void visit(Time time);
}
