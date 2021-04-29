package visitor;

import calculator.MyNumber;
import calculator.exceptions.ComputeError;

/**
 * Visitor who goes through an expression to evaluate it.
 */
public abstract class Evaluator extends Visitor{
    private MyNumber computedValue;
    private ComputeError exception; // if the expression throw an exception

    /**
     * Return result of evaluation or throw an exception if an error has been detected
     * @return value of evaluation
     * @throws ComputeError
     */
    public MyNumber getResult() throws ComputeError {
        if ( exception != null) throw  exception;
        return computedValue;
    }

    public void setComputedValue(MyNumber computedValue) { this.computedValue = computedValue; }
    public void setException(ComputeError exception) { this.exception = exception; }
}
