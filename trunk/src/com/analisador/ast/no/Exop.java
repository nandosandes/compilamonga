package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public  abstract class Exop {
	
	private int tipoDaExp;
	
	private int tamanhoDaExp;
	
	

	public int getTamanhoDaExp() {
		return tamanhoDaExp;
	}

	public void setTamanhoDaExp(int tamanhoDaExp) {
		this.tamanhoDaExp = tamanhoDaExp;
	}

	public int getTipoDaExp() {
		return tipoDaExp;
	}

	public void setTipoDaExp(int tipoDaExp) {
		this.tipoDaExp = tipoDaExp;
	}

	public void accept(Visitor v)throws Exception{
		v.visit(this);
	}
	
	public abstract String toString();	
}

