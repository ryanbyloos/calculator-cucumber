package calculator;

import visitor.Evaluator;

import java.util.ArrayList;
import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        Evaluator evaluator = new Evaluator();
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() == 0) {
                throw new DivisionByZeroException();
            } else {
                args = new ArrayList<>(elist);
            }
        }
        symbol = "/";
        neutral = 1;
    }

    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        Evaluator evaluator = new Evaluator();
        for (int i = 1; i < elist.size(); i++) {
            elist.get(i).accept(evaluator);
            if (evaluator.getResult() == 0) {
                throw new DivisionByZeroException();
            } else {
                args = new ArrayList<>(elist);
            }
        }
        symbol = "/";
        neutral = 1;
    }

    public int op(int l, int r) {
        return (l / r);
    }
}
