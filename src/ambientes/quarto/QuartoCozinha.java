package src.ambientes.quarto;

import src.ambientes.Quarto;

public class QuartoCozinha extends Quarto {
  public QuartoCozinha (String nome, String descricao) {
    super(nome, descricao);

    defineItens();
  }

  private void defineItens() {
    definirItensAmbiente("TESOURA", "uma tesoura de costura");
    definirItensAmbiente("CASACO RASGADO", "Um casaco velho e rasgado");
  }
}
