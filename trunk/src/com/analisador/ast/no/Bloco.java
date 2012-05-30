package com.analisador.ast.no;

import java.util.ArrayList;
import java.util.List;

import com.analisador.semantico.Visitor;

public abstract class Bloco {
	
	public Bloco(){}
	
	public void accept(Visitor visitor) throws Exception{
		visitor.visit(this);
	}
}
