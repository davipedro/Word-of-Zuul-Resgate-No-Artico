package src.ambientes;

import src.Ambiente;

public class Quarto extends Ambiente {
  public Quarto (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
  }

  private void defineMoveis() {
    definirMoveis("BELICHE", "uma beliche onde dorme dois dos seus colegas de expedicao");
    definirMoveis("GUARDA-ROUPA", "um guarda-roupa nao tao grande, seus colegas vivem reclamando disso");
    definirMoveis("MESA", "uma mesa pequena cheia de copos sujos de cafe");
  }
}
