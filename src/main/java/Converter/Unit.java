package Converter;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum Unit {

    Meter      ("Length","Meter",new BigDecimal("1") ),
    Kilometer  ("Length","Kilometer", new BigDecimal("0.001") ),
    Yard       ("Length","Yard",new BigDecimal("1.09361")),



    Kilogramme("Mass","Kilogramme",new BigDecimal("0.001") );

    private final String type;
    private final String name;
    private final BigDecimal value;

    private Unit(String type, String name, BigDecimal value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getFullName() {
        return name;
    }

    public BigDecimal getratio(){
        return value;
    }

    public String getType() {
        return type;
    }
}