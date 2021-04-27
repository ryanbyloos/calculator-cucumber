package visitor;

import calculator.MyNumber;
import calculator.exceptions.ComputeError;

public abstract class Evaluator extends Visitor{
    private MyNumber computedValue;
    private ComputeError exception; // if the expression throw an exception
    public MyNumber getResult() throws ComputeError {
        if ( exception != null) throw  exception;
        return computedValue;
    }

    public void setComputedValue(MyNumber computedValue) { this.computedValue = computedValue; }
    public void setException(ComputeError exception) { this.exception = exception; }
}
