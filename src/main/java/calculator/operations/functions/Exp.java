package calculator.operations.functions;

import calculator.Expression;
import calculator.IllegalConstruction;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.util.List;

public class Exp extends BigFunction{
    public Exp(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public BigDecimal op(BigDecimal x) {
        return BigDecimalMath.exp(x, mc);
    }
}
