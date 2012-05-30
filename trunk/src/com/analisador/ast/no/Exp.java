package com.analisador.ast.no;

import com.analisador.semantico.Visitor;



public abstract class Exp {
	
	private int resultadoCalculado;
  
	private boolean reultadoAvaliado;
	
	
	private int flag;
	
	private int valorInt;
	
	private float valFloat;
	
	private char valChar;
	

	public int getValorInt() {
		return valorInt;
	}

	public void setParentValorInt(int valorInt) {
		this.valorInt = valorInt;
	}

	public float getValFloat() {
		return valFloat;
	}

	public void setValParentFloat(float valFloat) {
		this.valFloat = valFloat;
	}

	public char getValChar() {
		return valChar;
	}

	public void setParentValChar(char valChar) {
		this.valChar = valChar;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}


	
	public int getValor() {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public void setValor(int valor) {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public Exp getExp_esq() {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public void setExp_esq(Exp exp_esq) {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public int getOperador() {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public void setOperador(int operador) {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public Exp getExp_dir() {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public void setExp_dir(Exp exp_dir) {
	    throw new ClassCastException("tried to call abstract method");
	  }

	  public int getResultadoCalculado() {
	    return resultadoCalculado;
	  }

	  public void setResultadoCalculado(int resultadoCalculado) {
	    this.resultadoCalculado = resultadoCalculado;
	  }

	  public boolean getReultadoAvaliado() {
	    return reultadoAvaliado;
	  }

	  public void setReultadoAvaliado(boolean reultadoAvaliado) {
	    this.reultadoAvaliado = reultadoAvaliado;
	  }

	  public String toString() {
	    return toString("");
	  }

	  public abstract String toString(String tab);
	
	public void accept(Visitor visitor) throws Exception {
		  visitor.visit(this);
	}
	
	
	
}
