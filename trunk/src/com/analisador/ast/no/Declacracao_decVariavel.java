package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Declacracao_decVariavel extends Declaracao {
	
	private DeclaracaoVariavel declaracaoVariavel;
	
	
	
	public Declacracao_decVariavel(DeclaracaoVariavel declaracaoVariavel) {
		super();
		this.declaracaoVariavel = declaracaoVariavel;
	}



	public DeclaracaoVariavel getDeclaracaoVariavel() {
		return declaracaoVariavel;
	}



	public void setDeclaracaoVariavel(DeclaracaoVariavel declaracaoVariavel) {
		this.declaracaoVariavel = declaracaoVariavel;
	}



	public void accept(Visitor v) throws Exception{v.visit(this);}
	
	
}
