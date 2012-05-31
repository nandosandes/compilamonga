// Technische Universitaet Muenchen 
// Fakultaet fuer Informatik 
// Riitta Hoellerer
//
// Praktikum des Uebersetzerbaus
// SS 2001
//
//  mimaAST.cl 
//  Classgen Abctract Syntax Tree Specification for Mima 
//
//  home page:
//  /usr/proj/uebbau/classgen-1.1/
//


package com.analisador.no;

attribute "int" resultadoCalculado with Exp;
attribute "boolean" reultadoAvaliado with Exp;

Exp		 ::=  {Inteiro} "int":valor 
			 |{ExpressaoBinaria} Exp:exp_esq "int":operador Exp:exp_dir


