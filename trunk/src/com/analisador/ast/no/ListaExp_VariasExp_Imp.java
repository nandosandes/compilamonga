package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class ListaExp_VariasExp_Imp {
	
	public abstract String toString();
	
	public void accept (Visitor v) throws Exception
	{
		v.visit(this);
	}
}
