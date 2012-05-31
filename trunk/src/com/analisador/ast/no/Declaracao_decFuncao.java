package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Declaracao_decFuncao extends Declaracao {

	private DeclaracaoFuncao declaracaoFuncao;
	
	public void accept(Visitor v) throws Exception{v.visit(this);}

	public DeclaracaoFuncao getDeclaracaoFuncao() {
		return declaracaoFuncao;
	}

	public void setDeclaracaoFuncao(DeclaracaoFuncao declaracaoFuncao) {
		this.declaracaoFuncao = declaracaoFuncao;
	}

	public Declaracao_decFuncao(DeclaracaoFuncao declaracaoFuncao) {
		super();
		this.declaracaoFuncao = declaracaoFuncao;
	}
	
	
}
