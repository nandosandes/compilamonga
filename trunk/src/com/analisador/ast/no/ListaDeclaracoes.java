package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class ListaDeclaracoes {
	
	public void accept(Visitor c) throws Exception
	{
		c.visit(this);
	}
	
	public abstract String toString();
	
	protected int tipoDaDeclaracao;

	public int getTipoDaDeclaracao() {
		return tipoDaDeclaracao;
	}

	public void setTipoDaDeclaracao(int tipoDaDeclaracao) {
		this.tipoDaDeclaracao = tipoDaDeclaracao;
	}
	

}
