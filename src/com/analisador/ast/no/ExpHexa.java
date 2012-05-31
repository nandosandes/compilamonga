package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ExpHexa extends Exp{

	 private int valorH;

	  public ExpHexa (int valor) {
	    this.valorH = valor;
	  }

	  public int getValorH() {
	    return valorH;
	  }

	  public void setValorH(int valor) {
	    this.valorH = valor;
	  }

	  public String toString(String tab) {
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(tab);
	    buffer.append("Inteiro(\n");
	    buffer.append("  "+tab+valorH);
	    buffer.append("\n");
	    buffer.append(tab);
	    buffer.append(") [Inteiro]");
	    return buffer.toString();
	  }
	  
	  public void accept(Visitor v) throws Exception {
			v.visit(this);
		}
}
