package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        BigInteger res = BigInteger.ONE;
        while(r.signum() > 0){
            if(r.testBit(0))
                res = res.multiply(l);
            l = l.multiply(l);
            r = r.shiftRight(1);
        }
        return res;
    }

    @Override
    public BigDecimal op(BigDecimal l, BigDecimal r) {
        return null;
    }
}
