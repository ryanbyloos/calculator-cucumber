package Converter;

import java.math.BigDecimal;

public enum Temperature {

    /////////////////////////////Temperature///////////////////////////////////////////////
    Celsius("Temperature", "Degree Celius", new BigDecimal("1"), new BigDecimal("0")),
    Fahrenheit("Temperature", "Degree Fahrenheit",new BigDecimal("1.8"), new BigDecimal("32")),
    Kelvin ("Temperature", "Degree Kelvin",  new BigDecimal("1"),new BigDecimal("273.15"));



    private final String type;
    private final String name;
    private final BigDecimal ratio;
    private final BigDecimal constant;

    private Temperature (String type, String name, BigDecimal ratio,BigDecimal constant) {
        this.type = type;
        this.name = name;
        this.ratio = ratio;
        this.constant = constant;
    }
    public String getFullName() {
        return name;
    }

    public BigDecimal getRatio(){
        return ratio;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getConstant()
    {
        return this.constant;
    }

}
