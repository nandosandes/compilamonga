package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaExp_NoExp extends ListaExp{
	
	
	
	public ListaExp_NoExp() {
		super();

	}

	public void accept(Visitor visitor) throws Exception {
		visitor.visit(this);
	}

	@Override
	public String toString(String tab) {
		// TODO Auto-generated method stub
		return null;
	}

}
