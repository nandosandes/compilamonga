/*
 * Generated by classgen, version 1.5
 * 13/05/12 16:04
 */
package com.anaisador.no;

public class ExpressaoBinaria extends Exp {

  private Exp exp_esq;
  private int operador;
  private Exp exp_dir;

  public ExpressaoBinaria (Exp exp_esq, int operador, Exp exp_dir) {
    this.exp_esq = exp_esq;
    this.operador = operador;
    this.exp_dir = exp_dir;
  }

  public Exp getExp_esq() {
    return exp_esq;
  }

  public void setExp_esq(Exp exp_esq) {
    this.exp_esq = exp_esq;
  }

  public int getOperador() {
    return operador;
  }

  public void setOperador(int operador) {
    this.operador = operador;
  }

  public Exp getExp_dir() {
    return exp_dir;
  }

  public void setExp_dir(Exp exp_dir) {
    this.exp_dir = exp_dir;
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ExpressaoBinaria(\n");
      if (exp_esq != null)
        buffer.append(exp_esq.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+operador);
    buffer.append("\n");
      if (exp_dir != null)
        buffer.append(exp_dir.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ExpressaoBinaria]");
    return buffer.toString();
  }
}