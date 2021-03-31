package time;
import calculator.MyNumber;
import calculator.RealNumber;
import visitor.Visitor;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;


public class MyDate extends MyNumber {
    public enum DaysAMonths {
        January(31),
        February(28),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);

        private final int nbr;
        DaysAMonths(int nbr) {
            this.nbr = nbr;

        }
    }


    //ZonedDateTime zonedDateTime;
    LocalDate lDate;

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public MyDate(LocalDate ldate)
    {
        this.lDate =  ldate;
    }
/*
    public MyDate (ZonedDateTime zonedDateTime){
        this.zonedDateTime = zonedDateTime;
    }

    public MyDate(LocalDateTime time)
    {
        LocalDateTime local = time;
        ZoneId zone = ZoneId.of("Europe/Paris");
        this.zonedDateTime = ZonedDateTime.of(time,zone);
    }
    public MyDate(int year,int month,int day)
    {
        LocalDate lDate = LocalDate.of(year,month,day);
        LocalTime lTime = LocalTime.of(0,0,0);
        LocalDateTime lDateTime = LocalDateTime.of(lDate,lTime);
        ZoneId zone = ZoneId.of("Europe/Paris");
        this.zonedDateTime = ZonedDateTime.of(lDateTime,zone);
    }
    public MyDate (String zonedDateTime){

        ZonedDateTime tmp = ZonedDateTime.of(1,2,3,4,5,6,7,ZoneId.of( "Europe/Oslo" ));

        //this.zonedDateTime = zonedDateTime;
    }
*/
    public MyDate plus(MyDate n2){
        int days = n2.lDate.getDayOfYear() + 365*(n2.lDate.getYear()-1);
        return new MyDate( this.lDate.plus(days,ChronoUnit.DAYS));
    }

    public MyDate minus(MyDate n2){
        int days = n2.lDate.getDayOfYear() + 365*(n2.lDate.getYear()-1);
        return new MyDate( this.lDate.minus(days,ChronoUnit.DAYS));
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
    LocalDate l3 = LocalDate.of(0,1,1);


    MyDate aurelien = new MyDate(l1);
    MyDate nicolo = new MyDate(l2);
    MyDate test = new MyDate(l3);
    MyDate plusplus = nicolo.plus(test);
    MyDate res = aurelien.minus(nicolo);
    System.out.println(plusplus.lDate);
}

}
