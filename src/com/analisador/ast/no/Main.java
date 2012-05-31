package com.analisador.ast.no;

import com.analisador.semantico.PrintVisitor;

public class Main {
	public static void main(String [] args){
//		ExpFloat exp = new ExpFloat(0, 1);
//		ExpFloat exp2= new ExpFloat(0, 3);
//		
//		System.out.println("valor: " +(new ExpFloat(2, 3).opFloat(exp, exp2)).getValue());
		System.out.println("percorrendo ast");
		
		ProgramaNormal pn = new ProgramaNormal();
		
		//Bloco bloco = new Bloco();
		
		//pn.bloco = bloco;
		
		//Comando comando = new Comando("if", null);
		
	//	bloco.addComando(comando);
//		bloco.addComando(new Comando("while", null));
	
	//	TipoChar tc = new TipoChar('a');
		
	//	Var v = new Var(tc, "var");
		
		//bloco.addVariavel(v);
		
		PrintVisitor pv = new PrintVisitor();
		
		//pv.visit(pn);
		
		ExpInt ei = new ExpInt(3);
		ExpInt ei_ = new ExpInt(3);
		
	//	ExpressaoBinaria e = new ExpressaoBinaria(ei, 2, ei);
		
//		pv.visit(e);
	}
	
}
