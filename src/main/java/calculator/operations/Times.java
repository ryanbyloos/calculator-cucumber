package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;

import java.util.List;

final public class Times extends Operation
{
	public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		setUp();
	}

	public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		setUp();
	}

	private void setUp(){
		symbol = "*";
		neutral = 1;
	}

	@Override
	public IntegerNumber op(IntegerNumber l, IntegerNumber r){ return l.times(r); }
	@Override
	public RealNumber op(RealNumber l, RealNumber r){ return l.times(r); }
}
