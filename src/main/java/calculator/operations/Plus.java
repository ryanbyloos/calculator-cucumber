package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

final public class Plus extends Operation
{

	public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		symbol = "+";
		neutral = 0;
	}
  
	public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		symbol = "+";
		neutral = 0;
	}

	public IntegerNumber op(IntegerNumber l, IntegerNumber r){ return l.plus(r); }
	public RealNumber op(RealNumber l, RealNumber r){ return l.plus(r); }
}
