package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Chamada {

	
	  public  String toString(String tab)
	  {
		  return "visit chamada";
	  }
		
	  public Chamada() {
	  }
	  


	public void accept(Visitor visitor) throws Exception {
			visitor.visit(this);
	  }
		
		
}
