package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class BlocoPtvirg extends Bloco{
	
	public BlocoPtvirg()
	{
		
	}
	
	public void accept(Visitor v) throws Exception
	{
		v.visit(this);
	}

}
