package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exp_Var extends Exp{
	
	private Var var;
	
	
	
	public Exp_Var(Var var) {
		super();
		this.var = var;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	@Override
	public String toString(String tab) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor v) throws Exception{
		v.visit(this);
	}

}
