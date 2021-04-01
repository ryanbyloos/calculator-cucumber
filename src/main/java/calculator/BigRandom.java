package calculator;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigRandom {

    public BigDecimal generate() {
        BigDecimal max = new BigDecimal("1");
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec
                .setScale(2, BigDecimal.ROUND_DOWN);
        return actualRandomDec;
    }

    public BigInteger generate(int i) {
        int randInt = (int) Math.random();
        return new BigInteger(String.format("%d", randInt));
    }
}
