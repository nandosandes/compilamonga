package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exp_Chamda extends Exp {

	private Chamada chamada;
	
	
	
	
	public Chamada getChamada() {
		return chamada;
	}


	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}


	public Exp_Chamda(Chamada chamada) {
		super();
		this.chamada = chamada;
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
