package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Tipo_TipoBase extends Tipo{

	private TipoBase tipo_base;
	
	
	public Tipo_TipoBase(TipoBase tipo_base) {
		super();
		this.tipo_base = tipo_base;
	}
	
	

	public TipoBase getTipo_base() {
		return tipo_base;
	}



	public void setTipo_base(TipoBase tipo_base) {
		this.tipo_base = tipo_base;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor v) throws Exception { v.visit(this); }

}
