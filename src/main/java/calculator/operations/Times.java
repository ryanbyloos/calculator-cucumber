package calculator.operations;

import calculator.Expression;
import calculator.Notation;
import calculator.exceptions.IllegalConstruction;

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

	public BigInteger op(BigInteger l, BigInteger r) { return l.multiply(r); }
  	public BigDecimal op(BigDecimal l, BigDecimal r) { return l.multiply(r); }
}
