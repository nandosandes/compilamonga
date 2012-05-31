package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Programa {
	
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}
