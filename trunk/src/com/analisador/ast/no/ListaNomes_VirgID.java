package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaNomes_VirgID extends ListaNomes{
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	private ListaNomes list;
	
	private String ID;
	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}

	public ListaNomes_VirgID(ListaNomes list, String id) {
		super();
		this.list = list;
		this.ID	  = id;
	}

	public ListaNomes getList() {
		return list;
	}

	public void setList(ListaNomes list) {
		this.list = list;
	}
	
	
	
}
