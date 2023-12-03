package src;

import java.util.ArrayList;

/**
 * Classe src.Movel - Essa classe define um movel do jogo.<br/>
 *
 * <br/>Um movel eh composto por nome e descricao e sua lista de itens.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public class Movel {
    private String nome;
    private String descricao;
    private ArrayList<Item> itens;

    /**
     * Cria um movel com a "descricao" e o "nome " passados. Inicialmente, ele
     * nao tem itens. "descricao" eh algo como "um armario velho"
     * @param nome O nome do movel
     * @param descricao A descricao do movel
     * @author Davi Pedro
     */
    public Movel(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        itens = new ArrayList<>();
    }

    /**
     * Retorna a descricao do movel
     * @return Descricao do movel
     * @author Davi Pedro
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Retorna o nome do movel
     * @return Nome do movel
     * @author Davi Pedro
     */
    public String getNome(){
        return nome;
    }

    /**
     * Adiciona um item a lista de itens
     * @author Davi Pedro
     */
    public void adicionarItem(Item item){
        itens.add(item);
    }

    /**
     * Retorna a informacao dos itens presentes no movel
     * @return Informacao dos itens no movel
     * @author Davi Pedro
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
     * @author Davi Pedro
     */
    public ArrayList<Item> transferirItens(){
        ArrayList<Item> itensTransferidos = new ArrayList<>(itens);
        itens.clear();
        return itensTransferidos;
    }


    @Override
    public String toString() {
        return getNome() + ": " + getDescricao() + "\n";
    }

}
