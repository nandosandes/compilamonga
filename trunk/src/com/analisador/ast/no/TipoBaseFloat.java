package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoBaseFloat extends TipoBase {
	
	public TipoBaseFloat(){}
	
	public void accept(Visitor v)
	{
		try {
			v.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
