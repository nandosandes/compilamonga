package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class Programa_naoVazio extends Programa {
		
		private Declaracao declaracao;
		
		private Programa programa;
		
		
	
	
	    public Declaracao getDeclaracao() {
			return declaracao;
		}




		public void setDeclaracao(Declaracao declaracao) {
			this.declaracao = declaracao;
		}




		public Programa getPrograma() {
			return programa;
		}




		public void setPrograma(Programa programa) {
			this.programa = programa;
		}




		public Programa_naoVazio(Declaracao declaracao, Programa programa) {
			super();
			this.declaracao = declaracao;
			this.programa = programa;
		}




		public void accept(Visitor v) throws Exception
		{
			v.visit(this);
		}
}
