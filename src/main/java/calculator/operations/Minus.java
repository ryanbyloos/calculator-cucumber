package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import time.MyDate;

import java.util.List;

final public class Minus extends Operation implements ComputeDate
{
	public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		setUp();
	}

	public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		setUp();
	}

	private void setUp(){
		symbol = "-";
		neutral = 0;
	}

	@Override
	public IntegerNumber op(IntegerNumber l, IntegerNumber r) { return l.minus(r); }
	@Override
	public RealNumber op(RealNumber l, RealNumber r) { return l.minus(r); }
	@Override
	public MyDate op (MyDate l, MyDate r){return l.minus(r);}
}
