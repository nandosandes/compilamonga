package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoInt extends Tipo {
	private int tipo_int;

	public int getTipo_int() {
		return tipo_int;
	}

	public void setTipo_int(int tipo_int) {
		this.tipo_int = tipo_int;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "tipo int";
	}
	
	public void accept(Visitor v) throws Exception{
		v.visit(this);
	}
	
}
