package visitor;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import function.Function;
import org.antlr.v4.runtime.tree.*;
import parser.ExpressionParser;
import parser.ExpressionVisitor;

import java.util.List;

public class CreateTreeVisitor implements ExpressionVisitor {
    private Calculator calculator;
    public CreateTreeVisitor(Calculator c){
        calculator = c;
    }

    @Override
    public Object visitExp(ExpressionParser.ExpContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Object visitPlusMinus(ExpressionParser.PlusMinusContext ctx) {
        if (ctx.getChildCount() == 1){ // if single element
            // return child result
            return ctx.getChild(0).accept(this);
        }else if (ctx.getChildCount() == 3){
            // get element of expression
            Expression e1 = (Expression) ctx.getChild(0).accept(this);
            Expression e2 = (Expression) ctx.getChild(2).accept(this);
            List<Expression> elist = List.of(e1,e2);
            // get operator
            TerminalNodeImpl ct = (TerminalNodeImpl) ctx.getChild(1);

            // if middle children is plus or minus
            try{
                switch (ct.getSymbol().getType()){
                    case ExpressionParser.PLUS:
                        return new Plus(elist);
                    case ExpressionParser.MINUS:
                        return new Minus(elist);
                    default:
                        System.out.println("SHOULD NOT BE HERE 1");
                        System.exit(0);
                        return null;
                }
            }catch (IllegalConstruction e){
                System.out.println("SHOULD NOT BE HERE 2");
                System.exit(0);
            }
        }
        System.out.println("SHOULD NOT BE HERE 3");
        System.exit(0);
        return null;
    }

    @Override
    public Object visitMultDiv(ExpressionParser.MultDivContext ctx) {
        if (ctx.getChildCount() == 1){ // if single element
            // return child result
            return ctx.getChild(0).accept(this);
        }else if (ctx.getChildCount() == 3){
            Expression e1 = (Expression) ctx.getChild(0).accept(this);
            Expression e2 = (Expression) ctx.getChild(2).accept(this);
            List<Expression> elist = List.of(e1,e2);
            TerminalNodeImpl ct = (TerminalNodeImpl) ctx.getChild(1);

            // if middle children is plus or minus
            try{
                switch (ct.getSymbol().getType()){
                    case ExpressionParser.MULT:
                        return new Times(elist);
                    case ExpressionParser.DIV:
                        return new Divides(elist);
                    default:
                        System.out.println("SHOULD NOT BE HERE 4");
                        System.exit(0);
                        return null;
                }
            }catch (IllegalConstruction e){
                System.out.println("SHOULD NOT BE HERE 5");
                System.exit(0);
            }
        }
        System.out.println("SHOULD NOT BE HERE 6");
        System.exit(0);
        return null;
    }

    @Override
    public Object visitValue(ExpressionParser.ValueContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Object visitFun(ExpressionParser.FunContext ctx){
        String funName = ctx.getChild(0).getText();

        MyNumber value = (MyNumber) this.visitNb((ExpressionParser.NbContext) ctx.getChild(2));

        if(!calculator.getStoredFun().containsKey(funName)){
            System.out.println("SHOULD NOT BE HERE 7");
            System.exit(0);
        }

        try{
            // cree une copie de la fonction
            Function f = new Function( calculator.getStoredFun().get(funName).getExpression() );
            f.setValue(value);
            return f;
        }catch (IllegalConstruction | BadAssignment e ){
            System.out.println("SHOULD NOT BE HERE 8");
            System.exit(0);
        }


        return null;
    }

    @Override
    public Object visitNb(ExpressionParser.NbContext ctx) {
        TerminalNodeImpl value = (TerminalNodeImpl) ctx.getChild(0);
        String numStr = value.getText();

        try {
            switch (value.getSymbol().getType()) {
                case ExpressionParser.INT:
                    return new IntegerNumber(numStr);
                case ExpressionParser.DECIMAL:
                    return new RealNumber(numStr);
                default:
                    System.out.println("SHOULD NOT BE HERE 7");
                    System.exit(0);
            }
        }catch (Exception e){
            System.out.println("SHOULD NOT BE HERE 8");
            System.exit(0);
        }
        System.out.println("SHOULD NOT BE HERE 9");
        System.exit(0);
        return null;
    }

    @Override
    public Object visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
