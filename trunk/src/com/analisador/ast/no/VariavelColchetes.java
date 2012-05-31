package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class VariavelColchetes extends Var{
	
	private Var var;
	
	private Exp exp;
	
	public VariavelColchetes(Var var, Exp exp) {
		super();
		this.var = var;
		this.exp = exp;
	
	}

	public void accept(Visitor visitor) throws Exception {
		visitor.visit(this);
    }
	
	public Var getVar() {
		return var;
	}


	public void setVar(Var var) {
		this.var = var;
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

}
