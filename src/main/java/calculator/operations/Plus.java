package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.TemporalException;
import time.MyDate;

import java.util.List;

final public class Plus extends Operation implements ComputeDate
{
	public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		setUp();
	}
  
	public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		setUp();
	}

	private void setUp(){
		symbol = "+";
		neutral = 0;
	}

	@Override
	public IntegerNumber op(IntegerNumber l, IntegerNumber r){ return l.plus(r); }
	@Override
	public RealNumber op(RealNumber l, RealNumber r){ return l.plus(r); }
	@Override
	public MyDate op(MyDate l, MyDate r)  {
		try {
			return l.plus(r);
		} catch (TemporalException e) {
			return null;
		}
	}
}
