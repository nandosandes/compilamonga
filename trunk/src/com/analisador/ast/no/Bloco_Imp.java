package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Bloco_Imp extends Bloco {
	
	private ListaDeclaracoes listaDeclaracoes;
	
	private ListaComandos listaComandos;
	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}

	public Bloco_Imp(ListaDeclaracoes listaDeclaracoes,
			ListaComandos listaComandos) {
		super();
		this.listaDeclaracoes = listaDeclaracoes;
		this.listaComandos = listaComandos;
	}

	public ListaDeclaracoes getListaDeclaracoes() {
		return listaDeclaracoes;
	}

	public void setListaDeclaracoes(ListaDeclaracoes listaDeclaracoes) {
		this.listaDeclaracoes = listaDeclaracoes;
	}

	public ListaComandos getListaComandos() {
		return listaComandos;
	}

	public void setListaComandos(ListaComandos listaComandos) {
		this.listaComandos = listaComandos;
	}
	
	

}
