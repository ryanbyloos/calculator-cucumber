package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Notation;
import calculator.Operation;

public class Printer extends Visitor{
    private Notation notation;

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

    @Override
    public void visit(MyNumber n) {
        eval = n.toString();
    }

    @Override
    public void visit(Operation o) {
        String tmp = "";
        switch (notation) {
            case INFIX  : tmp+="( "; break;
            case PREFIX : tmp+=o.getSymbol()+" ("; break;
            case POSTFIX: tmp+="("; break;
        }

        for(Expression a:o.args.subList(0,o.args.size()-1)) {
            a.accept(this);
            if(notation == Notation.INFIX)
            {
                tmp += eval + " " + o.getSymbol() + " ";
            } else {
                tmp += eval+ ", ";
            }
        }

        o.args.get(o.args.size()-1).accept(this);
        tmp += eval ;

        switch (notation) {
            case INFIX  : tmp += " )"; break;
            case PREFIX : tmp +=  ")"; break;
            case POSTFIX: tmp += ") "+o.getSymbol(); break;
        }
        eval = tmp;
    }
}
