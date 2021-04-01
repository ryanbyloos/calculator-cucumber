package calculator;

import calculator.exceptions.DivisionByZeroError;
import calculator.exceptions.NotARealNumber;
import calculator.exceptions.NotAnIntegerNumber;
import calculator.operations.Operation;
import visitor.Visitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RealNumber extends MyNumber{
    private final BigDecimal value;

    public BigDecimal getValue() { return value; }

    public RealNumber(String s) throws NotARealNumber {
        try {
            value = new BigDecimal(s);
        }catch (Exception e){
            throw new NotARealNumber(s);
        }
    }

    public RealNumber(BigDecimal b){
        value = b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public RealNumber divide(RealNumber n2) throws DivisionByZeroError {
        try {
            return new RealNumber(value.divide(n2.getValue(), Operation.CONST_ROUNDED, RoundingMode.HALF_UP));
        }catch (ArithmeticException e){
            throw new DivisionByZeroError();
        }
    }

    public RealNumber plus(RealNumber n2){
        return new RealNumber(value.add(n2.getValue()));
    }

    public RealNumber minus(RealNumber n2){
        return new RealNumber(value.add(n2.getValue().negate()));
    }
    public RealNumber times(RealNumber n2){
        return new RealNumber(value.multiply(n2.getValue()));
    }


    public IntegerNumber toIntegerNumber() throws NotAnIntegerNumber{
        try {
            return new IntegerNumber(value.toBigIntegerExact());
        }catch (Exception e){
            throw new NotAnIntegerNumber(value.toString());
        }
    }

    @Override
    public String toString(){
        int MAX_SIZE = 7;

        String res = value.toPlainString();
        String[] splitNumber = res.split("\\.");

        if(splitNumber.length == 1 && res.length()>MAX_SIZE){ // if it has only integer part and has more than 7 digit
            // use Exponent notation
            int exp = countZeroesAtEndOfString(res);
            return String.format("%c.%sE%d",res.charAt(0),res.substring(1,res.length()-exp),res.length()-1);
        }else if (splitNumber.length == 2 ){ // if has a decimal part

            // if too presice
            if(splitNumber[0].length() == 1 && splitNumber[0].charAt(0) == '0'){
                return value.toString();
            }

            int exp = countZeroesAtEndOfString(splitNumber[1]);
            if(exp == splitNumber[1].length()){ // if it decimal part is full of zero return integer part
                return splitNumber[0];
            }else{ // else remove useless zeroes
                return String.format("%s.%s",splitNumber[0], splitNumber[1].substring(0,splitNumber[1].length()-exp));
            }
        }
        return res;
    }

    /**
     * Return the number of consecutive zeroes at the end of the string
     * @param s String to count zeroes at the end
     * @return number of consecutive zeroes at the end
     */
    private int countZeroesAtEndOfString(String s){
        int exp = 0; // counter of zero number
        for (int i = s.length()-1 ; i>=0 ; i-- ){
            if(s.charAt(i) == '0') exp+=1;
            else return exp;
        }
        return exp;
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
        if (o instanceof RealNumber) {
            return this.value.compareTo(((RealNumber)o).value) == 0;
            // .compareTo is needed for BigDecimal
        }else if(o instanceof  IntegerNumber){
            RealNumber n = ((IntegerNumber) o).toRealNumber();
            return this.value.compareTo(n.value) == 0;
        }
        return false;
    }
}
