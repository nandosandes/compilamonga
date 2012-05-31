package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class VariavelID extends Var{
	
	private String id;
	
	
	public VariavelID(String id)
	{
		this.id = id;
	}
	
	
	
	
	
	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	@Override
	  public String toString(String tab) {
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(tab);
	    buffer.append("Inteiro(\n");
	    buffer.append("  ");
	    buffer.append("\n");
	    buffer.append(tab);
	    buffer.append(") [Inteiro]");
	    return buffer.toString();
	  }
	  
	  public void accept(Visitor v) throws Exception{
			v.visit(this);
		}
	

}
