package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoAtribuicao extends Comando {
	
	private Var var;
	
	private Exp exp;
	
	public ComandoAtribuicao(Var var, Exp exp) {
		super();
		this.var = var;
		this.exp = exp;
	}
	
	  public void accept(Visitor v) throws Exception{
			v.visit(this);
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
	public String toString() {
		// TODO Auto-generated method stub
		return "ComandoAtribuicao "+this.exp.toString()+""
			  +this.var.toString();
	}

}
