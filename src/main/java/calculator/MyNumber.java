package calculator;

import calculator.exceptions.ComputeError;

public abstract class MyNumber implements Expression {

    public abstract MyNumber convertTo(Calculator.Mode m) throws ComputeError;

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
