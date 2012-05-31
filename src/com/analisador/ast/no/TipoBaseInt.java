package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoBaseInt extends TipoBase{
	
	
	public TipoBaseInt() {
		super();
//		this.inteiro = inteiro;
	}

	public void accept(Visitor v) throws Exception { v.visit(this);}

}
