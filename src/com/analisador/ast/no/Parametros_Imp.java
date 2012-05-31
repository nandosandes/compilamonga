package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Parametros_Imp extends Parametros{
	
	private Parametro paramatro;
	
	private ListaParametros listaParametros;
	
	
	
	public Parametro getParamatro() {
		return paramatro;
	}



	public void setParamatro(Parametro paramatro) {
		this.paramatro = paramatro;
	}



	public ListaParametros getListaParametros() {
		return listaParametros;
	}



	public void setListaParametros(ListaParametros listaParametros) {
		this.listaParametros = listaParametros;
	}



	public Parametros_Imp(Parametro paramatro, ListaParametros listaParametros) {
		super();
		this.paramatro = paramatro;
		this.listaParametros = listaParametros;
	}



	public void accept(Visitor v)  throws Exception {v.visit(this);}
}
