package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{
	//ukljucivanje informacije o poziciji tokena
	 private Symbol new_symbol(int type){
	 return new Symbol (type, yyline + 1, yycolumn);
	 }
	// ukljucivanje informacije o poziciji tokena
	  private Symbol new_symbol(int type, Object value){
	 return new Symbol (type, yyline + 1, yycolumn, value);
	 }
%}

%cup
%line
%column
%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{}
"\b" 	{}
"\t" 	{}
"\r\n" 	{}
"\f" 	{}

"program" 	{return new_symbol(sym.PROGRAM, yytext());}
"break" 	{return new_symbol(sym.BREAK, yytext());}
"class" 	{return new_symbol(sym.CLASS, yytext());}
"else" 		{return new_symbol(sym.ELSE, yytext());}
"const" 	{return new_symbol(sym.CONST, yytext());}
"if" 		{return new_symbol(sym.IF, yytext());}
"new" 		{return new_symbol(sym.NEW, yytext());}
"print" 	{return new_symbol(sym.PRINT, yytext());}
"read" 		{return new_symbol(sym.READ, yytext());}
"return" 	{return new_symbol(sym.RETURN, yytext());}
"void" 		{return new_symbol(sym.VOID, yytext());}
"extends" 	{return new_symbol(sym.EXTENDS, yytext());}
"continue" 	{return new_symbol(sym.CONTINUE, yytext());}
"for" 		{return new_symbol(sym.FOR, yytext());}
"static" 	{return new_symbol(sym.STATIC, yytext());}
"range"		{return new_symbol(sym.RANGE, yytext());}
"in"		{return new_symbol(sym.IN, yytext());}

"+" 	{return new_symbol(sym.PLUS, yytext());}
"-" 	{return new_symbol(sym.MINUS, yytext());}
"*" 	{return new_symbol(sym.MULTIPLY, yytext());}
"/" 	{return new_symbol(sym.DIVIDE, yytext());}
"%" 	{return new_symbol(sym.MODUO, yytext());}
"==" 	{return new_symbol(sym.ISEQUAL, yytext());}
"!=" 	{return new_symbol(sym.NOTEQUAL, yytext());}
">" 	{return new_symbol(sym.GREATER, yytext());}
">=" 	{return new_symbol(sym.GREATEREQUAL,yytext());}
"<" 	{return new_symbol(sym.LESS, yytext());}
"<=" 	{return new_symbol(sym.LESSEQUAL, yytext());}
"&&" 	{return new_symbol(sym.AND, yytext());}
"||" 	{return new_symbol(sym.OR, yytext());}
"=" 	{return new_symbol(sym.EQUAL, yytext());}
"++" 	{return new_symbol(sym.INCREMENT, yytext());}
"--" 	{return new_symbol(sym.DECREMENT, yytext());}
"::"	{return new_symbol(sym.DOUBLECOLON, yytext());}
";" 	{return new_symbol(sym.SEMICOLON, yytext());}
":" 	{return new_symbol(sym.COLON, yytext());}
"," 	{return new_symbol(sym.COMMA, yytext());}
"." 	{return new_symbol(sym.DOT, yytext());}
"(" 	{return new_symbol(sym.LEFTPAREN, yytext());}
")" 	{return new_symbol(sym.RIGHTPAREN, yytext());}
"[" 	{return new_symbol(sym.LEFTBRACKET, yytext());}
"]" 	{return new_symbol(sym.RIGHTBRACKET, yytext());}
"{" 	{return new_symbol(sym.LEFTBRACE, yytext());}
"}" 	{return new_symbol(sym.RIGHTBRACE, yytext());}
"=>" 	{return new_symbol(sym.ARROW, yytext());}
"+++"   {return new_symbol(sym.INCDOBL, yytext());}

"//"               	{yybegin(COMMENT);} 
<COMMENT> .        	{yybegin(COMMENT);}
<COMMENT> "\r\n"	{yybegin(YYINITIAL);}

[0-9]+ 						{return new_symbol(sym.NUMCONSTANT, Integer.parseInt(yytext()));}
"'"[ -~]"'"		 			{return new_symbol(sym.CHARCONSTANT,new Character (yytext().charAt(1)));}
"true"  					{return new_symbol(sym.BOOLCONSTANT, true);}
"false" 					{return new_symbol(sym.BOOLCONSTANT, false);}
([a-z]|[A-Z])[a-zA-Z0-9_]* 			{return new_symbol(sym.IDENTIFICATOR, yytext());} 

. { System.err.println("Leksicka greska (" + yytext() + ")  liniji " + (yyline + 1) +" i koloni " + (yycolumn + 1));}