package time;
import calculator.Calculator;
import calculator.MyNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.TemporalException;
import calculator.exceptions.UselessComputation;
import visitor.Visitor;

import java.time.*;


public class MyDate extends MyNumber {
    /**
     * Make from either
     * a localDate
     * or 3 long for years, months and day
     */
    LocalDate lDate;
    long year;
    long months;
    long days;
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /** Creates an MyDate Object with the specified LocalDate.
     * @param ldate the LocalDate from which the MyDate is created.
     */
    public MyDate(LocalDate ldate)
    {
        this.lDate =  ldate;
        this.year = 0;
        this.months = 0;
        this.days = 0;
    }
    /** Creates an MyDate Object for addition or substraction .
     * @param y the long value for the years.
     * @param m the long value for the months.
     * @param d the long value for the days.
     */
    public MyDate(long y, long m , long d)
    {
        this.lDate = null;
        this.year = y;
        this.months = m;
        this.days = d;
    }
    /** Creates an MyDate Object for addition or substraction .
     * @param string the String object used to generatate the MyDate object for the addition or substraction.
     */
    public MyDate(String string)
    {
        //day-month-year
        String [] tab = string.split("-");
        this.lDate = null;
        this.year   = Long.parseLong(tab[2]);
        this.months = Long.parseLong(tab[1]);
        this.days   = Long.parseLong(tab[0]);
    }

    public LocalDate getlDate()
    {
        return this.lDate;
    }

    /** Return an MyDate object resulting from an addition between 2 myDate object .
     * @param n2 The myDate object used for the addition.
     * @throws TemporalException if n2 localDate is not null, since adding 2 dates doesn't make sense compared to adding a certain number of days or months or years
     */
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

    /** Return an MyDate object resulting from an Substraction between 2 myDate object. It also does the time elapsed between 2 MyDate Objects
     * @param n2 The myDate object used for the substraction.
     *
     */
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
            return( ""+d+"-"+m+"-"+y);
        }
        long y = this.year;
        long m = this.months;
        long d = this.days;
        return( ""+d+"-"+m+"-"+y);
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
    @Override
    public MyNumber convertTo(Calculator.Mode m) throws ComputeError {
        throw new ComputeError("Cannot convert Date");
    }
}
