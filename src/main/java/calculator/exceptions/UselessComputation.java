package calculator.exceptions;

public class UselessComputation extends ComputeError{
    @Override
    public String getMessage(){
        return "Cannot multiply or divide Date";
    }
}
