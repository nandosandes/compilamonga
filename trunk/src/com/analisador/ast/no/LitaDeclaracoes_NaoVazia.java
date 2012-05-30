package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class LitaDeclaracoes_NaoVazia extends ListaDeclaracoes{

	private DeclaracaoVariavel declaracao;
	
	private ListaDeclaracoes listaDeclaracoes;
	
	
	
	public LitaDeclaracoes_NaoVazia(DeclaracaoVariavel declaracao,
			ListaDeclaracoes listaDeclaracoes) {
		super();
		this.declaracao = declaracao;
		this.listaDeclaracoes = listaDeclaracoes;
	}
	
	
	
	public DeclaracaoVariavel getDeclaracao() {
		return declaracao;
	}



	public void setDeclaracao(DeclaracaoVariavel declaracao) {
		this.declaracao = declaracao;
	}



	public ListaDeclaracoes getListaDeclaracoes() {
		return listaDeclaracoes;
	}



	public void setListaDeclaracoes(ListaDeclaracoes listaDeclaracoes) {
		this.listaDeclaracoes = listaDeclaracoes;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}

}
