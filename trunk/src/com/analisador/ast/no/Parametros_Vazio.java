package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Parametros_Vazio extends Parametros{
	
	
	public Parametros_Vazio() {
	
		// TODO Auto-generated constructor stub
	}

	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	} 
}
