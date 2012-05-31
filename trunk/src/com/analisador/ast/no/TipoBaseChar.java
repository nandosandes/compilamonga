package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoBaseChar extends TipoBase{
	
	
	public TipoBaseChar()
	{
		super();
	}
	
	public void accept(Visitor v) throws Exception{ v.visit(this);}


}
