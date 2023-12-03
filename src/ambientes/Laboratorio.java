package src.ambientes;

import src.Ambiente;

public class Laboratorio extends Ambiente {
  public Laboratorio (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
    defineItens();
  }

  private void defineMoveis() {
    definirMoveis("BANCADA", "uma bancada para poder manufaturar itens");
    definirMoveis("ARMARIO", "um armario grande");
    definirMoveis("PRATELEIRA", "uma prateleira grande de metal");
    definirMoveis("MESA", "uma mesa de madeira com uma gaveta entreaberta");
  }

  private void defineItens() {
    definirItensAmbiente("BATERIA", "uma bateria de 9V");
    definirItensAmbiente("PLACA DE CIRCUITO IMPRESSO", "uma placa com circuitos que conectam e controlam aparelhos");
  }
}
