package calculator;

import calculator.exceptions.ComputeError;
import calculator.exceptions.DivisionByZeroError;
import calculator.exceptions.NotAnIntegerNumber;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IntegerNumber extends MyNumber{
    private final BigInteger value;

    public BigInteger getValue() { return value; }

    public IntegerNumber(String s) throws NotAnIntegerNumber {
        try {
            value = new BigInteger(s);
        }catch (Exception e){
            throw new NotAnIntegerNumber(s);
        }
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
    public String toString(){
        int MAX_SIZE = 7;
        String res = value.toString();
        if (res.length() <= MAX_SIZE) return res;

        int exp = 0;
        for (int i = res.length()-1 ; i > 0 ; i--){
            if(res.charAt(i) == '0') exp+=1;
            else break;
        }
        return String.format("%sE%d",res.substring(0,res.length()-exp),exp);
    }

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
        if (o instanceof IntegerNumber) {
            return this.value.compareTo(((IntegerNumber)o).value) == 0;
            // .compareTo is needed for BigInteger
        }else if (o instanceof RealNumber){
            try{
                IntegerNumber n = ((RealNumber) o).toIntegerNumber();
                return this.value.compareTo(n.value) == 0;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    @Override
    public MyNumber convertTo(Type t) {
        switch (t){
            case REAL:
                return toRealNumber();
            case INTEGER:
                return this;
        }
        return null;
    }
}
