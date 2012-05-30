package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class ListaComandos {
	
	public abstract String toString();
	
	public void accept(Visitor visitor) throws Exception
	{
		visitor.visit(this);
	}
}
