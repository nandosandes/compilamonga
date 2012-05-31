package com.analisador.sintatico;

import com.analisador.ast.no.Programa;
import com.analisador.ast.no.ProgramaVazio;
import com.analisador.ast.no.Programa_naoVazio;
import com.analisador.lexico.Lexer;
import com.analisador.semantico.PrintVisitor;



public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(args[0].toString());
		//com.analisador.sintatico.Scanner lexer = new com.analisador.sintatico.Scanner(new BufferedReader(new FileReader("teste.txt")));
				System.out.println(args[0]);
			    Lexer scanner = null;
			    try {
			      scanner = new Lexer( new java.io.FileReader(args[0]) );
			    }
			    catch (java.io.FileNotFoundException e) {
			      System.out.println("File not found : \""+args[0]+"\"");
			      System.exit(1);
			    }
			    catch (java.io.IOException e) {
			      System.out.println("Error opening file \""+args[0]+"\"");
			      System.exit(1);
			    }
			    catch (ArrayIndexOutOfBoundsException e) {
			      System.out.println("Usage : java Main <inputfile>");
			      System.exit(1);
			    }

			     parser p = new parser(scanner);
			     
			     Object result = p.parse().value;
			     
				 if (!p.getSintaxErros())
					 System.out.println(p.debug_parse());
				
				 System.out.println(result.toString());
				 
				 PrintVisitor visitorDeIdentificacao = new PrintVisitor();
				 
				 ((Programa) result).accept(visitorDeIdentificacao); 
	}
	
}










