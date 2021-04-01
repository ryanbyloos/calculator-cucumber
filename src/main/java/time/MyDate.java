package time;
import calculator.MyNumber;
import calculator.RealNumber;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
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

    public MyDate plus(MyDate n2) throws IllegalConstruction {
        //LocalDate tmp = this.lDate.plusYears(n2.lDate.getYear());
        //tmp = tmp.plusMonths(n2.lDate.getMonthValue());
        //tmp = tmp.plusDays(n2.lDate.getDayOfMonth() );
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
            throw new IllegalConstruction();
        }
    }

    public LocalDate getlDate()
    {
        return this.lDate;
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
public static void main (String [] args)

{
    ZoneId zoneId_Norway = ZoneId.of( "Europe/Paris" );
    ZonedDateTime zdt_Norway = ZonedDateTime.of( 1985 , 1 , 1 , 3 , 2 , 1 , 0 , zoneId_Norway );
    ZonedDateTime current = ZonedDateTime.now();

    ZoneId zoneId_NewYork = ZoneId.of( "America/New_York" );
    ZonedDateTime zdt_NewYork = zdt_Norway.withZoneSameInstant( zoneId_NewYork );
    System.out.println(zdt_Norway);
    ZonedDateTime zdt_Utc = zdt_Norway.withZoneSameInstant( ZoneOffset.UTC );  // Or, next line is similar.
    Instant instant = zdt_Norway.toInstant();  // Instant is always in UTC.
    LocalDate localDate_Norway = zdt_Norway.toLocalDate();
    System.out.println(zdt_Norway.getHour());
    System.out.println(zdt_Norway.compareTo(ZonedDateTime.now()));




    LocalDate l1 = LocalDate.of(1999,12,6);
    LocalDate l2 = LocalDate.of(1998,3,3);
    LocalDate l4 = LocalDate.of(0,1,1);

    MyDate test1 = new MyDate(0,0,1);
    MyDate d1 = new MyDate(l1);
    MyDate d2 = new MyDate(l2);
    //MyDate d3 = new MyDate(l3);
    MyDate d4 = new MyDate(l4);

    MyDate testouille = new MyDate(0,0,1);
    System.out.println(testouille.lDate);
    MyDate aurelien = new MyDate(l1);
    MyDate nicolo = new MyDate(l2);
    MyDate testouille1 = new MyDate(2,9,3);
    System.out.println(testouille.lDate);
    try {
        MyDate res = nicolo.plus(testouille1);
        System.out.println(res.lDate);
    } catch (IllegalConstruction illegalConstruction) {
        illegalConstruction.printStackTrace();
    }

}

}
