package junit5tests;
import calculator.*;
import Converter.*;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConvertionArgument;
import calculator.operations.Operation;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class TestConvert {
    public Calculator calc = new Calculator(Calculator.Mode.REAL, 10);

    public RealNumber test1 = new RealNumber("1");
    public final int value1 = 5;
    public final int value2 = 10;
    @Test
    public void test1()
    {
        try{
            calc.convert(test1,Unit.Yard,Unit.Kilometer);
        }
        catch(IllegalConvertionArgument e )
        {
            fail();
        }
    }

    @Test
    public void testErrorThrown()
    {   Unit unit1 = Unit.Meter;
        Unit unit2 = Unit.Kilogramme;

            assertThrows(IllegalConvertionArgument.class,() -> {calc.convert(test1,unit1,unit2);
            });
    }
    @Test
    public void testEquals() {
        RealNumber e = new RealNumber("10.0");
        BigDecimal eval;
        try {
            eval = calc.evalReal(e).getValue();
        }catch (ComputeError ce){
            fail();
            return;
        }
        for (Unit unit : Unit.values())
        {

            eval = eval.divide(unit.getratio(), Operation.CONST_ROUNDED, RoundingMode.HALF_DOWN);
            eval = eval.multiply(unit.getratio()).setScale(Operation.CONST_ROUNDED,RoundingMode.HALF_UP);
            BigDecimal res = eval.subtract(e.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.000001 ;
//            System.out.println(unit );
//            System.out.println(eval + " Eval");
//            System.out.println(e.getValue() + " base");
            assertTrue(bool);
        }

    }

}