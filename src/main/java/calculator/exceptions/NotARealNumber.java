package calculator.exceptions;

public class NotARealNumber extends ComputeError{
    String error;
    public NotARealNumber(String err){
        error = err;
    }
    @Override
    public String getMessage(){ return error+" is not an real"; }
}
