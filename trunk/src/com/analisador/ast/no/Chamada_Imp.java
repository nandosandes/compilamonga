package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Chamada_Imp extends Chamada{
	private ListaExp listaExp;
	
	private String ID;
	
	public Chamada_Imp(ListaExp listaExp, String id) {
		super();
		this.listaExp = listaExp;
		this.ID = id;
	}


	

	public String getID() {
		return ID;
	}




	public void setID(String iD) {
		ID = iD;
	}




	public ListaExp getListaExp() {
		return listaExp;
	}




	public void setListaExp(ListaExp listaExp) {
		this.listaExp = listaExp;
	}




	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
