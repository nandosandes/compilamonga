package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class Var {
	
	private String id;
	
	
	
	public String getId() {
		return id;
	}


	public void setParentId(String id) {
		this.id = id;
	}
	

	public Var getVar() {
		throw new ClassCastException("tried to call abstract method");
	}


	public void setVar(Var var) {
		throw new ClassCastException("tried to call abstract method");
	}

	public Exp getExp() {
		throw new ClassCastException("tried to call abstract method");
	}

	public void setExp(Exp exp) {
		throw new ClassCastException("tried to call abstract method");
	}

	
	public String toString() 
    {
	    return toString("");
	}

    public abstract String toString(String tab);
		
	public void accept(Visitor visitor) throws Exception {
		
		visitor.visit(this);
    }
}
