package visitor;

import calculator.*;
import calculator.operations.Operation;
import function.Variable;

public class Printer extends Visitor{
    private final Notation notation;

    private String eval;

    public Printer(Notation n){
        notation = n;
    }

    public String getEval(Expression e)
    {
        eval = "";
        e.accept(this);
        return eval;
    }

    public void visit(MyNumber n) {  eval = n.toString();}
    @Override
    public void visit(Variable v) { eval = v.toString(); }

    @Override
    public void visit(Operation o) {
        StringBuilder tmp = new StringBuilder();
        switch (notation) {
            case INFIX  : tmp.append("( "); break;
            case PREFIX : tmp.append(o.getSymbol()).append(" ("); break;
            case POSTFIX: tmp.append("("); break;
        }

        for(Expression a:o.args.subList(0,o.args.size()-1)) {
            a.accept(this);
            if(notation == Notation.INFIX)
            {
                tmp.append(eval).append(" ").append(o.getSymbol()).append(" ");
            } else {
                tmp.append(eval).append(", ");
            }
        }

        o.args.get(o.args.size()-1).accept(this);
        tmp.append(eval);

        switch (notation) {
            case INFIX  : tmp.append(" )"); break;
            case PREFIX : tmp.append(")"); break;
            case POSTFIX: tmp.append(") ").append(o.getSymbol()); break;
        }
        eval = tmp.toString();
    }

}
