import java.util.ArrayList;

public class Movel {
    private String nome;
    private String descricao;
    private ArrayList<Item> itens;

    /**
     * Cria um movel com a "descricao" e o "nome " passados. Inicialmente, ele
     * nao tem itens. "descricao" eh algo como "um armario velho"
     * @param nome O nome do movel
     * @param descricao A descricao do movel
     */
    public Movel(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        itens = new ArrayList<>();
    }

    /**
     * Retorna a descricao do movel
     * @return Descricao do movel
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Retorna o nome do movel
     * @return Nome do movel
     */
    public String getNome(){
        return nome;
    }

    /**
     * Adiciona um item a lista de itens
     */
    public void adicionarItem(Item item){
        itens.add(item);
    }

    /**
     * Retorna a informacao dos itens presentes no movel
     * @return Informacao dos itens no movel
     */
    public String getItensDescricao(){
        if (itens.isEmpty()) {
            return null;
        }
        StringBuilder itensString = new StringBuilder("Os itens em " + getNome() + " são:\n");
        for (Item item : itens) {
            itensString.append(item.getNome()).append(": ").append(item.getDescricao());
        }
        return itensString.toString();
    }

    /**
     * Transfere os itens do movel para outro lugar
     * @return Itens do movel
     */
    public ArrayList<Item> transferirItens(){
        ArrayList<Item> itensTransferidos = new ArrayList<>(itens);
        itens.clear();
        return itensTransferidos;
    }
}
