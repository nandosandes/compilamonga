package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoRetorno extends Comando {
	
	private Exop exop;
	
	public ComandoRetorno(Exop exop)
	{
		this.exop = exop;
	} 
	
	

	public Exop getExop() {
		return exop;
	}



	public void setExop(Exop exop) {
		this.exop = exop;
	}



	public void accept (Visitor v) throws Exception
	{
		v.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	} 
}
