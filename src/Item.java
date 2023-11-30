package src;

/**
 * Classe src.Item - Essa classe define um item do jogo.<br/>
 *
 * <br/>Um item eh composto por nome e descricao.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public class Item {
    private String nome;
    private String descricao;

    /**
     * Cria um item com a 'descricao' e o 'nome' passados por parametro."
     * @param nome O nome do item
     * @param descricao A descricao do item
     * @author Davi Pedro
     */
    public Item(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * @return Nome do item
     * @author Davi Pedro
     */
    public String getNome(){
        return nome;
    }

    /**
     * @return Descricao do item
     * @author Davi Pedro
     */
    public String getDescricao(){
        return descricao; 
    }
}
