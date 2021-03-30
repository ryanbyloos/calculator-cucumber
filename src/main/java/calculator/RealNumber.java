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
        System.out.println(res);
        String[] splited = res.split("\\.");

        if(splited.length == 1 && res.length()>7){
            int exp = 0; // counter of zero number
            for (int i = res.length()-1 ; i>=0 ; i-- ){
                if(res.charAt(i) == '0') exp+=1;
                else break;
            }
            return String.format("%c.%sE%d",res.charAt(0),res.substring(1,res.length()-exp),res.length()-1);
        }else if (splited.length == 2 ){
            int exp = 0; // counter of zero number
            System.out.println("I  L "+(splited[1].length()-1) );
            for (int i = splited[1].length()-1 ; i>=0 ; i-- ){
                if(splited[1].charAt(i) == '0') exp+=1;
                else break;
            }
            System.out.println(exp);
            if(exp == splited[1].length()){ // if decimal part is full of zero return integer part
                return splited[0];
            }else{
                return String.format("%s.%s",splited[0], splited[1].substring(0,splited[1].length()-exp));
            }
        }
        return res;
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
