package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exp_New extends Exp {

	private Tipo tipo;
	
	private Exp exp;
	
	
	
	public Exp_New(Tipo tipo, Exp exp) {
		super();
		this.tipo = tipo;
		this.exp = exp;
	}
	
	

	public Tipo getTipo() {
		return tipo;
	}



	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}



	public Exp getExp() {
		return exp;
	}



	public void setExp(Exp exp) {
		this.exp = exp;
	}



	@Override
	public String toString(String tab) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}
