package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class TipoBase {
	
	private int tipoVar;
	
	
	
	public int getTipoVar() {
		return tipoVar;
	}

	public void setTipoVar(int tipoVar) {
		this.tipoVar = tipoVar;
	}

	public void accept(Visitor c) throws Exception
	{
		try {
			c.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TipoBase(){}
}
