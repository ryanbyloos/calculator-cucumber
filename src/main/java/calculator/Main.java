package calculator;

import calculator.exceptions.IllegalConstruction;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import parser.*;
import visitor.CreateTreeVisitor;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main {

  public static void main(String[] args) {
	CharStream in = new ANTLRInputStream("2+3*2+2.8");
	ExpressionLexer lexer = new ExpressionLexer(in);
	CommonTokenStream tokens = new CommonTokenStream(lexer);
	ExpressionParser parser = new ExpressionParser(tokens);
	parser.setBuildParseTree(true);

	ParseTree tree = parser.exp();

	CreateTreeVisitor visitor = new CreateTreeVisitor();
  	Calculator c1 = new Calculator(Calculator.Mode.REAL);
	Expression e1 = (Expression) visitor.visitExp((ExpressionParser.ExpContext) tree);
	c1.print(e1);
	c1.eval(e1);

  	Expression e;
  	Calculator c = new Calculator(Calculator.Mode.INTEGER);

	try{
		// Here is an example of how to use the calculator:

		e = new IntegerNumber("8");
		c.print(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new IntegerNumber("3"), new IntegerNumber("4"), new IntegerNumber("5"));
	    e = new Plus(params,Notation.PREFIX);
		c.printExpressionDetails(e);
		c.eval(e);
	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new IntegerNumber("5"), new IntegerNumber("3"));
		e = new Minus(params2, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new IntegerNumber("5"));
		e = new Divides(params4,Notation.POSTFIX);
		c.print(e);
		c.eval(e);
	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
