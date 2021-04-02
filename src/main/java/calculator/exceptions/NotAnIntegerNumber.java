package calculator.exceptions;

public class NotAnIntegerNumber extends ComputeError{

    public NotAnIntegerNumber(String err) { super(err); }

    @Override
    public String getMessage(){ return getError()+ " is not an integer"; }
}
