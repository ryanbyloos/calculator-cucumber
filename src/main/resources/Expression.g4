grammar Expression;

INT : '-'?[0-9]+;
DECIMAL : '-'?[0-9]+'.'[0-9]+;

NAME : [a-z]+;

PLUS    : '+';
MINUS   : '-';
MULT    : '*';
DIV     : '/';
POW     : '^';

exp : plusMinus | deffun;

plusMinus :  plusMinus PLUS multDiv
           | plusMinus MINUS multDiv
           | multDiv;

multDiv : multDiv MULT pow
        | multDiv DIV pow
        | pow;

pow : pow POW value
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

multDivf : multDivf MULT powf
        | multDivf DIV powf
        | powf;

powf : powf POW valuef
     | valuef;

valuef : nb
        | funf
        | parenth
        | var;

funf : NAME'('plusMinusf')';

var : 'x';

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines