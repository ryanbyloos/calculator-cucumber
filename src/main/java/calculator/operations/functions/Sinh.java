package calculator.operations.functions;

import calculator.Expression;
import calculator.exceptions.IllegalConstruction;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.util.List;

public class Sinh extends BigFunction{
    public Sinh(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public BigDecimal op(BigDecimal x) {
        return BigDecimalMath.sinh(x, mc);
    }
}
