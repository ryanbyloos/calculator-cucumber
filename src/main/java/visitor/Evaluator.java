package visitor;

import calculator.MyNumber;
import calculator.exceptions.ComputeError;

public abstract class Evaluator extends Visitor{
    private MyNumber computedValue;
    private ComputeError exception; // if the expression throw an exception
    public MyNumber getResult() { return getComputedValue(); }

    public ComputeError getException() { return exception; }
    public MyNumber getComputedValue() { return computedValue; }

    public void setComputedValue(MyNumber computedValue) { this.computedValue = computedValue; }
    public void setException(ComputeError exception) { this.exception = exception; }
}
