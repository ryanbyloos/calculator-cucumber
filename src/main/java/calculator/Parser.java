package calculator;

import calculator.exceptions.IllegalConstruction;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.ExpressionLexer;
import parser.ExpressionParser;
import visitor.CreateTreeVisitor;

public class Parser {
    final ExpressionParser.ExpContext tree;
    public Parser(String s){
        CharStream in = new ANTLRInputStream(s);
        ExpressionLexer lexer = new ExpressionLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.setBuildParseTree(true);

        tree = parser.exp();
    }

    public Expression getExpression(Calculator c) throws IllegalConstruction {
        CreateTreeVisitor visitor = new CreateTreeVisitor(c);
        visitor.visitExp(tree);
        return visitor.getResult();
    }
}
