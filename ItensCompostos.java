import java.util.ArrayList;
import java.util.Arrays;

/**
 * Enum ItenCompostos - Esse enum define e fornece os comandos dos itens que podem ser criados
 * no jogo.<br/>
 *
 * <br/>Um item composto eh um item formado por outros itens para que seja possivel concluir
 * os objetivos da historia do jogo. Ele é definido por nome, descricao e receita, sendo a receita
 * um array com os itens que estao espalhados pelo jogo.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public enum ItensCompostos {
    PICARETA_ESCALADA("PICARETA DE ESCALADA","Uma picareta de escalada improvisada, nao eh das melhores mas parece ser bem resistente",new ArrayList<>(Arrays.asList("FACA DE CHEF","CANO DE METAL"
            ,"CORDA"))),
    LANTERNA("LANTERNA","Uma lanterna improvisada, sera muito util para enxergar em meio a nevasca",new ArrayList<>(Arrays.asList("LAMPADA","BATERIA"
            ,"FITA ISOLANTE PEQUENA"))),
    AQUECEDOR_PORTATIL("AQUECEDOR PORTATIL","A temperatura do lado de fora eh congelante, isso ira ajudar a se manter aquecido",new ArrayList<>(Arrays.asList("FIO DE COBRE", "BATERIA DE CARRO",
            "FITA ISOLANTE GRANDE"))),
    TOUCA("TOUCA","Uma touca de retalhos, a costura esta bem feita, voce parece ter habilidade com isso",new ArrayList<>(Arrays.asList("CASACO RASGADO", "LINHA", "AGULHA", "TESOURA")));

    private ArrayList<String> receita;
    private String descricao;
    private String nome;

    ItensCompostos(String nome, String descricao, ArrayList<String> receita){
        this.receita = receita;
        this.descricao = descricao;
        this.nome = nome;
    }

    /**
     * @return Um array com os itens que sao necessarios para formar o item composto
     * @author Davi Pedro
     */
    public ArrayList<String> getReceita(){
        return receita;
    }

    /**
     * @return A descricao do item composto
     * @author Davi Pedro
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * @return O nome do item composto
     * @author Davi Pedro
     */
    public String getNome(){
        return nome;
    }

    /**
     * Retorna o item composto referente ao nome passado por parametro, caso o nome
     * passado nao seja encontrado, nao seja um item composto, eh retornado null.
     * @param nomeItem Nome do item que se deseja receber de retorno
     * @return Item composto definido no Enum ou null caso nao seja encontrado
     * @author Davi Pedro
     */
    public static ItensCompostos getItemComposto(String nomeItem){
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                return itemComposto;
            }
        }
        return null;
    }

    /**
     * @return Os itens compostos em string
     * @author Davi Pedro
     */
    public static String getItensCompostosString(){
        StringBuilder itens = new StringBuilder(" - ");
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            itens.append(itemComposto.getNome()).append(" - ");
        }
        return itens.toString();
    }
}
