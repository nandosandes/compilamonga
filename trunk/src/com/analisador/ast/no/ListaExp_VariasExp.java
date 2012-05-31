package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaExp_VariasExp extends ListaExp {

	private Exp exp;
	
	private ListaExp_VariasExp_Imp lista;
	
	
	public ListaExp_VariasExp(Exp exp, ListaExp_VariasExp_Imp lista) {
		super();
		this.exp = exp;
		this.lista = lista;
	}

	public void accept(Visitor visitor) throws Exception {
		visitor.visit(this);
	}
	
	
	
	public Exp getExp() {
		return exp;
	}

	public void setExp(Exp exp) {
		this.exp = exp;
	}

	public ListaExp_VariasExp_Imp getLista() {
		return lista;
	}

	public void setLista(ListaExp_VariasExp_Imp lista) {
		this.lista = lista;
	}

	public String toString(String tab) {
		// TODO Auto-generated method stub
		return null;
	}

}
