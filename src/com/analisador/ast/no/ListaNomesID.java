package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaNomesID extends ListaNomes {
	
	private String ID;
	
	
	
	public void accept(Visitor v) throws Exception
	{	
		v.visit(this);
    }
	
	public ListaNomesID(String d)
	{
		this.ID = d;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	
}
