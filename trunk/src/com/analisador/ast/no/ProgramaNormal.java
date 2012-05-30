package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ProgramaNormal extends Programa{
		
		public Bloco bloco;	
		
		public void accept(Visitor v) throws Exception{
			v.visit(this);
		}
		
		
		

}
