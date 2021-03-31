package calculator;

import calculator.exceptions.DivisionByZeroError;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IntegerNumber extends MyNumber{
    private final BigInteger value;

    public BigInteger getValue() { return value; }

    public IntegerNumber(String s){
        value = new BigInteger(s);
    }
    public IntegerNumber(BigInteger b){
        value = b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public RealNumber toRealNumber() throws ArithmeticException{
        return new RealNumber(new BigDecimal(value));
    }

    public IntegerNumber divide(IntegerNumber n2) throws DivisionByZeroError {
        try {
            return new IntegerNumber(value.divide(n2.getValue()));
        }catch (ArithmeticException e){
            throw new DivisionByZeroError();
        }
    }
    public IntegerNumber plus(IntegerNumber n2){
        return new IntegerNumber(value.add(n2.getValue()));
    }

    public IntegerNumber minus(IntegerNumber n2){
        return new IntegerNumber(value.add(n2.getValue().negate()));
    }
    public IntegerNumber times(IntegerNumber n2){
        return new IntegerNumber(value.multiply(n2.getValue()));
    }


    @Override
    public String toString(){ return value.toString(); }

    //Two MyNumber expressions are equal if the values they contain are equal
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof IntegerNumber)) {
            return false;
        }
        return this.value.compareTo(((IntegerNumber)o).value) == 0;
        // .compareTo is needed for BigInteger
    }
}
