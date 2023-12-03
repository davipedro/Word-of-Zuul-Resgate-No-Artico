package src.ambientes;

import src.Ambiente;

public class Oficina extends Ambiente {
  public Oficina (String nome, String descricao) {
    super(nome, descricao);

    defineMoveis();
    defineItens();
  }

  private void defineMoveis() {
    definirMoveis("ARMARIO", "armário de metal com duas portas e quatro gavetas ");
    definirMoveis("PRATELEIRA", "uma prateleira grande de metal");
    definirMoveis("SUCATA", "uma pilha de sucata de metal");
    definirMoveis("MALETA","uma maleta de ferramentas");
  }

  private void defineItens() {
    definirItensAmbiente("FIO DE COBRE", "um fio extenso de cobre");
    definirItensAmbiente("KIT DE SOLDA", "um kit de solda com ferro de solda e estanho");
    definirItensAmbiente("CANO DE METAL", "um cano de metal nao muito grande");
    definirItensAmbiente("FITA ISOLANTE GRANDE", "uma fita isolante grande de cor preta");

  }
}
