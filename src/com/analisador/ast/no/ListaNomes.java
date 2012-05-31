package com.analisador.ast.no;

import java.util.ArrayList;

import com.analisador.semantico.Visitor;

public abstract class ListaNomes {
	
	private ArrayList<String> listaDeNomes = new ArrayList<String>();
	
	public void accept(Visitor v) throws Exception
	{	
		v.visit(this);
    }

	public ArrayList<String> getListaDeNomes() {
		return listaDeNomes;
	}

	public void setListaDeNomes(ArrayList<String> listaDeNomes) {
		this.listaDeNomes = listaDeNomes;
	}
	
	
}
