package com.analisador.semantico;

import java.io.Console;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;

import JFlex.DFA;

import com.analisador.ast.no.Bloco;
import com.analisador.ast.no.BlocoPtvirg;
import com.analisador.ast.no.Bloco_Imp;
import com.analisador.ast.no.Chamada;
import com.analisador.ast.no.Chamada_Imp;
import com.analisador.ast.no.Comando;
import com.analisador.ast.no.ComandoAtribuicao;
import com.analisador.ast.no.ComandoBloco;
import com.analisador.ast.no.ComandoChamada;
import com.analisador.ast.no.ComandoIf_ComElse;
import com.analisador.ast.no.ComandoIf_semElse;
import com.analisador.ast.no.ComandoRetorno;
import com.analisador.ast.no.ComandoWhile;
import com.analisador.ast.no.DeclFuncao;
import com.analisador.ast.no.Declacracao_decVariavel;
import com.analisador.ast.no.Declaracao;
import com.analisador.ast.no.DeclaracaoFuncao;
import com.analisador.ast.no.DeclaracaoFuncao_Tipo;
import com.analisador.ast.no.DeclaracaoFuncao_void;
import com.analisador.ast.no.DeclaracaoVariavel;
import com.analisador.ast.no.Declaracao_decFuncao;
import com.analisador.ast.no.Exop;
import com.analisador.ast.no.Exop_Vazio;
import com.analisador.ast.no.Exop_exp;
import com.analisador.ast.no.Exp;
import com.analisador.ast.no.ExpFloat;
import com.analisador.ast.no.ExpHexa;
import com.analisador.ast.no.ExpInt;
import com.analisador.ast.no.ExpString;
import com.analisador.ast.no.Exp_Chamda;
import com.analisador.ast.no.Exp_New;
import com.analisador.ast.no.Exp_NotExp;
import com.analisador.ast.no.Exp_Parenteses;
import com.analisador.ast.no.Exp_Sub;
import com.analisador.ast.no.Exp_Var;
import com.analisador.ast.no.ExpressaoBinaria;
import com.analisador.ast.no.ListaComandos;
import com.analisador.ast.no.ListaComandos_Imp_NaoVazia;
import com.analisador.ast.no.ListaComandos_Vazia;
import com.analisador.ast.no.ListaDeclaracoes;
import com.analisador.ast.no.ListaDeclaracoes_Vazio;
import com.analisador.ast.no.ListaExp;
import com.analisador.ast.no.ListaExp_NoExp;
import com.analisador.ast.no.ListaExp_VariasExp;
import com.analisador.ast.no.ListaExp_VariasExp_Imp;
import com.analisador.ast.no.ListaExp_VariasExp_Imp_NotVazio;
import com.analisador.ast.no.ListaExp_VariasExp_Imp_Vazio;
import com.analisador.ast.no.ListaNomes;
import com.analisador.ast.no.ListaNomesID;
import com.analisador.ast.no.ListaNomes_VirgID;
import com.analisador.ast.no.ListaParametros;
import com.analisador.ast.no.ListaParametros_Imp;
import com.analisador.ast.no.ListaParametros_Vazia;
import com.analisador.ast.no.LitaDeclaracoes_NaoVazia;
import com.analisador.ast.no.Parametro;
import com.analisador.ast.no.Parametro_TipoId;
import com.analisador.ast.no.Parametros;
import com.analisador.ast.no.Parametros_Imp;
import com.analisador.ast.no.Parametros_Vazio;
import com.analisador.ast.no.Programa;
import com.analisador.ast.no.ProgramaNormal;
import com.analisador.ast.no.ProgramaVazio;
import com.analisador.ast.no.Programa_naoVazio;
import com.analisador.ast.no.Tipo;
import com.analisador.ast.no.TipoBase;
import com.analisador.ast.no.TipoBaseChar;
import com.analisador.ast.no.TipoBaseFloat;
import com.analisador.ast.no.TipoBaseInt;
import com.analisador.ast.no.TipoChar;
import com.analisador.ast.no.TipoColchetes;
import com.analisador.ast.no.TipoFloat;
import com.analisador.ast.no.TipoInt;
import com.analisador.ast.no.Tipo_TipoBase;
import com.analisador.ast.no.Var;
import com.analisador.ast.no.VariavelColchetes;
import com.analisador.ast.no.VariavelID;
import com.analisador.sintatico.Constantes;

public class PrintVisitor implements Visitor, Constantes {

	private Hashtable<String, Info> tabelaDeSimbolos = new Hashtable<String, Info>();

	private Stack<String> nomesLocais = new Stack<String>();

	private ArrayList<String> ListaDeVariaviesDeclaradas = new ArrayList<String>();

	private ArrayList<Info> ListaDeParametrosDeclaradas = new ArrayList<Info>();

	private int numeroDoEscolpo = -1;

	private int offset = -1;

	/*
	 * REGISTRADORES UTILIZADOS PARA GUARDAR ALGUNS DADOS AO MOVER-SE PELOS NOS
	 * DA AST
	 */
	private String REGISTRADOR_NOME_VARIAVEL;

	private String REGISTRADOR_TIPO_VARIAVEL;

	private int REGISTRADOR_VALOR_TIPO_VARIAVEL;

	private boolean REGISTRADOR_EH_ARRAY = false;

	private Info REGISTRADOR_RESULTADO_EXP = null;

	public PrintVisitor() {
		this.tabelaDeSimbolos.put("global", new Info(PROGRAMA, numeroDoEscolpo,
				null));
	}

	public void initOffset(int i) {
		offset = i;
	}

	public int newOffset(int kind, int length) {

		int result = offset;

		if (kind == LISTA_PARAMETROS || kind == LISTA_VARIAVEIS) {

			offset += 1;

		} else {
			offset += length; // length in words
		}
		return result;
	}

	private void iniciarEscopo() {

		numeroDoEscolpo++;

		nomesLocais.push("$$$");

		System.out
				.println(" ===> DEBUG: ENTREI NO NOVO ESCOLPO, MEU NUM ESCOLPO EH : "
						+ numeroDoEscolpo);

		System.out.println(" ===> DEBUG : SITUACAO Da PILHA !! "
				+ nomesLocais.toString());
	}

	private void encerrarEscopo() {

		String topoDaPilha = nomesLocais.pop();

		while (!topoDaPilha.equals("$$$")) {
			System.out.println(" ===> TINHA " + topoDaPilha + " na pilha");

			topoDaPilha = nomesLocais.pop();

			System.out.println(" ===> AGORA TIREI " + topoDaPilha + "da pilha");
		}

		numeroDoEscolpo--;

		System.out
				.println(" ===> DEBUG: SAINDO DO ESCOLPO, MEU NUM ESCOLPO EH:"
						+ numeroDoEscolpo);

		System.out.println(" ===> DEBUG: SITUACAO Da PILHA !! "
				+ nomesLocais.toString());
	}

	private void novaEntrarda(String nome, Info info) {

		nomesLocais.push(nome);

		System.out
				.println("NOVO ELEMENTO NA PILHA + " + nomesLocais.toString());

		Info informacaoAnterior = tabelaDeSimbolos.put(nome, info);

		if (informacaoAnterior != null) {
			if (informacaoAnterior.numeroDoEscolpo == info.numeroDoEscolpo
					&& informacaoAnterior.natureza == info.natureza)
				System.out.println("\u001B[31m ERRO DECLAROU" + info.natureza
						+ " DUAS VEZES COM O MESMO NOME!");

		}

	}

	private Info lookUpTabelaDeSimbolos(String nome) throws Exception {
		Info info = tabelaDeSimbolos.get(nome);

		if (info == null)

			throw new Exception(
					"\u001B[31m ERRRO! SIMBOLO NAO DECLARADO ANTERIORMENTE");

		return info;
	}

	/* Implementacao dos metodos visit */
	@Override
	public void visit(ProgramaNormal pn) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Programa Normal");
		pn.bloco.accept(this);
	}

	@Override
	public void visit(Programa p) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Programa");
	}

	@Override
	public void visit(Programa_naoVazio d) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando programa nao vazio");

		initOffset(-1);

		d.getDeclaracao().accept(this);

		initOffset(-1);

		d.getPrograma().accept(this);

	}

	@Override
	public void visit(ProgramaVazio pv) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando programa vazio");
	}

	@Override
	public void visit(Bloco bloco) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando bloco");
		// for(Comando c : bloco.comandos)
		// c.accept(this);
		//
		// for( Var v :bloco.varList)
		// v.accept(this);
	}

	@Override
	public void visit(Comando comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando comando ");

	}

	@Override
	public void visit(DeclFuncao decFunc) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando funcao [" + decFunc.ident + "]");
		decFunc.bloco.accept(this);
	}

	@Override
	public void visit(Var var) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando variavel ");
		// var.tipo.accept(this);
	}

	@Override
	public void visit(Tipo tipo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando tipo");

	}

	@Override
	public void visit(TipoChar tc) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo char");

	}

	@Override
	public void visit(TipoFloat tf) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo float");

	}

	@Override
	public void visit(TipoInt ti) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo int");
		// get ( num do escolpo + 1) pq o anterior eh sempre $$$ marcando o
		// inicio do escolpo!

	}

	@Override
	public void visit(Exop exop) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Expop");

	}

	@Override
	public void visit(Exp exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando exp");

	}

	@Override
	public void visit(ExpFloat expf) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando expf: " + expf.getValorF());
		expf.setFlag(FLOAT);
		expf.setValParentFloat(expf.getValorF());
	}

	@Override
	public void visit(ExpHexa exph) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando exph:");
	}

	@Override
	public void visit(ExpInt expi) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando expi: " + expi.getValorI());
		expi.setFlag(INT);
		expi.setParentValorInt(expi.getValorI());
	}

	@Override
	public void visit(ExpString visit) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando exps: " + visit.getValorStr());
	}

	@Override
	public void visit(Parametro p) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando parametro: ");

	}

	@Override
	public void visit(ExpressaoBinaria visit) throws Exception {
		// TODO Auto-generated method stub
				System.out
						.println("visitando Expressao_binaria: " + visit.toString(""));
				// p.tipo.accept(this);
				System.out.println("operador " + visit.getOperadorString());
				visit.getExp_esq().accept(this);
				visit.getExp_dir().accept(this);

				
				if (visit.getExp_esq().getFlag() == visit.getExp_dir().getFlag())
					System.out.println("tipos iguasi das expressoes");

				int auxIntEsq, auxIntDir;
				auxIntEsq = auxIntDir = 0;

				float auxFloatEsq, auxFloatDir;
				auxFloatEsq = auxFloatDir = 0;

				int flagLocal = 0;
					
				
					if((visit.getOperadorString().equals("||"))||(visit.getOperadorString().equals("&&"))){
						
						if( !(visit.getExp_esq().getFlag()==INT) && !(visit.getExp_dir().getFlag() == INT))
							throw new Exception("nao eh um booleano");
						
						//ta faltando testar se a esq igual a 0 ou igual 1 e se a direita \E9 igual a 0 ou igual 1
						
//						if( !(( (visit.getExp_esq().getValorInt()<=VERDADEIRO) &&
//								(visit.getExp_esq().getValorInt()>=FALSO)) 
//												||
//								(visit.getExp_dir().getValorInt()<=VERDADEIRO &&
//								(visit.getExp_dir().getValorInt()>=FALSO)))) 
//								
//							throw new Exception("n\E3o \E9 um booleano");
					}
					
				
				
					if (visit.getExp_esq().getFlag() == INT) {
						if (visit.getExp_dir().getFlag() == INT) {
							visit.setFlag(INT);
							flagLocal = INT;
							auxIntEsq = (int) visit.getExp_esq().getValorInt();
							auxIntDir = (int) visit.getExp_dir().getValorInt();

						} else if (visit.getExp_dir().getFlag() == FLOAT) {

							auxFloatEsq = (float) visit.getExp_esq().getValorInt();
							auxFloatDir = visit.getExp_dir().getValFloat();
							flagLocal = FLOAT;

							System.out.println("somando valor "
									+ visit.getExp_esq().getValorInt()
									+ " valor float "
									+ visit.getExp_dir().getValFloat()
									+ "resultado "
									+ ((float) visit.getExp_esq().getValorInt() + visit
											.getExp_dir().getValFloat()));

						}

					} else if (visit.getExp_esq().getFlag() == FLOAT) {
						float auxf = 0;
						flagLocal = FLOAT;
						if (visit.getExp_dir().getFlag() == INT) {
							auxFloatDir = (float) visit.getExp_dir().getValorInt();
							auxf = (float) visit.getExp_dir().getValorInt();
						} else if (visit.getExp_dir().getFlag() == FLOAT)
							auxf = visit.getExp_dir().getValFloat();

						auxFloatEsq = visit.getExp_esq().getValFloat();
						auxFloatDir = auxf;
						visit.setFlag(FLOAT);
					}


				int operador = 0;
				
				System.out.println("valor flag "+flagLocal);
				
				if (visit.getOperadorString().equals("+"))
					operador = SOMA;
				else
				if(visit.getOperadorString().equals("*")){
					operador = MULT;
					System.out.println("entour na operacao  "+MULT+" at "+operador);
				}else	
				if(visit.getOperadorString().equals("-"))
					operador =SUB;
				else
				if(visit.getOperadorString().equals("/"))
					operador =DIV;
				else
				if(visit.getOperadorString().equals("=="))	
					operador =IGUAL;
				else
				if(visit.getOperadorString().equals(">"))
					operador = MAIORQ;
				else
				if(visit.getOperadorString().equals("<"))	
					operador = MENORQ;
				else
				if(visit.getOperadorString().equals(">="))
					operador =MAIORIG;
				else
				if(visit.getOperadorString().equals("<=")){
					operador =MENORIG;
					System.out.println("entrou no operado menorig");
				}else
				if(visit.getOperadorString().equals("||"))
					operador = OR;
				else
				if(visit.getOperadorString().equals("&&"))
					operador = AND;
					
				System.out.println("val operador: "+operador);	
					
				switch (operador) {
				case SOMA:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(INT);
						visit.setParentValorInt((int)(auxIntDir + auxIntEsq));
						System.out.println("passou no switch soma int " + " soma int "
								+ (auxIntDir + auxIntEsq));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(FLOAT);
						System.out.println("valor float "+(auxFloatDir+auxFloatEsq));
						visit.setValParentFloat((float)(auxFloatDir+auxFloatEsq));
					break;	
					}
					
					default:
						break;
				
				}
				break;	
					
				case MULT:
					switch (flagLocal) {
					case INT: {

						visit.setFlag(INT);
						visit.setParentValorInt((int)(auxIntDir*auxIntEsq));
						System.out.println("passou no switch mult int " + " soma int "
								+ (auxIntDir*auxIntEsq));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(FLOAT);
						System.out.println("valor float "+(auxFloatDir*auxFloatEsq));
						visit.setValParentFloat((float)(auxFloatDir*auxFloatEsq));
					}
					
					default:
						break;
				
				}
					break;
					
				case SUB:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(INT);
						visit.setParentValorInt((int)(  auxIntEsq-auxIntDir));
						System.out.println("passou no switch sub int " + " "
								+ ( auxIntEsq-auxIntDir));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(FLOAT);
						System.out.println("valor float "+(auxFloatEsq-auxFloatDir));
						visit.setValParentFloat((float)(auxFloatEsq-auxFloatDir));
					break;	
					}
					
					default:
						break;
				
				}
					break;
				case DIV:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(INT);
						visit.setParentValorInt((auxIntEsq/auxIntDir));
						System.out.println("passou no switch div int " + " "
							+ (int)((auxIntEsq/auxIntDir)) +"valro esq "+auxIntEsq+"valor dir "+auxIntDir);
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(FLOAT);
						System.out.println("valor float "+(auxFloatEsq/auxFloatDir));
						visit.setValParentFloat((float)(auxFloatEsq/auxFloatDir));
					break;	
					}
					
					default:
						break;
				
				}
				break;	
			
				case IGUAL:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(IGUAL);
						visit.setParentValorInt(((auxIntDir == auxIntEsq) ? VERDADEIRO : FALSO));
						System.out.println("passou no switch igual int " + "int "
								+ ((auxIntDir == auxIntEsq) ? VERDADEIRO : FALSO));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(IGUAL);
						System.out.println("igual float "+((auxFloatDir==auxFloatEsq) ? VERDADEIRO : FALSO));
						visit.setParentValorInt(((auxFloatDir==auxFloatEsq) ? VERDADEIRO : FALSO));
					break;	
					}
				  
					default:
						break;
				
				}
				break;	
				
				case MAIORQ:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(MAIORQ);
						visit.setParentValorInt(((auxIntEsq > auxIntDir) ? VERDADEIRO : FALSO));
						System.out.println("passou no switch maiorq " + "int "
								+ ((auxIntDir > auxIntEsq) ? VERDADEIRO : FALSO));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(MAIORQ);
						System.out.println("maiorq float "+((auxFloatEsq > auxFloatDir) ? VERDADEIRO : FALSO));
						visit.setParentValorInt(((auxFloatEsq > auxFloatDir) ? VERDADEIRO : FALSO));
					break;	
					}
				  
					default:
						break;
				
				}
					
					break;	
					
				case MENORQ:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(MENORQ);
						visit.setParentValorInt(((auxIntEsq < auxIntDir) ? VERDADEIRO : FALSO));
						System.out.println("passou no switch menorq " + "int "
								+ ((auxIntEsq < auxIntDir) ? VERDADEIRO : FALSO));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(MENORQ);
						System.out.println("menorq float "+((auxFloatEsq < auxFloatDir) ? VERDADEIRO : FALSO));
						visit.setParentValorInt(((auxFloatEsq < auxFloatDir) ? VERDADEIRO : FALSO));
					break;	
					}
				  
					default:
						break;
				
				}
				
					break;	
					
				case MAIORIG:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(MAIORIG);
						visit.setParentValorInt(((auxIntEsq >= auxIntDir) ? VERDADEIRO : FALSO));
						System.out.println("passou no switch maior e igual " + "int "
								+ ((auxIntEsq >=auxIntDir ) ? VERDADEIRO : FALSO));
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(MAIORIG);
						System.out.println("maior e igual float "+((auxFloatEsq >= auxFloatDir) ? VERDADEIRO : FALSO));
						visit.setParentValorInt(((auxFloatEsq >= auxFloatDir) ? VERDADEIRO : FALSO));
					break;	
					}
				  
					default:
						break;
				
				}
					
				break;	
					
				case MENORIG:

					switch (flagLocal) {
					case INT: {

						visit.setFlag(MENORIG);
						visit.setParentValorInt(((auxIntEsq <= auxIntDir) ? VERDADEIRO : FALSO));
						System.out.println("passou no switch menor e igual " + "int "
								+ ((auxIntEsq <=auxIntDir ) ? VERDADEIRO : FALSO));
						System.out.println("debug menorig");
					break;	
					}
					
					case FLOAT:{
						
						visit.setFlag(MENORIG);
						System.out.println("menor e igual float "+((auxFloatEsq <= auxFloatDir) ? VERDADEIRO : FALSO));
						visit.setParentValorInt(((auxFloatEsq <= auxFloatDir) ? VERDADEIRO : FALSO));
					break;	
					}
				  
					default:
						break;
				
				}
					
					break;
			
				case OR:{
					 
					System.out.println("operacao ou operador "+operador);
					visit.setFlag(OR);
					System.out.println("valor da expresaao ou" +((visit.getExp_esq().getValorInt()==VERDADEIRO)||(visit.getExp_dir().getValorInt()==VERDADEIRO)?VERDADEIRO:FALSO));
					visit.setParentValorInt(((visit.getExp_esq().getValorInt()==VERDADEIRO)||(visit.getExp_dir().getValorInt()==VERDADEIRO)?VERDADEIRO:FALSO));
					
			
			
					}
					
					break;
					
				case AND:{
					 
					System.out.println("operacao and operador "+operador);
					visit.setFlag(AND);
					System.out.println("valor da expresaao and " +((visit.getExp_esq().getValorInt()==VERDADEIRO)&&(visit.getExp_dir().getValorInt()==VERDADEIRO)?VERDADEIRO:FALSO));
					visit.setParentValorInt(((visit.getExp_esq().getValorInt()==VERDADEIRO)&&(visit.getExp_dir().getValorInt()==VERDADEIRO)?VERDADEIRO:FALSO));
					
			
			
					}	
					
					
				default:
				break;
				
			}

	}

	@Override
	public void visit(VariavelID var) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando VariavelID: " + var.toString(""));

		// chega se a variavel existe no escopo atual ou no global

		var.setParentId(var.getId());

	}

	@Override
	public void visit(VariavelColchetes var) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Vaiavel Colchete" + var.toString(" "));
		var.getVar().accept(this);
		this.guardarTipoArray();
		var.getExp().accept(this);

	}

	/* alteracoes isaac */
	@Override
	public void visit(ComandoAtribuicao comando) throws Exception {
		// TODO Auto-generated method stub
				System.out.println("visitando Comando de atribuicao: "
						+ comando.toString());
				comando.getVar().accept(this);

				String local = comando.getVar().getId();

				System.out.println("variavel resolvida =>" + comando.getVar().getId());

				// Info info = lookUpTabelaDeSimbolos(local);

				String aux = nomesLocais.get(numeroDoEscolpo + 1);

				Info var = tabelaDeSimbolos.get(aux).listaDeVariaveis.get(local);

				if (var == null)
					var = lookUpTabelaDeSimbolos(local);

				comando.getExp().accept(this);

				// analisa se o tipo da variavel \E9 igual ao tipo do valor
				if (var.tipo == INT) {
					if (var.tipo != comando.getExp().getFlag())
						throw new Exception("tipo da variavel incompativel");
					else
						var.setValorInt(comando.getExp().getValorInt());

				} else if (var.tipo == FLOAT)
					if (comando.getExp().getFlag() == INT) { // aki ja se sabe ki a
																// variavel \E9 do tipo
																// float, agora analisa
																// se o outro lado \E9 um
																// int p/ faze um
																// casting
						float auxFloat = (float) comando.getExp().getValorInt();
						var.setValorFloat(auxFloat);
						
						System.out.println("o val da var e "+var.getValorFloat());
					} else if (var.tipo != comando.getExp().getFlag())
						throw new Exception("tipo da variavel incompativel");
					else {
						var.setValorFloat(comando.getExp().getValFloat());
						System.out.println("imprimindo valor var: "
								+ comando.getExp().getValFloat());
					}

				System.out.println("xxxx : " + comando.getExp().getFlag() + " aaa "
						+ var.getValorInt());

				System.out.println("tttt " + comando.getExp().getValFloat());
	}

	@Override
	public void visit(Chamada chamada) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Chamada" + chamada.toString());

	}

	@Override
	public void visit(ListaExp listaExp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lita exp");
	}

	@Override
	public void visit(ListaExp_NoExp listaExp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lista exp com uma exp:");

	}

	@Override
	public void visit(ListaExp_VariasExp listaExp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lista exp varias exp:");
		
		listaExp.getExp().accept(this);
		int tamanho = (this.variavel_ehArray())? 1 : 0;
		ListaDeParametrosDeclaradas.add(new Info(PARAMETRO, listaExp.getExp().getFlag(), numeroDoEscolpo, tamanho));
		
		listaExp.getLista().accept(this);
	}

	@Override
	public void visit(ListaExp_VariasExp_Imp listaExp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lista exp Imp:");

	}

	@Override
	public void visit(ListaExp_VariasExp_Imp_Vazio listaExp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lista exp ImpVazio:");

	}

	@Override
	public void visit(ListaExp_VariasExp_Imp_NotVazio listaExp)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando lista exp Imp:");

		listaExp.getExp().accept(this); // resolve o tipo da exp
		
		int tamanho = (this.variavel_ehArray())? 1 : 0;
		ListaDeParametrosDeclaradas.add(new Info(PARAMETRO, listaExp.getExp().getFlag(), numeroDoEscolpo, tamanho));
		
		
		listaExp.getLista().accept(this);

	}

	@Override
	public void visit(ComandoChamada comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando chamada:");
		comando.getChamada().accept(this);
		 

	}

	@Override
	public void visit(ComandoWhile comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando while:");
		comando.getExp().accept(this);
		comando.getComando().accept(this);
	}

	@Override
	public void visit(ComandoIf_semElse comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando IF SEM else:");
		comando.getExp().accept(this);
		comando.getComando().accept(this);
	}

	@Override
	public void visit(ComandoIf_ComElse comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando IF COM else:");

		comando.getExp().accept(this);
		comando.getCmd1().accept(this);
		comando.getCmd2().accept(this);

	}

	@Override
	public void visit(ComandoRetorno comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando Retorno :");

		comando.getExop().accept(this);

		String nomeFuncaoDoEscopo = this.nomesLocais
				.get(this.numeroDoEscolpo + 1);

		Info funcaoDoEscopo = this.tabelaDeSimbolos.get(nomeFuncaoDoEscopo);

		System.out.println(" ===========> TIPO DE RETORNO DA EXP   = "
				+ comando.getExop().getTipoDaExp());
		System.out.println("TIPO DE RETORNO ESPERADO = " + funcaoDoEscopo.tipo);
		if (comando.getExop().getTipoDaExp() != funcaoDoEscopo.tipo )

			throw new Exception("Tipo de retono esperado para a funcao "
					+ nomeFuncaoDoEscopo + " esta errado.");
	}

	@Override
	public void visit(Exop_Vazio exop) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando Exop vazip:");

		exop.setTipoDaExp(VOID);
		//exop.setTamanhoDaExp(0);
	}

	@Override
	public void visit(Exop_exp exop) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Comando Exop com exp:");

		exop.getExp().accept(this);

		exop.setTipoDaExp(exop.getExp().getFlag());
		

	}

	@Override
	public void visit(ListaComandos comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lista De Comandos:");
	}

	@Override
	public void visit(ListaComandos_Vazia comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lista Vazia De Comandos:");
	}

	@Override
	public void visit(ListaComandos_Imp_NaoVazia comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lista Comandos Nao Vazia:");
		comando.getComando().accept(this);
		comando.getListaComandos().accept(this);
	}

	@Override
	public void visit(TipoBase tipo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando tipoBase:");

		;
	}

	@Override
	public void visit(TipoBaseInt tipo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando tipoBaseInt:");
		System.out.println("");
		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo base int");

		tipo.setTipoVar(INT);

	}

	@Override
	public void visit(TipoBaseChar tipo) throws Exception {
		// TODO Auto-generated method stub System.out.println("");
		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo Base char");
		// get ( num do escolpo + 1) pq o anterior eh sempre $$$ marcando o
		// inicio do escolpo!

		tipo.setTipoVar(CHAR);

	}

	@Override
	public void visit(TipoBaseFloat tipo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando tipoBaseFloat:");
		System.out.println("");
		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando tipo base float");
		// get ( num do escolpo + 1) pq o anterior eh sempre $$$ marcando o
		// inicio do escolpo!

		tipo.setTipoVar(FLOAT);

	}

	@Override
	public void visit(ListaDeclaracoes declaracoes) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lista De declaracoes:");

	}

	@Override
	public void visit(Tipo_TipoBase tipo_tp_base) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Tipo base com um tipo:");
		tipo_tp_base.getTipo_base().accept(this);

		tipo_tp_base.setElTipo((tipo_tp_base.getTipo_base().getTipoVar()));
	}

	@Override
	public void visit(TipoColchetes tipo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Tipo com colchetes:");
		this.guardarTipoArray();
		tipo.getTipo().accept(this);
		// coloca na memoria que o tipo utilizado eh um array
	}

	@Override
	public void visit(ListaNomes l) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lsta Nomes:");

	}

	@Override
	public void visit(ListaNomesID l) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Lsta Nomes ID:");

		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando LISTA NOMES ID");

		ListaDeVariaviesDeclaradas.add(l.getID());
		// int a

	}

	@Override
	public void visit(ListaNomes_VirgID l) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando LISTA NOMES VIRG ID");
		// get ( num do escolpo + 1) pq o anterior eh sempre $$$ marcando o
		// inicio do escolpo!

		l.getListaDeNomes().add(l.getID());

		ListaDeVariaviesDeclaradas.add(l.getID());

		l.getList().accept(this);

		// int a, b
	}

	@Override
	public void visit(DeclaracaoVariavel declaracao) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Declaracao Variavel:");
		// adiciona nova declaracao ao escopo
		declaracao.getTipo().accept(this); // O tipo sera setado apos o accept

		declaracao.setTipoDaDeclaracao(declaracao.getTipo().getElTipo());

		declaracao.getLista_nomes().accept(this);

		int tamanho = (this.variavel_ehArray()) ? 1 : 0; // 0 para varivaies
															// simples

		boolean global = (this.numeroDoEscolpo < 0) ? true : false; // -1 eh o
																	// escopo
																	// global

		for (String nomeVar : ListaDeVariaviesDeclaradas) {

			addVariavelAoEscopo(global, nomeVar,
					new Info(VARIAVEL, declaracao.getTipoDaDeclaracao(),
							this.numeroDoEscolpo, tamanho));

		}

		System.out.println("DEPOIS DA DEVLARACAO: "
				+ this.tabelaDeSimbolos.get("global").toString());

		ListaDeVariaviesDeclaradas.clear();
	}

	/* adiciona a variavel ao escopo correto */
	private void addVariavelAoEscopo(boolean global, String nomeVar, Info var)
			throws Exception {
		if (global) {
			Info info = tabelaDeSimbolos.get("global");
			// info = info.listaDeVariaveis.get(nomeVar);

			if (info.listaDeVariaveis.put(nomeVar, var) != null)
				throw new Exception("Variavel com o mesmo nome \"" + nomeVar
						+ "\" declarada previamente");

			this.tabelaDeSimbolos.put("global", info);

			info = null;
		}

		else {
			String nomeFuncaoDoEscopo = this.nomesLocais
					.get(this.numeroDoEscolpo + 1);

			Info funcao = tabelaDeSimbolos.get(nomeFuncaoDoEscopo);

			if (funcao.listaDeVariaveis.put(nomeVar, var) != null)
				throw new Exception(
						"Variavel com o mesmo nome ja existe neste contexto!!");

			this.tabelaDeSimbolos.put(nomeFuncaoDoEscopo, funcao);

		}
	}

	@Override
	public void visit(ListaDeclaracoes_Vazio declaracao) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Lista de declaracoes vazia");

	}

	@Override
	public void visit(LitaDeclaracoes_NaoVazia declaracao) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Lista de declaracoes nao vazia");
		declaracao.getDeclaracao().accept(this);
		declaracao.getListaDeclaracoes().accept(this);

	}

	@Override
	public void visit(BlocoPtvirg bloco) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando bloco ptvirg");

	}

	@Override
	public void visit(Bloco_Imp bloco) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando bloco imp");
		bloco.getListaDeclaracoes().accept(this);
		bloco.getListaComandos().accept(this);
	}

	@Override
	public void visit(ListaParametros lista) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando lista de parametros");
	}

	@Override
	public void visit(ListaParametros_Vazia lista) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando lista de parametros vazia");
	}

	@Override
	public void visit(ListaParametros_Imp lista) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando lista de parametros imp");

		lista.getParametro().accept(this);

		int tamanho = (this.variavel_ehArray()) ? 1 : 0;

		ListaDeParametrosDeclaradas.add(new Info(PARAMETRO, lista
				.getParametro().getTipoDoParametro(), this.numeroDoEscolpo,
				tamanho, lista.getParametro().getParentId()));

		lista.getListaParametros().accept(this);
	}

	@Override
	public void visit(Parametros p) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando parametros");
	}

	@Override
	public void visit(Parametros_Vazio p) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando parametros VAZIO");
	}

	@Override
	public void visit(Parametros_Imp p) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * USO O RESGAtAR VARIAVEL POIS NAO ESTA SENDO POSSIVEL ReSGATAR O VALOR
		 * DO ID DO PARAMETRO
		 */
		System.out.println("Visitando parametros imp");
		p.getParamatro().accept(this);

		int tamanho = (this.variavel_ehArray()) ? 1 : 0;

		ListaDeParametrosDeclaradas.add(new Info(PARAMETRO, this
				.resgatarValorDoTipo(), this.numeroDoEscolpo, tamanho, this
				.resgatarVariavel()));

		p.getListaParametros().accept(this);
	}

	@Override
	public void visit(DeclaracaoFuncao d) {
		// TODO Auto-generated method stub

		System.out.println("Visitando Declaracao funcao");

	}

	@Override
	public void visit(DeclaracaoFuncao_void d) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Declaracao funcao de retorno void 88");

		iniciarEscopo();

		novaEntrarda(d.getId(), new Info(FUNCAO_VOID, numeroDoEscolpo, null));

		Info funcaoDoEscopo = tabelaDeSimbolos.get(d.getId());
		
		funcaoDoEscopo.tipo = VOID; // usado para saber o tipo de retorno

		initOffset(-1);

		d.getParametros().accept(this);

		for (Info par : ListaDeParametrosDeclaradas) {

			funcaoDoEscopo.listaDeParametros.put(par.ID, par);
		}

		ListaDeParametrosDeclaradas.clear();

		Info f = this.tabelaDeSimbolos.put(d.getId(), funcaoDoEscopo); // atualiza
																		// na
																		// tabela

		initOffset(-1);

		d.getBloco().accept(this);

		encerrarEscopo();

	}

	@Override
	public void visit(DeclaracaoFuncao_Tipo d) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Declaracao funcao de retorno tip000009");

		iniciarEscopo();
		System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUU "   + this.tabelaDeSimbolos.toString());
		novaEntrarda(d.getId(), new Info(FUNCAO_TIPO, numeroDoEscolpo, null));
		System.out.println("ÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇÇ "   + this.tabelaDeSimbolos.toString());
		d.getTipo().accept(this);

		Info funcaoDoEscopo = tabelaDeSimbolos.get(d.getId());

		funcaoDoEscopo.tipo = d.getTipo().getElTipo(); // usado para saber o
														// tipo de retorno

		initOffset(-1);

		d.getParametros().accept(this);

		for (Info par : ListaDeParametrosDeclaradas) {

			funcaoDoEscopo.listaDeParametros.put(par.ID, par);
		}

		ListaDeParametrosDeclaradas.clear();

		this.tabelaDeSimbolos.put(d.getId(), funcaoDoEscopo); // atualiza na
																// tabela

		initOffset(-1);

		d.getBloco().accept(this);

		encerrarEscopo();
	}

	@Override
	public void visit(Declaracao d) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Visitando Declaracao abstrata");

	}

	@Override
	public void visit(Declaracao_decFuncao d) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Declaracao declaracao de funcao");

		d.getDeclaracaoFuncao().accept(this);

	}

	@Override
	public void visit(Declacracao_decVariavel d) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Declaracao declaracao de variavel");
		d.getDeclaracaoVariavel().accept(this);

	}

	@Override
	public void visit(Parametro_TipoId p) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" NUM ESCOLPO = " + this.numeroDoEscolpo
				+ " visitando parametro tipo id ==" + p.getID());
		// get ( num do escolpo + 1) pq o anterior eh sempre $$$ marcando o
		// inicio do escolpo, porra!

		p.getTipo().accept(this);

		System.out.println("OOOOO O TIPO + " + p.getTipo().getElTipo());
		p.setTipoDoParametro(p.getTipo().getElTipo());

		this.guardarVariavel(p.getID()); // para o uso da lista de parametros
											// quen ao consegue resgatar o valor
		this.guardarValorDoTipo(p.getTipoDoParametro());

		p.setParentId(p.getID());

	}

	@Override
	public void visit(Chamada_Imp chamada) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Visitando Chamada Imp");

		initOffset(-1);
		chamada.getListaExp().accept(this); // avaliar os parametros
		System.out.println(this.tabelaDeSimbolos.toString());
		Info funcaoChamada = this.lookUpTabelaDeSimbolos(chamada.getID());
		
		int i = 0;
		for (String nome : funcaoChamada.listaDeParametros.keySet()) {
			System.out.println("MY PAR LIST: " + nome );
			System.out.println("M CALL LIST: " +   ListaDeParametrosDeclaradas.get(i).tipo);
			Info var = funcaoChamada.listaDeParametros.get(nome);
			System.out.println("la lista : " + ListaDeParametrosDeclaradas.toString());
			if (var.tipo != ListaDeParametrosDeclaradas.get(i++).tipo  )
				throw new Exception("Parametros incompativeis!! " + nome + " na chamada " + chamada.getID());
			
		}
		
		chamada.setTipoDaChamada(funcaoChamada.tipo);
	}

	@Override
	public void visit(Exp_NotExp exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Exp_notExp");

		exp.getExp().accept(this);

	}

	@Override
	public void visit(Exp_New exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Exp_new");
		exp.getTipo().accept(this);
		exp.getExp().accept(this);

	}

	@Override
	public void visit(Exp_Sub exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Exp_Sub");
		exp.getExp().accept(this);

		// this.offset++; // contar a posicao dos parametros
		//
		// this.guardarTipoInfo(PARAMETRO, INVALIDO_COMO_PARAMETRO, 0);

	}

	@Override
	public void visit(Exp_Parenteses exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando Exp_Parenteses");
		
		exp.getExp().accept(this);

		/* programar como guardar o tipo da exp aqui DEPOIS */

		// this.offset++; // contar a posicao dos parametros
		//
		// this.guardarTipoInfo(PARAMETRO, INVALIDO_COMO_PARAMETRO, 0);

	}

	@Override
	public void visit(Exp_Var exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando exp_ var");
		
		exp.getVar().accept(this); // entra no no e salva o tipo da var na
									// memoria

		/* programar como guardar o tipo da variavel aqui */

		String nome_funcaoDoEscopo = this.nomesLocais.get(numeroDoEscolpo + 1);

		Info funcaoDoEscopo = this.tabelaDeSimbolos.get(nome_funcaoDoEscopo);

		Info var = funcaoDoEscopo.listaDeVariaveis.get(exp.getVar().getId());

		if (var != null) {
			exp.setFlag(var.tipo);
		}
		else {

			var = funcaoDoEscopo.listaDeParametros.get(exp.getVar().getId());
			System.out.println(var.toString());

			if (var != null){
				exp.setFlag(var.tipo);
			} else {
				funcaoDoEscopo = this.tabelaDeSimbolos.get("global");

				var = funcaoDoEscopo.listaDeVariaveis.get(exp.getVar().getId());

				if (var != null)
					exp.setFlag(var.tipo);
				else
					throw new Exception("Variavel \"" + exp.getVar().getId()
							+ "\" nao definida anteriormente");
			}

		}

	}

	@Override
	public void visit(Exp_Chamda exp) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando exp_ chamada");

		exp.getChamada().accept(this); // acessa chamada e guarda na memoria o
										// nome da chamada
		
		exp.setFlag(exp.getChamada().getTipoDaChamada());

		/* programar como guardar o retorno da funcao aqui */
	}

	@Override
	public void visit(ComandoBloco comando) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("visitando comando bloco");
		comando.getBloco().accept(this);
	}

	private void guardarVariavel(String s) {
		this.REGISTRADOR_NOME_VARIAVEL = s;
	}

	private String resgatarVariavel() {
		String retorno = this.REGISTRADOR_NOME_VARIAVEL;
		this.REGISTRADOR_NOME_VARIAVEL = null;
		return retorno;

	}

	private void guardarTipo(String s) {
		this.REGISTRADOR_TIPO_VARIAVEL = s;
	}

	private String resgatarTipo() {
		String retorno = this.REGISTRADOR_TIPO_VARIAVEL;
		this.REGISTRADOR_TIPO_VARIAVEL = null;
		return retorno;

	}

	private void guardarTipoArray() {
		this.REGISTRADOR_EH_ARRAY = true;
	}

	private boolean variavel_ehArray() {
		boolean eh_array = this.REGISTRADOR_EH_ARRAY;
		this.REGISTRADOR_EH_ARRAY = false;
		return eh_array;

	}

	private void guardarTipoInfo(int tipoInfo, int tipoExp, int tamanho) {
		this.REGISTRADOR_RESULTADO_EXP = new Info(tipoInfo, tipoExp,
				this.numeroDoEscolpo, tamanho);
	}

	private Info resgatarTipoInfo() {
		Info info = this.REGISTRADOR_RESULTADO_EXP;
		this.REGISTRADOR_RESULTADO_EXP = null;
		return info;

	}

	private void guardarValorDoTipo(int s) {
		this.REGISTRADOR_VALOR_TIPO_VARIAVEL = s;
	}

	private int resgatarValorDoTipo() {
		int retorno = this.REGISTRADOR_VALOR_TIPO_VARIAVEL;
		this.REGISTRADOR_VALOR_TIPO_VARIAVEL = AINDA_NAO_DEFINIDO;
		return retorno;

	}

}
