package src.ambientes.quarto;

import src.ambientes.Quarto;

public class QuartoBanheiro extends Quarto {
  public QuartoBanheiro (String nome, String descricao) {
    super(nome, descricao);

    defineItens();
  }

  private void defineItens() {
    definirItensAmbiente("LINHA", "um rolo pequeno de linha de costura");
    definirItensAmbiente("AGULHA", "uma agulha de custura, sempre util para remendar roupas");
  }
}
