package src.ambientes;

import src.Ambiente;

public class Garagem extends Ambiente {
  public Garagem (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
    defineItens();
  }

  private void defineMoveis() {
    definirMoveis("LIXEIRA", "uma lixeira grande de plastico com rodas");
    definirMoveis("PRATELEIRA", "uma prateleira muito empoeirada");
    definirMoveis("CARRO", "um carro de expedicao, ele parece estar muito danificado, nao ha tempo para conserta-lo");
  }

  private void defineItens() {
    definirItensAmbiente("BATERIA DE CARRO", "uma bateria de carro");
    definirItensAmbiente("FITA ISOLANTE PEQUENA", "uma pequena fita isolante de cor preta");
    definirItensAmbiente("CORDA", "uma corda muito extensa e resistente");

  }
}
