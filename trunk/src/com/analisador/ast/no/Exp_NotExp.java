package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exp_NotExp extends Exp {
	
	private Exp exp;
	
	
	
	public Exp_NotExp(Exp exp) {
		super();
		this.exp = exp;
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
