package junit5tests;

import calculator.Calculator;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.NotARealNumber;
import calculator.exceptions.TemporalException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import time.MyDate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestMyDate {
    private Calculator calc;
    private RealNumber e;
    private MyDate test1;
    private RealNumber test2;
    private LocalDate l1;
    private LocalDate l2;
    private LocalDate l3;
    private MyDate d1;
    private MyDate d2;
    private MyDate d3;
    private MyDate d4;
    private MyDate d5;
    private MyDate d6;
    private MyDate d7;

    @BeforeEach
    public void setUp(){
        LocalDate l1 = LocalDate.of(1999,12,6);
        LocalDate l2 = LocalDate.of(1998,3,3);
        LocalDate l3 = LocalDate.of(0,1,1);
        MyDate d1 = new MyDate(l1);
        MyDate d2 = new MyDate(l2);
        MyDate d3 = new MyDate(l3);
        MyDate d4 = new MyDate(0,0,1);
        MyDate d5 = new MyDate(0,1,0);
        MyDate d6 = new MyDate(1,0,0);
        MyDate d7 = new MyDate(1,1,1);
    }

@Test
    public void test1() throws TemporalException {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d4 = new MyDate(0,0,1);
        d2 = d2.plus(d4);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1998,3,4);
        assertEquals(0,tmp.compareTo(compared));
    }

    @Test
    public void test2() throws TemporalException {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d5 = new MyDate(0,1,0);
        d2 = d2.plus(d5);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1998,4,3);
        assertEquals(0,tmp.compareTo(compared));
    }

    @Test
    public void test3() throws TemporalException {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d6 = new MyDate(1,0,0);
        d2 = d2.plus(d6);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1999,3,3);
        assertEquals(0,tmp.compareTo(compared));
    }
    @Test
    public void test4() throws TemporalException {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d7 = new MyDate(1,1,1);
        d2 = d2.plus(d7);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1999,4,4);
        assertEquals(0,tmp.compareTo(compared));
    }

    @Test
    public void test5()  {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d4 = new MyDate(0,0,1);
        d2 = d2.minus(d4);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1998,3,2);
        assertEquals(0,tmp.compareTo(compared));
    }

    @Test
    public void test6()  {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d5 = new MyDate(0,1,0);
        d2 = d2.minus(d5);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1998,2,3);
        assertEquals(0,tmp.compareTo(compared));
    }

    @Test
    public void test7()  {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d6 = new MyDate(1,0,0);
        d2 = d2.minus(d6);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1997,3,3);
        assertEquals(0,tmp.compareTo(compared));
    }
    @Test
    public void test8()   {
        l2 = LocalDate.of(1998,3,3);
        d2 = new MyDate(l2);
        d7 = new MyDate(1,1,1);
        d2 = d2.minus(d7);
        LocalDate tmp = d2.getlDate();
        LocalDate compared = LocalDate.of(1997,2,2);
        assertEquals(0,tmp.compareTo(compared));
    }

}
