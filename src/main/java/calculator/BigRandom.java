package calculator;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigRandom {

    public BigDecimal generate() {
        BigDecimal max = new BigDecimal("1");
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        return randFromDouble.divide(max, BigDecimal.ROUND_DOWN);
    }

    public BigInteger generate(int i) {
        int randInt = (int) (Math.random()*i);
        return new BigInteger(String.format("%d", randInt));
    }
}
