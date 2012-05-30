package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaExp_VariasExp_Imp_NotVazio extends ListaExp_VariasExp_Imp {
	
	
	private Exp exp;
	
	private ListaExp_VariasExp_Imp lista;
	
	
	
	public ListaExp_VariasExp_Imp_NotVazio(Exp exp,
			ListaExp_VariasExp_Imp lista) {
		super();
		
		this.exp = exp;
		this.lista = lista;
	}

	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
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


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
