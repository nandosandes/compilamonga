package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Exop_Vazio extends Exop {
	
	
	public Exop_Vazio(){}
	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
