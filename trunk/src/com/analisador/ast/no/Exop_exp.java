package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exop_exp extends Exop {
	
	private Exp exp;
	
	
	
	public void accept(Visitor v)  throws Exception
	{
		v.visit(this);
	}

	

	public Exop_exp(Exp exp) {
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
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
