package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaParametros_Vazia extends ListaParametros {
	
	public ListaParametros_Vazia(){}
	
	public void accept(Visitor v) throws Exception {v.visit(this);}
}
