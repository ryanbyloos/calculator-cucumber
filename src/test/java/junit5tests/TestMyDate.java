package junit5tests;

import calculator.Calculator;
import calculator.Expression;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.TemporalException;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import time.MyDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyDate {
    private Calculator calc;
    private RealNumber e;
    private MyDate test1;
    private RealNumber test2;
    private LocalDate l1;
    private LocalDate l2;
    private MyDate d1;
    private MyDate d2;
    private MyDate d3;
    private MyDate d4;
    private MyDate d5;
    private MyDate d6;
    private MyDate d7;

    @BeforeEach
    public void setUp(){
        calc = new Calculator(Calculator.Mode.REAL);
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

    @Test
    public void test9() throws IllegalConstruction {
        List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1998,3,3)), new MyDate(0,1,1)));
        Plus op = new Plus(params2);
        assertEquals("( 3-3-1998 + 1-1-0 )", op.toString());
    }
    @Test
    public void test9_1() throws IllegalConstruction {
        List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1998,3,3)), new MyDate(LocalDate.of(1998,3,3))));
        Minus op1 = new Minus(params2);
        assertEquals("( 3-3-1998 - 3-3-1998 )", op1.toString());
    }
    @Test
    public void test10()  {
        l2 = LocalDate.of(1998,3,3);
        LocalDate l3 = LocalDate.of(1998, 3, 3);
        MyDate m1 = new MyDate(l2);
        MyDate m2 = new MyDate(l3);
        assertEquals(m1,m2);
    }

    @Test
    public void test11()  {
        l2 = LocalDate.of(1998,3,3);
        MyDate m1 = new MyDate(l2);
        MyDate m2 = new MyDate(1,1,1);
        assertNotEquals(m1,m2);
        assertNotEquals(m2,m1);
    }
    @Test
    public void test12()  {
        try
        {
            List<Expression> params2 =
                    new ArrayList<>(Arrays.asList(new MyDate(LocalDate.of(1999,12,6)), new MyDate(1998,3,3)));
            Expression exp = new Times(params2);
            assertThrows(ComputeError.class,() -> calc.evalDate(exp) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }
}
