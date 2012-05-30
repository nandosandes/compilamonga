/*
 * Generated by classgen, version 1.5
 * 13/05/12 14:27
 */
package no;

public class ExpressaoBinaria extends Exp {

  private Exp exp_esq;
  private Operador operador;
  private Exp exp_dir;

  public ExpressaoBinaria (Exp exp_esq, Operador operador, Exp exp_dir) {
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

  public Operador getOperador() {
    return operador;
  }

  public void setOperador(Operador operador) {
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
      if (operador != null)
        buffer.append(operador.toString("  "+tab));
      else
        buffer.append(tab+"  null");
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
