package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaComandos_Vazia extends ListaComandos{

	public ListaComandos_Vazia(){}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor c) throws Exception
	{
		c.visit(this);
	}

}
