package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaDeclaracoes_Vazio extends ListaDeclaracoes {

	
	public ListaDeclaracoes_Vazio(){}
	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
