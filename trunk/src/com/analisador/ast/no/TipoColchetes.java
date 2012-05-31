package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoColchetes extends Tipo{
	
	public Tipo tipo;
	
	
	
	public TipoColchetes(Tipo tipo) {
		super();
		this.tipo = tipo;
	}
	
	
	

	public Tipo getTipo() {
		return tipo;
	}




	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}




	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor v) throws Exception { v.visit(this);}

}
