package calculator.exceptions;

public class ImpossibleConversionError extends ComputeError{
    @Override
    public String getMessage(){
        return "Division By Zero error";
    }
}
