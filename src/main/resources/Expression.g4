grammar Expression;

INT : [0-9]+;
DECIMAL : [0-9]+'.'[0-9]+;

NAME : [a-z]+;

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV  : '/';

exp : plusMinus | deffun;

plusMinus :  plusMinus PLUS multDiv
           | plusMinus MINUS multDiv
           | multDiv;

multDiv : multDiv MULT value
        | multDiv DIV value
        | value;

value : nb
        | fun
        | parenth;

parenth : '('plusMinus')';

fun : NAME'('plusMinus')';

nb : INT | DECIMAL;

deffun : NAME '->' '('plusMinusf')';

plusMinusf : plusMinusf PLUS multDivf
           | plusMinusf MINUS multDivf
           | multDivf;

multDivf : multDivf MULT valuef
        | multDivf DIV valuef
        | valuef;

valuef : nb
        | fun
        | parenth
        | var;

var : 'x';

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines