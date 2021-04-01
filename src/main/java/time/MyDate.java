package time;
import calculator.MyNumber;
import calculator.RealNumber;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.TemporalException;
import calculator.exceptions.UselessComputation;
import visitor.Visitor;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;


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
    public MyDate times(MyDate n2){
        return null;
    }
    public MyDate divide(MyDate n2){
        return null;
    }


}
