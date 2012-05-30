package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class DeclaracaoFuncao_Tipo extends DeclaracaoFuncao {
	
	private Tipo tipo;
	
	private Parametros parametros;
	
	private Bloco bloco;
	
	private String id;
	
	
	
	public DeclaracaoFuncao_Tipo(Tipo tipo, Parametros parametros, Bloco bloco, String id) {
		super();
		this.tipo = tipo;
		this.parametros = parametros;
		this.bloco = bloco;
		this.id = id;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Tipo getTipo() {
		return tipo;
	}



	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}



	public Parametros getParametros() {
		return parametros;
	}



	public void setParametros(Parametros parametros) {
		this.parametros = parametros;
	}



	public Bloco getBloco() {
		return bloco;
	}



	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}



	public void accept(Visitor v) throws Exception { v.visit(this);}
	
	
}
