package time;
import calculator.*;
import visitor.Visitor;

public class Time extends MyNumber {
    int year;
    int month;
    int day;

    public Time()
    {

    }



    public void accept(Visitor v) {
        v.visit(this);
    }
}
