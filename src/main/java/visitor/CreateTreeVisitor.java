package visitor;

import calculator.*;
import calculator.exceptions.BadAssignment;
import calculator.exceptions.IllegalConstruction;
import calculator.exceptions.InvalidSyntax;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import calculator.operations.functions.*;
import function.Function;
import function.Variable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.*;
import parser.ExpressionParser;
import parser.ExpressionVisitor;

import java.util.List;

public class CreateTreeVisitor implements ExpressionVisitor {
    private final Calculator calculator;
    private Expression result;
    private IllegalConstruction exception;

    public CreateTreeVisitor(Calculator c){
        calculator = c;
    }

    public Expression getResult() throws IllegalConstruction{
        if(exception != null) throw exception;
        return result;
    }

    public Expression plusMinus(ParserRuleContext ctx){
        if (ctx.getChildCount() == 1){ // if single element
            // return child result
            result = (Expression) ctx.getChild(0).accept(this);
            return result;
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
                        result = new Plus(elist);
                        return result;
                    case ExpressionParser.MINUS:
                        result = new Minus(elist);
                        return result;
                    default:
                        exception = new InvalidSyntax("Unsupported case");
                        return null;
                }
            }catch (IllegalConstruction e){
                exception = new InvalidSyntax("Parser accept an unsupported syntax");
                return null;
            }
        }
        exception = new InvalidSyntax("PlusMinus unsupported number of child : "+ctx.getChildCount());
        return null;
    }

    public Object multDiv(ParserRuleContext ctx) {
        if (ctx.getChildCount() == 1){ // if single element
            // return child result
            result = (Expression) ctx.getChild(0).accept(this);
            return result;
        }else if (ctx.getChildCount() == 3){
            Expression e1 = (Expression) ctx.getChild(0).accept(this);
            Expression e2 = (Expression) ctx.getChild(2).accept(this);
            List<Expression> elist = List.of(e1,e2);
            TerminalNodeImpl ct = (TerminalNodeImpl) ctx.getChild(1);

            // if middle children is plus or minus
            try{
                switch (ct.getSymbol().getType()){
                    case ExpressionParser.MULT:
                        result = new Times(elist);
                        return result;
                    case ExpressionParser.DIV:
                        result = new Divides(elist);
                        return result;
                    default:
                        exception = new InvalidSyntax("Unsupported case");
                        return null;
                }
            }catch (IllegalConstruction e){
                exception = new InvalidSyntax("Parser accept an unsupported syntax");
                return null;
            }
        }
        exception = new InvalidSyntax("PlusMinus unsupported number of child : "+ctx.getChildCount());
        return null;
    }

    @Override
    public Object visitExp(ExpressionParser.ExpContext ctx) {
        result = (Expression) ctx.getChild(0).accept(this);
        return result;
    }

    @Override
    public Object visitPlusMinus(ExpressionParser.PlusMinusContext ctx) {
        return plusMinus(ctx);
    }

    @Override
    public Object visitMultDiv(ExpressionParser.MultDivContext ctx) {
        return multDiv(ctx);
    }

    @Override
    public Object visitParenth(ExpressionParser.ParenthContext ctx) {
        result = (Expression) this.visitPlusMinus((ExpressionParser.PlusMinusContext) ctx.getChild(1));
        return result;
    }

    @Override
    public Object visitValue(ExpressionParser.ValueContext ctx) {
        result = (Expression) ctx.getChild(0).accept(this);
        return result;
    }

    @Override
    public Object visitFun(ExpressionParser.FunContext ctx){
        String funName = ctx.getChild(0).getText();

        Expression value = (Expression) ctx.getChild(2).accept(this);
        try{
            switch (funName){
                case "acos":
                    result = new Acos(List.of(value));
                    return result;
                case "acosh":
                    result = new Acosh(List.of(value));
                    return result;
                case "asin":
                    result = new Asin(List.of(value));
                    return result;
                case "asinh":
                    result = new Asinh(List.of(value));
                    return result;
                case "atan":
                    result = new Atan(List.of(value));
                    return result;
                case "atanh":
                    result = new Atanh(List.of(value));
                    return result;
                case "cos":
                    result = new Cos(List.of(value));
                    return result;
                case "cosh":
                    result = new Cosh(List.of(value));
                    return result;
                case "exp": // TODO
                    result = new Exp(List.of(value));
                    return result;
                case "inverse": // TODO
                    result = new Inverse(List.of(value));
                    return result;
                case "log":
                    result = new Log(List.of(value));
                    return result;
                case "sin":
                    result = new Sin(List.of(value));
                    return result;
                case "sinh":
                    result = new Sinh(List.of(value));
                    return result;
                case "sqrt": //TODO
                    result = new SquareRoot(List.of(value));
                    return result;
                case "tan":
                    result = new Tan(List.of(value));
                    return result;
                case "tanh":
                    result = new Tanh(List.of(value));
                    return result;
                default: // case where this is not a basic function
                    if(!calculator.getStoredFun().containsKey(funName)){
                        exception = new InvalidSyntax("Function "+funName+" does not exist");
                        return null;
                    }

                    // cree une copie de la fonction
                    Function f = new Function( calculator.getStoredFun().get(funName).getExpression() );
                    f.setValue(value);
                    result = f;
                    return f;
            }
        }catch (IllegalConstruction | BadAssignment e ){
            exception = new InvalidSyntax("Parser accept an unsupported syntax");
            return null;
        }
    }

    @Override
    public Object visitNb(ExpressionParser.NbContext ctx) {
        TerminalNodeImpl value = (TerminalNodeImpl) ctx.getChild(0);
        String numStr = value.getText();

        try {
            switch (value.getSymbol().getType()) {
                case ExpressionParser.INT:
                    result = new IntegerNumber(numStr);
                    return result;
                case ExpressionParser.DECIMAL:
                    result = new RealNumber(numStr);
                    return result;
                default:
                    exception = new InvalidSyntax("Unsupported case");
                    return null;
            }
        }catch (Exception e){
            exception = new InvalidSyntax("Parser accept an unsupported syntax");
            return null;
        }
    }

    @Override
    public Object visitDeffun(ExpressionParser.DeffunContext ctx) {
        String funName = ctx.getChild(0).getText();
        result = (Expression) ctx.getChild(3).accept(this);
        try {
            calculator.addFunction(funName,new Function(result));
        }catch (IllegalConstruction e){
            exception = e;
        }
        return null;
    }

    @Override
    public Object visitPlusMinusf(ExpressionParser.PlusMinusfContext ctx) {
        return plusMinus(ctx);
    }

    @Override
    public Object visitMultDivf(ExpressionParser.MultDivfContext ctx) {
        return multDiv(ctx);
    }

    @Override
    public Object visitValuef(ExpressionParser.ValuefContext ctx) {
        result = (Expression) ctx.getChild(0).accept(this);
        return result;
    }

    @Override
    public Object visitVar(ExpressionParser.VarContext ctx) {
        result = new Variable();
        return result;
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
