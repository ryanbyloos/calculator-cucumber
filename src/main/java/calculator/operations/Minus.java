package calculator.operations;

import calculator.Expression;
import calculator.IntegerNumber;
import calculator.Notation;
import calculator.RealNumber;
import calculator.exceptions.IllegalConstruction;
import time.MyDate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

final public class Minus extends Operation
{
	public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
		super(elist);
		symbol = "-";
		neutral = 0;
	}

	public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
		super(elist,n);
		symbol = "-";
		neutral = 0;
	}

	public IntegerNumber op(IntegerNumber l, IntegerNumber r) { return l.minus(r); }
	public RealNumber op(RealNumber l, RealNumber r) { return l.minus(r); }
	public MyDate op (MyDate l, MyDate r){return l.minus(r);}
}
