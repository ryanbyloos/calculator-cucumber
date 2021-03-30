package calculator.exceptions;

public class VariableUnassignedError extends ComputeError{
    @Override
    public String getMessage(){
        return "Variable is not assign";
    }
}
