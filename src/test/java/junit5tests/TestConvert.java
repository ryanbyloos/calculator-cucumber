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
    public Calculator calc = new Calculator(Calculator.Mode.REAL);
    RealNumber e = new RealNumber("10");
    public RealNumber test1 = new RealNumber("1");
    public RealNumber test2 = new RealNumber("0");


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
    public void testEquals() throws IllegalConvertionArgument {
        RealNumber e = new RealNumber("10.0");
        BigDecimal eval = calc.evalReal(e);
        Operation.CONST_ROUNDED = 15;
        BigDecimal eval;
        try {
            eval = calc.evalReal(e).getValue();
        }catch (ComputeError ce){
            fail();
            return;
        }
        for (Unit unit : Unit.values())
        {
            try {
                eval = calc.convert(e,unit,unit);
                BigDecimal res = eval.subtract(e.getValue()) ;
                float tested = res.floatValue();
                Boolean bool = Math.abs(tested )< 0.000001 ;
                assertTrue(bool);
            }
            catch (IllegalConvertionArgument argument)
            {
                fail();
            }
        }

    }


    @Test
    public void testTemperature1()
    {
        String res = calc.converttoString(test2,Temperature.Celsius,Temperature.Kelvin);
        assertNotNull(res);
    }


    @Test
    public void testTemperature2()
    {
        //Test Celsius to Fahrenheit
        Temperature temp1 = Temperature.Celsius;
        Temperature temp2 = Temperature.Fahrenheit;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("50");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.000001 ;
        assertTrue(bool);
    }

    @Test
    public void testTemperature3()
    {    //Test Celsius to Kelvin

        Temperature temp1 = Temperature.Celsius;
        Temperature temp2 = Temperature.Kelvin;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("283.15");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.1 ;
        assertTrue(bool);
    }
    @Test
    public void testTemperature4()
    {
        //Test Fahrenheit to Celsius
        Temperature temp1 = Temperature.Fahrenheit;
        Temperature temp2 = Temperature.Celsius;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("-12.2222");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.1 ;
        assertTrue(bool);
    }

    @Test
    public void testTemperature5()
    {
        //Test Fahrenheit to Kelvin
        Temperature temp1 = Temperature.Fahrenheit;
        Temperature temp2 = Temperature.Kelvin;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("260.928");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.1 ;
        assertTrue(bool);
    }
    @Test
    public void testTemperature6()
    {
        //Test Kelvin to Celsius
        Temperature temp1 = Temperature.Kelvin;
        Temperature temp2 = Temperature.Celsius;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("-263.15");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.1 ;
        assertTrue(bool);
    }
    @Test
    public void testTemperature7()
    {
        //Test Kelvin to Fahrenheit
        Temperature temp1 = Temperature.Kelvin;
        Temperature temp2 = Temperature.Fahrenheit;
        BigDecimal eval = calc.convert(e,temp1,temp2);
        RealNumber compared = new RealNumber("-441.67");
        BigDecimal res = eval.subtract(compared.getValue()) ;
        float tested = res.floatValue();
        Boolean bool = Math.abs(tested )< 0.1 ;
        assertTrue(bool);
    }

}