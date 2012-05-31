package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoChar extends Tipo {
	public char tipoChar;

	public TipoChar(char tipoChar) {
		super();
		this.tipoChar = tipoChar;
	}

	public char getTipoChar() {
		return tipoChar;
	}

	public void setTipoChar(char tipoChar) {
		this.tipoChar = tipoChar;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "tipo char";
	}
	
	public void accept(Visitor v){
		try {
			v.visit(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
