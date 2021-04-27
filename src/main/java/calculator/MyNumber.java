package calculator;

import calculator.exceptions.ComputeError;

public abstract class MyNumber implements Expression {

    public enum Type {INTEGER,REAL};

    public abstract MyNumber convertTo(Type t) throws ComputeError;

    public Integer countDepth() {
        return 0;
    }

    public Integer countOps() {
        return 0;
    }

    public Integer countNbs() {
        return 1;
    }
}
