package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.ComputeError;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.UselessComputation;
import time.MyDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

final public class Times extends Operation
{
	public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		symbol = "*";
		neutral = 1;
	}

	public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		symbol = "*";
		neutral = 1;
	}

	public IntegerNumber op(IntegerNumber l, IntegerNumber r){ return l.times(r); }
	public RealNumber op(RealNumber l, RealNumber r){ return l.times(r); }

	@Override
	public MyDate op(MyDate l, MyDate r) throws ComputeError {
		return null;
	}

}
