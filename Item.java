public class Item {
    private String nome;
    private String descricao;

    /**
     * Cria um item com a "descricao" e o "nome " passados. "descricao" eh algo como "uma fita isolante"
     * @param nome O nome do item
     * @param descricao A descricao do item
     */
    public Item(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Retorna o nome do item
     * @return Nome do item
     */
    public String getNome(){
        return nome;
    }

    /**
     * Retorna a descricao do itemc
     * @return Descricao do item
     */
    public String getDescricao(){
        return descricao; 
    }

    @Override      // APAGAR DEPOIS APAGAR DEPOIS APAGAR DEPOIS APAGAR DEPOIS APAGAR DEPOIS APAGAR DEPOIS
    public String toString() {
        return getNome();
    }
}
