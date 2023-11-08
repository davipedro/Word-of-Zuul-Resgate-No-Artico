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

    public void adicionarItem(Item item){
        itens.add(item);
    }

    /**
     * Retorna os itens presentes no movel
     * @return Itens no movel
     */
    public String getItens(){
        if (itens.isEmpty()) {
            return "Nao ha nada util aqui";
        }
        String itensString = "Os itens em " + getNome() + " são:\n"; 
        for (Item item : itens) {
            itensString += item + " ";
        }
        return itensString;
    }
}
