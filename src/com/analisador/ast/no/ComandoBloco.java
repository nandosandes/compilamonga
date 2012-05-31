package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoBloco extends Comando {

	private Bloco bloco;
	
	
	
	public ComandoBloco(Bloco bloco) {
		super();
		this.bloco = bloco;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void accept(Visitor c) throws Exception{ c.visit(this);}

}
