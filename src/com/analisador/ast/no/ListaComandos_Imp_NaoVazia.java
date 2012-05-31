package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaComandos_Imp_NaoVazia extends ListaComandos{
	
	private Comando comando;
	
	private ListaComandos listaComandos;
	
	
	
	public ListaComandos_Imp_NaoVazia(Comando comando,
			ListaComandos listaComandos) {
		super();
		this.comando = comando;
		this.listaComandos = listaComandos;
	}



	public Comando getComando() {
		return comando;
	}



	public void setComando(Comando comando) {
		this.comando = comando;
	}



	public ListaComandos getListaComandos() {
		return listaComandos;
	}



	public void setListaComandos(ListaComandos listaComandos) {
		this.listaComandos = listaComandos;
	}

	public void accept(Visitor v) throws Exception { v.visit(this);}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
