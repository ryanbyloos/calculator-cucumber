package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.operations.Operation;

import java.math.BigDecimal;
import java.math.BigInteger;
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
  
	public BigInteger op(BigInteger l, BigInteger r) {
	return l.add(r.negate());
	}
	public BigDecimal op(BigDecimal l, BigDecimal r) {
		return l.add(r.negate());
	}
}
