package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Chamada {
		
	  private int tipoDaChamada;
	  
	  
	
	  public int getTipoDaChamada() {
		return tipoDaChamada;
	}

	public void setTipoDaChamada(int tipoDaChamada) {
		this.tipoDaChamada = tipoDaChamada;
	}

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
