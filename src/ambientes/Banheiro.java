package src.ambientes;

import src.Ambiente;

public class Banheiro extends Ambiente {
  public Banheiro (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
    defineItens();
  }

  private void defineMoveis() {
    definirMoveis("ARMARIO", "um armario com itens de banheiro");

  }

  private void defineItens() {
    definirItensAmbiente("LAMPADA", "uma lampada de led reserva");  
  }
}
