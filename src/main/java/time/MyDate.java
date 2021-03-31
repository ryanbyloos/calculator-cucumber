package time;
import calculator.MyNumber;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import visitor.Visitor;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;


public class MyDate extends MyNumber {

    LocalDate lDate;

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public MyDate(LocalDate ldate)
    {
        this.lDate =  ldate;
    }

    public MyDate(long y, long m , long d)
    {

        LocalDate tmp =  LocalDate.of(0,1,1);
        tmp=tmp.plus(y,ChronoUnit.YEARS);
        tmp=tmp.plus(m-1,ChronoUnit.MONTHS);
        tmp=tmp.plus(d-1,ChronoUnit.DAYS);
        this.lDate = tmp;
    }

    public MyDate plus(MyDate n2){
        LocalDate tmp = this.lDate.plusYears(n2.lDate.getYear());
        tmp = tmp.plusMonths(n2.lDate.getMonthValue());
        tmp = tmp.plusDays(n2.lDate.getDayOfMonth() );
        return new MyDate( tmp);
    }

    public MyDate Minus(MyDate n2){
        LocalDate tmp = this.lDate.minusYears(n2.lDate.getYear());
        tmp = tmp.minusMonths(n2.lDate.getMonthValue());
        tmp = tmp.minusDays(n2.lDate.getDayOfMonth());
        return new MyDate( tmp);
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
   // LocalDate l3 = LocalDate.of(27,48,999);
  LocalDate L4 = LocalDate.of(0,1,1);
    //L4.plus(27,ChronoUnit.YEARS);
   // L4.plus(48,ChronoUnit.MONTHS);
  //  L4.plus(999,ChronoUnit.DAYS);

    MyDate testouille = new MyDate(0,0,1);
    System.out.println("CACA");
    System.out.println(testouille.lDate);
    System.out.println("CACA");


    MyDate aurelien = new MyDate(l1);
    MyDate nicolo = new MyDate(l2);
    MyDate testouille1 = new MyDate(2,9,3);
   // nicolo.plus(testouille);
   // MyDate test = new MyDate(l3);
    System.out.println(testouille.lDate);
    MyDate res = aurelien.Minus(testouille);
 //   MyDate caca = nicolo.plus(test);
   System.out.println(res.lDate);

}

}
