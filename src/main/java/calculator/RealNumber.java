package calculator;

import visitor.Visitor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RealNumber extends MyNumber{
    private final BigDecimal value;

    public BigDecimal getValue() { return value; }

    public RealNumber(String s){
        value = new BigDecimal(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public BigInteger toBigInteger() throws ArithmeticException{
        return value.toBigIntegerExact();
    }

    @Override
    public String toString(){
        String res = value.toString();
        String[] splitNumber = res.split("\\.");

        if(splitNumber.length == 1 && res.length()>7){ // if it has only integer part and has more than 7 digit
            // use Exponent notation
            int exp = countZeroesAtEndOfString(res);
            return String.format("%c.%sE%d",res.charAt(0),res.substring(1,res.length()-exp),res.length()-1);
        }else if (splitNumber.length == 2 ){ // if has a decimal part
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
        if (!(o instanceof RealNumber)) {
            return false;
        }
        return this.value.compareTo(((RealNumber)o).value) == 0;
        // .compareTo is needed for BigDecimal
    }
}
