package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Parametros {
		
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}
	
protected String id;
	
	protected int tipoDoParametro;
	
	
	
	public String getParentId() {
		return id;
	}

	public void setParentId(String id) {
		this.id = id;
	}

	public int getTipoDoParametro() {
		return tipoDoParametro;
	}

	public void setTipoDoParametro(int tipoDoParametro) {
		this.tipoDoParametro = tipoDoParametro;
	}

}
