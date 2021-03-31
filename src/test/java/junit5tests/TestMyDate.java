package junit5tests;

import calculator.Calculator;
import calculator.RealNumber;
import calculator.exceptions.NotARealNumber;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import time.MyDate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

public class TestMyDate {
    private Calculator calc;
    private RealNumber e;
    private MyDate test1;
    private RealNumber test2;
    private LocalDate l1;
    private LocalDate l2;
    private LocalDate l3;
    private LocalDate l4;

    @BeforeEach
    public void setUp(){
        calc = new Calculator(Calculator.Mode.REAL);
        LocalDate l1 = LocalDate.of(1999,12,6);
        LocalDate l2 = LocalDate.of(1998,3,3);
        LocalDate l3 = LocalDate.of(27,48,999);
        LocalDate l4 = LocalDate.of(0,1,1);
        MyDate test1 = new MyDate(0,0,1);
        MyDate d1 = new MyDate(l1);
        MyDate d2 = new MyDate(l2);
        MyDate d3 = new MyDate(l3);
        MyDate d4 = new MyDate(l4);
    }





}
