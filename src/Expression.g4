grammar Expression;

INT : [0-9]+;
DECIMAL : [0-9]+'.'[0-9]+;

NAME : [a-z]+;

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV  : '/';

exp : plusMinus;


plusMinus :  plusMinus PLUS multDiv
           | plusMinus MINUS multDiv
           | multDiv;

multDiv : multDiv MULT value
        | multDiv DIV value
        | value;

value : nb
        | fun;

fun : NAME'('nb')';

nb : INT | DECIMAL;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines