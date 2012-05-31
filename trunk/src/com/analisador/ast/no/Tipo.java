package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Tipo {
	
	protected int elTipo;
	
	
	
	public int getElTipo() {
		return elTipo;
	}

	public void setElTipo(int elTipo) {
		this.elTipo = elTipo;
	}



	public abstract String toString();
	
	public void accept(Visitor v) throws Exception{
		try {
			v.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
