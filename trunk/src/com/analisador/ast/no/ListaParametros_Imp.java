package com.analisador.ast.no;

import com.analisador.semantico.Visitor;

public class ListaParametros_Imp extends ListaParametros {
		
		private Parametro parametro;
		
		private ListaParametros listaParametros;

		public ListaParametros_Imp(Parametro parametro,
				ListaParametros listaParametros) {
			super();
			this.parametro = parametro;
			this.listaParametros = listaParametros;
		}

		public Parametro getParametro() {
			return parametro;
		}

		public void setParametro(Parametro parametro) {
			this.parametro = parametro;
		}

		public ListaParametros getListaParametros() {
			return listaParametros;
		}

		public void setListaParametros(ListaParametros listaParametros) {
			this.listaParametros = listaParametros;
		}
		
		public void accept(Visitor v) throws Exception {v.visit(this);}
}
