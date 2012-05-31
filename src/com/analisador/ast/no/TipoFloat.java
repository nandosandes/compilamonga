package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class TipoFloat extends Tipo {
	
	private float tipoFloat;
	
	public float getTipoFloat() {
		return tipoFloat;
	}
	
	public void setTipoFloat(float tipoFloat) {
		this.tipoFloat = tipoFloat;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "tipo float";
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
