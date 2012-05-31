package com.analisador.semantico;

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



public interface Visitor {
	

	public void visit(ProgramaNormal pn) throws Exception;
	
	public void visit(Programa p)throws Exception;
	
	public void visit(ProgramaVazio pv)throws Exception;
	
	public void visit(Programa_naoVazio pov)throws Exception;
	
	public void visit(Bloco bloco)throws Exception;
	
	public void visit(Comando comando)throws Exception;
	
	public void visit(ComandoAtribuicao comando) throws Exception;
	
	public void visit(DeclFuncao decFunc) throws Exception;
	
	public void visit(Var var)throws Exception;
	
	public void visit(VariavelID var)throws Exception;
	
	public void visit(VariavelColchetes var)throws Exception;
	
	public void visit(Tipo tipo)throws Exception;
	
	public void visit(TipoColchetes tipo)throws Exception;
	
	public void visit(TipoChar tc)throws Exception;
	
	public void visit(TipoFloat tf)throws Exception;
	
	public void visit(TipoInt ti)throws Exception;
	
	public void visit(Exop exop)throws Exception;
	
	public void visit(Exop_Vazio exop)throws Exception;
	
	public void visit(Exop_exp exop)throws Exception;
	
	public void visit(Exp exp)throws Exception;
	
	public void visit(ExpFloat expf)throws Exception;
	
	public void visit(ExpHexa exph)throws Exception;
	
	public void visit(ExpInt expi)throws Exception;
	
	public void visit(ExpString visit)throws Exception;
	
	public void visit(ExpressaoBinaria visit)throws Exception;
	
	public void visit(Parametro p)throws Exception;
	
	public void visit(Parametro_TipoId p)throws Exception;
	
	public void visit(Chamada chamada)throws Exception;
	
	public void visit(ListaExp listaExp)throws Exception;
	
	public void visit(ListaExp_NoExp listaExp)throws Exception;
	
	public void visit(ListaExp_VariasExp listaExp)throws Exception;
	
	public void visit(ListaExp_VariasExp_Imp listaExp)throws Exception;
	
	public void visit(ListaExp_VariasExp_Imp_Vazio listaExp)throws Exception;
	
	public void visit(ListaExp_VariasExp_Imp_NotVazio listaExp)throws Exception;
	
	public void visit(ComandoChamada comando)throws Exception;
	
	public void visit(ComandoWhile comando) throws Exception;
	
	public void visit(ComandoIf_semElse comando) throws Exception; 
	
	public void visit(ComandoIf_ComElse comando) throws Exception;
	
	public void visit(ComandoRetorno comando)throws Exception;
	
	public void visit(ComandoBloco bloco)throws Exception;
	
	public void visit(ListaComandos comando)throws Exception;
	
	public void visit(ListaComandos_Vazia comando)throws Exception;

	public void visit(ListaComandos_Imp_NaoVazia comando) throws Exception;
	
	public void visit(TipoBase tipo_base)throws Exception;
	
	public void visit(TipoBaseInt tipo_base)throws Exception;
	
	public void visit(TipoBaseChar tipo_base)throws Exception;
	
	public void visit(Tipo_TipoBase tipo_tp_base)throws Exception;
	
	public void visit(TipoBaseFloat tipo_base)throws Exception;
	
	public void visit(ListaNomes list)throws Exception;
	
	public void visit(ListaNomesID list)throws Exception;
	
	public void visit(ListaNomes_VirgID list)throws Exception;
	
	public void visit(ListaDeclaracoes declaracoes)throws Exception;
	
	public void visit(DeclaracaoVariavel declaracao) throws Exception;
	
	public void visit(ListaDeclaracoes_Vazio declaracao)throws Exception;

	public void visit(LitaDeclaracoes_NaoVazia declaracao)throws Exception;
	
	public void visit(BlocoPtvirg bloco)throws Exception;
	
	public void visit(Bloco_Imp bloco) throws Exception;
	
	public void visit(ListaParametros lista)throws Exception;
	
	public void visit(ListaParametros_Vazia lista)throws Exception;
	
	public void visit(ListaParametros_Imp lista)throws Exception;
	
	public void visit(Parametros p)throws Exception;
	
	public void visit(Parametros_Vazio p)throws Exception;
	
	public void visit(Parametros_Imp p)throws Exception;
	
	public void visit(DeclaracaoFuncao d)throws Exception;
	
	public void visit(DeclaracaoFuncao_void d)throws Exception;
	
	public void visit(DeclaracaoFuncao_Tipo d)throws Exception;
	
	public void visit(Declaracao declaracao)throws Exception;
	
	public void visit(Declaracao_decFuncao declaracao)throws Exception;
	
	public void visit(Declacracao_decVariavel declaracao)throws Exception;
	
	public void visit(Chamada_Imp chamada)throws Exception;
	
	public void visit(Exp_NotExp exp)throws Exception;
	
	public void visit(Exp_New exp)throws Exception;
	
	public void visit(Exp_Sub exp)throws Exception;
	
	public void visit(Exp_Parenteses exp)throws Exception;
	
	public void visit(Exp_Var exp)throws Exception;
	
	public void visit(Exp_Chamda exp)throws Exception;
}

