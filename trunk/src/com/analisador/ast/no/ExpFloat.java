package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ExpFloat extends Exp{
	
	  private float valorF;

	  public ExpFloat (float valor) {
	    this.valorF = valor;
	  }

	  public float getValorF()
	  {
		  return this.valorF;
	  }

	  public void setValor(float valor) {
	    this.valorF = valor;
	  }

	  public String toString(String tab) {
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(tab);
	    buffer.append("Inteiro(\n");
	    buffer.append("  "+tab+valorF);
	    buffer.append("\n");
	    buffer.append(tab);
	    buffer.append(") [Inteiro]");
	    return buffer.toString();
	  }
	
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

	
	
	
	

}
