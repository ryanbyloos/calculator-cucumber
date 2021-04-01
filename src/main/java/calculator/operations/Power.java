package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;

public class Power extends Operation{
    public Power(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol="^";
        neutral=1;
    }

    public Power(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol="^";
        neutral=1;
    }

    @Override
    public BigInteger op(BigInteger l, BigInteger r) {
        BigDecimal a = new BigDecimal(l.toString());
        BigDecimal b = new BigDecimal(r.toString());
        return op(a,b).toBigInteger();
    }

    @Override
    public BigDecimal op(BigDecimal l, BigDecimal r) {
        return BigDecimalMath.pow(l, r, MathContext.UNLIMITED);
    }
}
