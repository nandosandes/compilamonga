package com.analisador.semantico;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import com.analisador.sintatico.Constantes;

/*
 *	Classe que representa as informacoes de uma determinada entidade do
 *  codigo. Sera usada para fins de comparacoes semanticas
 * */
public class Info implements Constantes {
	
	/* Variaveis de informacao */
	
	public int natureza; // guarda a natureza da entidade. Array, global,  parametro, constante, etc 
	
	public int tipo; // tipo da informacao, int, float, char..
	
	public int numeroDoEscolpo; // contador da pilha de nomes
	
	public int tamanho; // para arrays
	
	public int offset; // enderecar variaveis e parametros
	
	public Hashtable<String, Info> listaDeParametros = new Hashtable<String, Info>();

	public Hashtable<String, Info> listaDeVariaveis  = new Hashtable<String, Info>();
	
	public int valor;
	
	public String ID;
	
	/*alteracoes de isaac 28/05*/
	public Integer valorInt;
	
	public Float valorFloat;
	
	public char valorChar;
	
	// usar este __consruct para varaveis ou parametros
	public Info(int natureza, int tipo, int contPilha, int tamanho) {
		super();
		this.natureza 		 = natureza;
		this.tipo 			 = tipo;
		this.numeroDoEscolpo = contPilha;
		this.tamanho 	     = tamanho;
	}
	
	public Info(int natureza, int tipo, int contPilha, int tamanho, String id) {
		super();
		this.natureza 		 = natureza;
		this.tipo 			 = tipo;
		this.numeroDoEscolpo = contPilha;
		this.tamanho 	     = tamanho;
		this.ID				 = id; 
	}

	// usar este __consruct para as demais entidades: funcoes, blocos
	public Info(int natureza, int contPilha, Vector listaDeParametros) {
		super();
		this.natureza 		  = natureza;
		this.numeroDoEscolpo  = contPilha;
	
	}

	@Override
	public String toString() {
		return "Info [natureza=" + natureza + ", tipo=" + tipo
				+ ", numeroDoEscolpo=" + numeroDoEscolpo + ", tamanho="
				+ tamanho + ", offset=" + offset + ", listaDeParametros="
				+ listaDeParametros + ", listaDeVariaveis=" + listaDeVariaveis
				+ "ID="+ ID + "]";
	}


	
	public Integer getValorInt() {
		return valorInt;
	}

	public void setValorInt(Integer valorInt) {
		this.valorInt = valorInt;
	}

	public Float getValorFloat() {
		return valorFloat;
	}

	public void setValorFloat(Float valorFloat) {
		this.valorFloat = valorFloat;
	}

	public char getValorChar() {
		return valorChar;
	}

	public void setValorChar(char valorChar) {
		this.valorChar = valorChar;
	}
	
	
	

}
