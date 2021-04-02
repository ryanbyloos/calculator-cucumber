package calculator.exceptions;

public class NotARealNumber extends ComputeError{
    public NotARealNumber(String err){ super(err); }
    @Override
    public String getMessage(){ return getError()+" is not an real"; }
}
