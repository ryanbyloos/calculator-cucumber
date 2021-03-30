package calculator;

import visitor.Visitor;

import java.math.BigDecimal;

public class BigMath implements Expression {

    public final BigDecimal PI = new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286");
    public final BigDecimal E = new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353");

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public Integer countDepth() {
        return null;
    }

    @Override
    public Integer countOps() {
        return null;
    }

    @Override
    public Integer countNbs() {
        return null;
    }

    public RealNumber log(RealNumber n, int base) {
        return new RealNumber("0");
    }

    public RealNumber sqrt(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber sin(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber cos(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber tan(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber asin(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber acos(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber atan(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber inverse(RealNumber n) {
        return new RealNumber("0");
    }

    public RealNumber random() {
        BigDecimal max = new BigDecimal("1");
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec
                .setScale(2, BigDecimal.ROUND_DOWN);
        return new RealNumber(actualRandomDec.toPlainString());
    }

    public IntegerNumber random(int max){
        int randInt = (int) Math.random();
        return new IntegerNumber(String.format("%d", randInt));
    }
}
