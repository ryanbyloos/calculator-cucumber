package calculator.exceptions;

public class DivisionByZeroError extends  ComputeError{
    @Override
    public String getMessage(){
        return "Division By Zero Error";
    }
}
