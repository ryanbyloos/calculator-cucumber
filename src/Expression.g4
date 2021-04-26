grammar Expression;

INT : [0-9]+;
DECIMAL : [0-9]+'.'[0-9]+;

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV  : '/';

exp : plusMinus;


plusMinus :  plusMinus PLUS multDiv
           | plusMinus MINUS multDiv
           | multDiv;

multDiv : multDiv MULT nb
        | multDiv DIV nb
        | nb;

nb : INT | DECIMAL;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines