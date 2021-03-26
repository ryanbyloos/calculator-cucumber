package calculator;

import visitor.Visitor;

import java.math.BigInteger;

public class IntegerNumber extends MyNumber{
    private final BigInteger value;

    public BigInteger getValue() { return value; }

    public IntegerNumber(String s){
        value = new BigInteger(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
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
