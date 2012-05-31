package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ProgramaVazio extends Programa{
	
	public ProgramaVazio(){
		super();
	}
	
	public void accept(Visitor v) {
		try {
			v.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
