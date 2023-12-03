package src.ambientes;

import src.Ambiente;

public class Cozinha extends Ambiente {
  public Cozinha (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
    defineItens();
  }

  private void defineMoveis() {
    definirMoveis("BALCAO", "um balcao de tamanho medio");
    definirMoveis("ARMARIO", "um armario que contém algumas comidas enlatadas");
    definirMoveis("PIA", "uma pia de cozinha");
  }

  private void defineItens() {
    definirItensAmbiente("BATERIA", "uma bateria de 9V");
  }
}
