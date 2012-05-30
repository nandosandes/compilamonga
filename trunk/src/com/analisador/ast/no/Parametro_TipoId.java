package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Parametro_TipoId extends Parametro {
	
	private Tipo tipo;
	
	private String ID;
	
	
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

	public Parametro_TipoId(Tipo tipo, String id) {
		super();
		this.tipo = tipo;
		this.ID   = id; 
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;

	}

}
