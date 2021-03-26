package calculator;

import visitor.Visitor;

import java.math.BigDecimal;

public class RealNumber extends MyNumber{
    private final BigDecimal value;

    public BigDecimal getValue() { return value; }

    public RealNumber(String s){
        value = new BigDecimal(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString(){
        String res = value.toString();
        if(res.split("\\.").length == 1 && res.length()>7){
            int exp = 0; // counter of zero number
            for (int i = res.length()-1 ; i>0 ; i-- ){
                if(res.charAt(i) == '0') exp+=1;
                else break;
            }
            return String.format("%c.%sE%d",res.charAt(0),res.substring(1,res.length()-exp),res.length()-1);
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
