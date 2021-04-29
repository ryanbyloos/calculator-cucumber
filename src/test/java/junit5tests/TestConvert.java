package junit5tests;
import calculator.*;
import Converter.*;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConvertionArgument;
import calculator.exceptions.NotARealNumber;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestConvert {
    private Calculator calc;
    private RealNumber e;
    private RealNumber test1;
    private RealNumber test2;

    @BeforeEach
    public void setUp(){
        calc = new Calculator(Calculator.Mode.REAL);
        try {
            e = new RealNumber("10");
            test1 = new RealNumber("1");
            test2 = new RealNumber("0");
        }catch (NotARealNumber e){
            fail();
        }
    }

    @Test
    public void test1()
    {
        try{
            calc.convert(test1,Unit.Yard,Unit.Kilometer);
        }
        catch(Exception e )
        {
            fail();
        }
    }

    @Test
    public void testErrorThrown()
    {   Unit unit1 = Unit.Meter;
        Unit unit2 = Unit.Kilogramme;

            assertThrows(IllegalConvertionArgument.class,() -> calc.convert(test1,unit1,unit2));
    }
    @Test
    public void testEquals() throws ComputeError, IllegalConvertionArgument {

            RealNumber e = new RealNumber("10.0");
            BigDecimal eval;
            RealNumber.setPrecision(15);
            for (Unit unit : Unit.values())
            {

                    eval = calc.convert(e,unit,unit);
                    BigDecimal res = eval.subtract(e.getValue()) ;
                    float tested = res.floatValue();
                    Boolean bool = Math.abs(tested )< 0.000001 ;
                    assertTrue(bool);
                }
    }

    @Test
    public void test2() throws ComputeError, IllegalConvertionArgument {
        RealNumber e = new RealNumber("10.0");
        RealNumber.setPrecision(15);
        String res = calc.convertToString(e, Unit.Kilogramme,Unit.Pound);
        String compared = "22.0462 Pound";
        assertEquals(res,compared);
    }


    @Test
    public void testTemperature1()
    {
        String res = calc.convertToString(test2,Temperature.Celsius,Temperature.Kelvin);
        assertEquals("273.15 Kelvin",res);
    }


    @Test
    public void testTemperature2()
    {
        try {
            //Test Celsius to Fahrenheit
            Temperature temp1 = Temperature.Celsius;
            Temperature temp2 = Temperature.Fahrenheit;
            BigDecimal eval = calc.convert(e, temp1, temp2);
            RealNumber compared = new RealNumber("50");
            BigDecimal res = eval.subtract(compared.getValue());
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested) < 0.000001;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void testTemperature3()
    {    //Test Celsius to Kelvin
        try{
            Temperature temp1 = Temperature.Celsius;
            Temperature temp2 = Temperature.Kelvin;
            BigDecimal eval = calc.convert(e,temp1,temp2);
            RealNumber compared = new RealNumber("283.15");
            BigDecimal res = eval.subtract(compared.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.1 ;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }
    @Test
    public void testTemperature4()
    {
        try{
            //Test Fahrenheit to Celsius
            Temperature temp1 = Temperature.Fahrenheit;
            Temperature temp2 = Temperature.Celsius;
            BigDecimal eval = calc.convert(e,temp1,temp2);
            RealNumber compared = new RealNumber("-12.2222");
            BigDecimal res = eval.subtract(compared.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.1 ;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void testTemperature5()
    {
        try{
            //Test Fahrenheit to Kelvin
            Temperature temp1 = Temperature.Fahrenheit;
            Temperature temp2 = Temperature.Kelvin;
            BigDecimal eval = calc.convert(e,temp1,temp2);
            RealNumber compared = new RealNumber("260.928");
            BigDecimal res = eval.subtract(compared.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.1 ;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }
    @Test
    public void testTemperature6()
    {
        try{
            //Test Kelvin to Celsius
            Temperature temp1 = Temperature.Kelvin;
            Temperature temp2 = Temperature.Celsius;
            BigDecimal eval = calc.convert(e,temp1,temp2);
            RealNumber compared = new RealNumber("-263.15");
            BigDecimal res = eval.subtract(compared.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.1 ;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }
    @Test
    public void testTemperature7()
    {
        try{
            //Test Kelvin to Fahrenheit
            Temperature temp1 = Temperature.Kelvin;
            Temperature temp2 = Temperature.Fahrenheit;
            BigDecimal eval = calc.convert(e,temp1,temp2);
            RealNumber compared = new RealNumber("-441.67");
            BigDecimal res = eval.subtract(compared.getValue()) ;
            float tested = res.floatValue();
            Boolean bool = Math.abs(tested )< 0.1 ;
            assertTrue(bool);
        }catch (Exception e){
            fail();
        }
    }
}