package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;

import java.math.BigDecimal;
import java.util.List;

public class Inverse extends BigFunction{
    public Inverse(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public BigDecimal op(BigDecimal x) {
        return BigDecimal.ONE.divide(x, mc);
    }
}
