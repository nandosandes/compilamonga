package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoChamada extends Comando {
	
	private Chamada chamada;
	
	public void accept(Visitor visitor)throws Exception
	{
		visitor.visit(this);
	}
	
	public ComandoChamada(Chamada chamada) {
		super();
		this.chamada = chamada;
	
	}

	public Chamada getChamada() {
		return chamada;
	}


	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
