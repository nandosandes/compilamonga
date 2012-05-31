package com.analisador.ast.no;

import java.util.List;

import com.analisador.semantico.Visitor;

public class DeclFuncao {
	public String ident;
	public List<Parametro> parametros;
	public Bloco bloco;
	
	public void accept(Visitor v){
		try {
			v.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
