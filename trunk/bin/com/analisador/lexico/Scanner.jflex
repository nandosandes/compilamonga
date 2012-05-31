package com.analisador.sintatico;

import java_cup.runtime.Symbol;
%%
%public
//%implements java_cup.runtime.Scanner
%class Lexer
%cup
%line
%state COMMENT

%{
  
  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }
%} 


Letras=[a-zA-Z_]
Numeros=[0-9]
Hexadecimal =0x[a-fA-F0-9]*


ESPACO_EM_BRANCO=[ \t\r\n]
String= (\\\"|[^\n\"]|\\{ESPACO_EM_BRANCO}+\\)*
COMENTARIO= ([^/*\n]|[^*\n]"/"[^*\n]|[^/\n]"*"[^/\n]|"*"[^/\n]|"/"[^*\n])*
PCHAVE = if|while|int|float|char|else|return|void|for|new

new_line = \r|\n|\r\n;

white_space = {new_line} | [ \t\f]

linha =\n
COMMENT_TEXT=([^/*\n]|[^*\n]"/"[^*\n]|[^/\n]"*"[^/\n]|"*"[^/\n]|"/"[^*\n])*
%%

"if" 			{ return new Symbol(sym.IF, new String(yytext()));    }
"else" 			{ return new Symbol(sym.ELSE, new String(yytext()));  }
"while"			{ return new Symbol(sym.WHILE, new String(yytext())); }
"int"			{ return new Symbol(sym.INT, new String(yytext()));   }
"float"			{ return new Symbol(sym.FLOAT, new String(yytext())); }
"char"			{ return new Symbol(sym.CHAR, new String(yytext()));  }
"new" 			{ return new Symbol(sym.NEW,new String(yytext()));    }
"return"		{ return new Symbol(sym.RETURN,new String(yytext())); }
"void"		    { return new Symbol(sym.VOID,new String(yytext()));   }

//<YYINITIAL>{PCHAVE}  { return new Symbol(sym.PALAVRA_CHAVE, new String(yytext()));}
[0-9]+        { return new Symbol(sym.NUMINT, new String(yytext())); }
{Hexadecimal} { return new Symbol(sym.NUMHEXA, new String(yytext()));}
{Letras}({Letras}|{Numeros})* { return new Symbol(sym.ID, new String(yytext()));}
[0-9]\.[0.9]+([eE][-+]?[0-9]+)? { return new Symbol(sym.NUMFLOAT, new String(yytext()));}

<YYINITIAL> \"{String}\" {
	String str =  yytext().substring(1,yytext().length() - 1);

	if (false == (str.length() == yytext().length() - 2)) {
	  throw (new Error("Error: Assertion failed."));
	}
      
	return new Symbol(sym.STRING, new String(yytext()));
}
<YYINITIAL> \"{String} {
	String str =  yytext().substring(1,yytext().length());
	System.out.print("Erro: linha "+yyline+" Não fechou a ");
	if (false == (str.length() == yytext().length() - 1)) {
	  throw (new Error("Error: Falha."));
	}	

		return new Symbol(sym.STRING, new String(yytext()));
}

";" 			{ return new Symbol(sym.PTVIRG, new String(yytext())); }
"+" 			{ return new Symbol(sym.SOMA,new String(yytext())); }
"-" 			{ return new Symbol(sym.SUB,new String(yytext())); }
"&&" 			{ return new Symbol(sym.E,new String(yytext())); }
"||" 			{ return new Symbol(sym.OU,new String(yytext())); }
"<=" 			{ return new Symbol(sym.MENORIG,new String(yytext())); }
">=" 			{ return new Symbol(sym.MAIORIG,new String(yytext())); }
"<" 			{ return new Symbol(sym.MENORQ,new String(yytext())); }
">" 			{ return new Symbol(sym.MAIORQ,new String(yytext())); }
"!" 			{ return new Symbol(sym.NAO,new String(yytext())); }
"*" 			{ return new Symbol(sym.MULT,new String(yytext())); }
"/" 			{ return new Symbol(sym.DIV,new String(yytext())); }
"(" 			{ return new Symbol(sym. APAR,new String(yytext())); }
")" 			{ return new Symbol(sym.FPAR,new String(yytext())); }
"[" 			{ return new Symbol(sym.ACOL,new String(yytext()));}
"]" 			{ return new Symbol(sym.FCOL,new String(yytext()));}
"," 			{ return new Symbol(sym.VIRG,new String(yytext()));}
"=" 			{ return new Symbol(sym.ATRIBUI,new String(yytext()));}
"=="			{ return new Symbol(sym.IGUAL,new String(yytext()));}
"{"			    { return new Symbol(sym.ACHA,new String(yytext()));}
"}"			    { return new Symbol(sym.FCHA,new String(yytext()));}



{white_space}     { /* ignore */ }
. {  System.out.println("Error na linha "+(yyline+1)+", coluna "+(yycolumn+1)+" na entrada : "+(yytext())); return new Symbol(sym.error, new String(yytext())); }
