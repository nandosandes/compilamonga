package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public abstract class ListaExp {
	
	
	public Exp getExp() {
		throw new ClassCastException("Chamada Indevida de metodo");
	}

	public void setExp(Exp exp) {
		throw new ClassCastException("Chamada Indevida de metodo");
	}
	


	public int getVirg() {
		throw new ClassCastException("Chamada Indevida de metodo");
	}



	public void setVirg(int virg) {
		throw new ClassCastException("Chamada Indevida de metodo");
	}



	public ListaExp getListaExp() {
		throw new ClassCastException("Chamada Indevida de metodo");
	}

	public abstract String toString(String tab);
		
	public void accept(Visitor visitor) throws Exception {
			
			visitor.visit(this);
	 }

}
