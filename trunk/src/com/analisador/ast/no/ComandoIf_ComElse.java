package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ComandoIf_ComElse extends Comando{
	
	private Exp exp;
	
	private Comando cmd1;
	
	private Comando cmd2;
	
	
	
	public ComandoIf_ComElse(Exp exp, Comando cmd1, Comando cmd2) {
		super();
		this.exp = exp;
		this.cmd1 = cmd1;
		this.cmd2 = cmd2;
	}

	public void accept(Visitor v) throws Exception{
		v.visit(this);
	}
	
	
	
	public Exp getExp() {
		return exp;
	}

	public void setExp(Exp exp) {
		this.exp = exp;
	}

	public Comando getCmd1() {
		return cmd1;
	}

	public void setCmd1(Comando cmd1) {
		this.cmd1 = cmd1;
	}

	public Comando getCmd2() {
		return cmd2;
	}

	public void setCmd2(Comando cmd2) {
		this.cmd2 = cmd2;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
