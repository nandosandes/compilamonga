package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoIf_semElse extends Comando{
	
	private Exp exp;
	
	private Comando comando;
	
	
	
	public ComandoIf_semElse(Exp exp, Comando comando) {
		super();
		this.exp = exp;
		this.comando = comando;
	}

	
	
	public Exp getExp() {
		return exp;
	}



	public void setExp(Exp exp) {
		this.exp = exp;
	}



	public Comando getComando() {
		return comando;
	}



	public void setComando(Comando comando) {
		this.comando = comando;
	}



	public void accept(Visitor visitor) throws Exception
	{
		visitor.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
