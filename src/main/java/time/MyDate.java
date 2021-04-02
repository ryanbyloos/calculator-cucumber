package time;
import calculator.IntegerNumber;
import calculator.MyNumber;
import calculator.exceptions.TemporalException;
import visitor.Visitor;

import java.time.*;


public class MyDate extends MyNumber {

    LocalDate lDate;
    long year;
    long months;
    long days;
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public MyDate(LocalDate ldate)
    {
        this.lDate =  ldate;
        this.year = 0;
        this.months = 0;
        this.days = 0;
    }
    public MyDate(long y, long m , long d)
    {
        this.lDate = null;
        this.year = y;
        this.months = m;
        this.days = d;
    }
    public MyDate(String string)
    {
        String [] tab = string.split("-");
        this.lDate = null;
        this.year   = 0;
        this.months = 0;
        this.days   = 0;
    }

    public LocalDate getlDate()
    {
        return this.lDate;
    }

    public MyDate plus(MyDate n2) throws TemporalException {

        if (n2.lDate == null)
        {
            LocalDate tmp = this.lDate;
            tmp = tmp.plusYears(n2.year);
            tmp = tmp.plusMonths(n2.months);
            tmp = tmp.plusDays(n2.days);
            return new MyDate( tmp);
        }
        else
        {
            throw new TemporalException();
        }
    }

    public MyDate minus(MyDate n2){
        if (n2.lDate == null)
        {
            LocalDate tmp = this.lDate;
            tmp = tmp.minusYears(n2.year);
            tmp = tmp.minusMonths(n2.months);
            tmp = tmp.minusDays(n2.days);
            return new MyDate( tmp);
        }
        else
        {
            int i = this.lDate.compareTo(n2.lDate);
            LocalDate tmp;
            if (i < 0)
            {
                tmp = n2.lDate.minusYears(this.lDate.getYear());
                tmp = tmp.minusMonths(this.lDate.getMonthValue());
                tmp = tmp.minusDays(this.lDate.getDayOfMonth());
            }
            else {
                tmp = this.lDate.minusYears(n2.lDate.getYear());
                tmp = tmp.minusMonths(n2.lDate.getMonthValue());
                tmp = tmp.minusDays(n2.lDate.getDayOfMonth());
            }
            return new MyDate(tmp);
        }
}
    public String toString()
    {
        if(lDate != null)
        {
            int y = this.lDate.getYear();
            int m = this.lDate.getMonthValue();
            int d = this.lDate.getDayOfMonth();
            return( ""+y+"-"+m+"-"+d);
        }
        long y = this.year;
        long m = this.months;
        long d = this.days;
        return( ""+y+"-"+m+"-"+d);
    }
    public MyDate times(MyDate n2) throws UselessComputation {
        throw new UselessComputation();
    }
    public MyDate divide(MyDate n2) throws UselessComputation {
        throw new UselessComputation();
    }



    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }
        // If the object is of another type then return false
        if (o instanceof MyDate) {
            if (this.lDate == null ||((MyDate)o).lDate== null )
            {
                return false;
            }
            return this.lDate.compareTo(((MyDate)o).lDate) == 0;
            // .compareTo is needed for BigDecimal
        }
        return false;
    }
}
