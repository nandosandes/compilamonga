package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ExpString extends Exp {
	 private String valorStr;

	  public ExpString (String valor) {
	    this.valorStr = valor;
	  }

	  public String getValorStr() {
	    return this.valorStr;
	  }

	  public void setValorStr(String valor) {
	    this.valorStr = valor;
	  }

	  public String toString(String tab) {
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(tab);
	    buffer.append("Inteiro(\n");
	    buffer.append("  "+tab+valorStr);
	    buffer.append("\n");
	    buffer.append(tab);
	    buffer.append(") [Inteiro]");
	    return buffer.toString();
	  }
	  
	  public void accept(Visitor v) throws Exception {
			v.visit(this);
		}	
}
