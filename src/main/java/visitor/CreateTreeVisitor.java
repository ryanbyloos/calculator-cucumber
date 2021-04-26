package visitor;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import calculator.operations.functions.*;
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
    public Object visitParenth(ExpressionParser.ParenthContext ctx) {
        return this.visitPlusMinus((ExpressionParser.PlusMinusContext) ctx.getChild(1));
    }

    @Override
    public Object visitValue(ExpressionParser.ValueContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Object visitFun(ExpressionParser.FunContext ctx){
        String funName = ctx.getChild(0).getText();

        MyNumber value = (MyNumber) this.visitNb((ExpressionParser.NbContext) ctx.getChild(2));
        try{
            switch (funName){
                case "acos":
                    return new Acos(List.of(value));
                case "acosh":
                    return new Acosh(List.of(value));
                case "asin":
                    return new Asin(List.of(value));
                case "asinh":
                    return new Asinh(List.of(value));
                case "atan":
                    return new Atan(List.of(value));
                case "atanh":
                    return new Atanh(List.of(value));
                case "cos":
                    return new Cos(List.of(value));
                case "cosh":
                    return new Cosh(List.of(value));
                case "exp": // TODO
                    return new Exp(List.of(value));
                case "inverse": // TODO
                    return new Inverse(List.of(value));
                case "log":
                    return new Log(List.of(value));
                case "sin":
                    return new Sin(List.of(value));
                case "sinh":
                    return new Sinh(List.of(value));
                case "sqrt": //TODO
                    return new SquareRoot(List.of(value));
                case "tan":
                    return new Tan(List.of(value));
                case "tanh":
                    return new Tanh(List.of(value));
                default: // case where this is not a basic function
                    if(!calculator.getStoredFun().containsKey(funName)){
                        System.out.println("SHOULD NOT BE HERE 7");
                        System.exit(0);
                    }

                    // cree une copie de la fonction
                    Function f = new Function( calculator.getStoredFun().get(funName).getExpression() );
                    f.setValue(value);
                    return f;
            }
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
