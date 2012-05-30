package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class DeclaracaoVariavel extends ListaDeclaracoes{
	
	private Tipo tipo;
	
	private ListaNomes lista_nomes;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public DeclaracaoVariavel(Tipo tipo, ListaNomes lista_nomes) {
		super();
		this.tipo = tipo;
		this.lista_nomes = lista_nomes;
	}

	
	
	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public ListaNomes getLista_nomes() {
		return lista_nomes;
	}


	public void setLista_nomes(ListaNomes lista_nomes) {
		this.lista_nomes = lista_nomes;
	}


	public void accept(Visitor v){try {
		v.visit(this);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

}
