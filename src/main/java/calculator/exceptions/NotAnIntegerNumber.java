package calculator.exceptions;

public class NotAnIntegerNumber extends ComputeError{
    String error;
    public NotAnIntegerNumber(String err){
        error = err;
    }
    @Override
    public String getMessage(){ return error+ " is not an integer"; }
}
