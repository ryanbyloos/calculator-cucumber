package Converter;

import java.math.BigDecimal;

public enum Unit {

    ////////////////////////LENGTH/////////////////////////////////////////////
    Meter       ("Length","Meter",new BigDecimal("1") ),
    Kilometer   ("Length","Kilometer", new BigDecimal("0.001") ),
    Centimeter  ("Length","Centimeter",new BigDecimal("100")),
    Milimeter   ("Length","Milimeter",new BigDecimal("1000")),
    Yard        ("Length","Yard",new BigDecimal("1.09361")),
    Inch        ("Length","Inch",new BigDecimal("39.3701")),
    Feet        ("Length","Feet",new BigDecimal("3.28084")),



    /////////////////////////////Volume///////////////////////////////////////////////
    Liter ("Volume", "Liter" , new BigDecimal("1")),
    Deciliter ("Volume","Deciliter",new BigDecimal("10")),
    Centiliter ("Volume","Centiliter",new BigDecimal("100")),
    Mililiter ("Volume","Mililiter",new BigDecimal("1000")),
    Decaliter("Volume","Decaliter",new BigDecimal("0.1")),
    Hectoliter("Volume","Hectoliter", new BigDecimal("0.01")),
    Kiloliter("Volume","Kiloliter",new BigDecimal("0.001") ),
    Gallon ("Volume","Gallon", new BigDecimal("0.264172")),
    FluidOunce ("Volume","Ounce",new BigDecimal("33.814") ),


    /////////////////////////////Mass///////////////////////////////////////////////
    Gramme ("Mass","Gramme",new BigDecimal("1")),
    Decigramme ("Mass","Décigramme",new BigDecimal("10")),
    Centigramme ("Mass","Centigramme",new BigDecimal("100")),
    Miligramme ("Mass","Miligramme",new BigDecimal("1000")),
    Decagramme("Mass","Decagramme",new BigDecimal("0.1")),
    Hectogramme("Mass","Hectogramme", new BigDecimal("0.01")),
    Kilogramme("Mass","Kilogramme",new BigDecimal("0.001") ),
    Pound ("Mass","Pound",new BigDecimal("0.00220462")),
    Ounce ("Mass","Ounce",new BigDecimal("0.035274")),
    Tonne ("Mass","Tonne", new BigDecimal("0.000001")),
    ImperialTonne  ("Mass","ImperialTonne", new BigDecimal("0.00000098421")),


    /////////////////////////////Areas///////////////////////////////////////////////
    SquareMeter ("Area", "SquareMeter" , new BigDecimal("1")),
    SquareKilometer ("Area","SquareKilometer",new BigDecimal("0.000001")),
    SquareMiles ("Area","SquareMiles",new BigDecimal("0.0000003861")),
    SquareYard ("Area","SquareYard",new BigDecimal("1.19599")),
    SquareFoot("Area","SquareFoot",new BigDecimal("10.7639")),
    SquareInch("Area","SquareInch", new BigDecimal("1550")),
    Hectare("Area","Hectare",new BigDecimal("0.0001") ),
    Acre ("Area","Acre",new BigDecimal("0.000247105")),

    /////////////////////////////Speed///////////////////////////////////////////////
    MeterSecond ("Speed", "MeterSecond" , new BigDecimal("1")),
    KilometerHour ("Speed", "KilometerHour" , new BigDecimal("3.6")),
    MilesHour ("Speed","MilesHour",new BigDecimal("2.23694")),
    Knot ("Speed","Knot",new BigDecimal("1.94384")),

    /////////////////////////////Energy///////////////////////////////////////////////
    Joule ("Energy", "Joule" , new BigDecimal("1")),
    Kilocalorie ("Energy", "Kilocalorie" , new BigDecimal("0.000239006")),
    Kilojoule ("Energy","Kilojoule",new BigDecimal("0.001")),
    GramCalorie ("Energy","GramCalorie",new BigDecimal("0.2369006")),
    WattHour ("Energy","WattHour", new BigDecimal("0.000277778")),
    KiloWattHour ("Energy","KiloWattHour", new BigDecimal("0.000000277778")),
    FootPound ("Energy","FootPound", new BigDecimal("0.737562")),

    /////////////////////////////Pressure///////////////////////////////////////////////
    Pascal ("Pressure", "Pascal", new BigDecimal("1")),
    Bar ("Pressure", "Bar", new BigDecimal("0.00001")),
    Atmostphere ("Pressure", "Atmostphere",new BigDecimal("0.0000098692")),
    PoundPerSquareInch("Pressure", "PoundPerSquareInch",new BigDecimal("0.0001485038")),

    /////////////////////////////Power///////////////////////////////////////////////
    Watt("Power", "Watt", new BigDecimal("1")),
    Horsepower("Power", "Horsepower", new BigDecimal("0.001341022089595")),
    Kilowatt("Power", "Kilowatt", new BigDecimal("0.001")),
    Milliwatt("Power", "MilliWatt", new BigDecimal("1000")),
    JouleSecond("Power","JouleSecond", new BigDecimal("1")),
    VoltAmpere ("Power", "VoltAmpere",new BigDecimal("1")),

    /////////////////////////////Time///////////////////////////////////////////////
    Minute("Time", "Minute", new BigDecimal("1")),
    Second("Time", "Second", new BigDecimal("60")),
    Hour("Time", "Hour", new BigDecimal("0.016666667")),
    Day("Time", "Day", new BigDecimal("0.000694")),
    Week("Time","Week", new BigDecimal("0.000099")),
    Year ("Time", "Year",new BigDecimal("0.000002")),

    /////////////////////////////Currency///////////////////////////////////////////////
    Euro("Currency", "Euro", new BigDecimal("1")),
    Dollar("Currency", "Dollar", new BigDecimal("1.18")),
    JapYen("Currency", "JapYen", new BigDecimal("129.28")),
    PoundSterling("Currency", "PoundSterling", new BigDecimal("0.85")),
    ChineseYuan("Currency","ChineseYuan", new BigDecimal("7.73"));







    private final String type;
    private final String name;
    private final BigDecimal value;

    Unit(String type, String name, BigDecimal value) {
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