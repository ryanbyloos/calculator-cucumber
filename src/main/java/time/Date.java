package time;
import calculator.MyNumber;
import visitor.Visitor;

import java.math.BigDecimal;
import java.time.*;
import java.time.ZonedDateTime;


public class Date extends MyNumber {




    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

public static void main (String [] args)

{
    ZoneId zoneId_Norway = ZoneId.of( "Europe/Oslo" );
    ZonedDateTime zdt_Norway = ZonedDateTime.of( 1985 , 1 , 1 , 3 , 2 , 1 , 0 , zoneId_Norway );

    ZoneId zoneId_NewYork = ZoneId.of( "America/New_York" );
    ZonedDateTime zdt_NewYork = zdt_Norway.withZoneSameInstant( zoneId_NewYork );

    ZonedDateTime zdt_Utc = zdt_Norway.withZoneSameInstant( ZoneOffset.UTC );  // Or, next line is similar.
    Instant instant = zdt_Norway.toInstant();  // Instant is always in UTC.

    LocalDate localDate_Norway = zdt_Norway.toLocalDate();
}

}
