package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaExp_VariasExp_Imp_Vazio extends ListaExp_VariasExp_Imp {
	
	public ListaExp_VariasExp_Imp_Vazio() {
		super();

	}

	public void accept(Visitor visitor)throws Exception {
		visitor.visit(this);
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}	
