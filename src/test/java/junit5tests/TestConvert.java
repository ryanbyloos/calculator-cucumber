package junit5tests;
import calculator.*;
import Converter.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

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
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new IntegerNumber(Integer.toString(value1)), new IntegerNumber(Integer.toString(value2))));
        RealNumber e = new RealNumber("10.0");
        BigDecimal eval = calc.evalReal(e);
        eval = eval.divide(Unit.Meter.getratio(),Operation.CONST_ROUNDED, RoundingMode.HALF_UP);
        eval = eval.multiply(Unit.Meter.getratio());
        int bool = eval.compareTo(e.getValue());
        assertEquals(0,bool);
    }

}