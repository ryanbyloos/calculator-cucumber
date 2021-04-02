package calculator.exceptions;

public class ComputeError extends IllegalConstruction{
    String error;
    public ComputeError(){
        setError("Undefined");
    }
    public ComputeError(String err){
        setError(err);
    }
    @Override
    public String getMessage(){ return getError(); }

    public void setError(String error) { this.error = error; }
    public String getError() { return error; }
}
