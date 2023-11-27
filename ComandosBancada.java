/**
 * Enum ComandosBancada - Esse enum define e fornece os comandos da bancada,
 * alem de um metodo para validar se um comando pertence aos comandos da bancada<br/>
 *
 * <br/>A bancada eh um objeto especial, que eh tratado como um comando, no qual
 * permite o jogador a compor seus itens necessarios para avancar no jogo, ela interage
 * com os itens compostos declarados no jogo e os comandos da bancada.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public enum ComandosBancada {

    BANCADA("bancada"),
    COMPOR("compor"),
    AJUDA("ajuda"),
    VOLTAR("voltar");

    private final String comando;

    ComandosBancada(String comando){
        this.comando = comando;
    }


    /**
     * Verifica se o comando fornecido eh um comando da bancada
     * @param comando Comando a ser verificado
     * @return Boolean que indica se o comando eh valido ou nao
     */
    public static boolean ehComandoBancada(String comando){
        for (ComandosBancada c : ComandosBancada.values()){
            if (comando.equalsIgnoreCase(c.comando)){
                return true;
            }
        }
        return false;
    }
}
