package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import time.MyDate;

import java.util.List;

public class Pow extends Operation{
    public /*constructor*/ Pow(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "^";
        neutral = 1;
    }

    public Pow(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "^";
        neutral = 1;
    }
    @Override
    public IntegerNumber op(IntegerNumber l, IntegerNumber r) throws ComputeError {
        return  new IntegerNumber(l.getValue().pow(r.getValue().intValue()));
    }

    @Override
    public RealNumber op(RealNumber l, RealNumber r) throws ComputeError {
        return new RealNumber(l.getValue().pow(r.toIntegerNumber().getValue().intValue()));
    }

    @Override
    public MyDate op(MyDate l, MyDate r) throws ComputeError {
        return null;
    }
}
