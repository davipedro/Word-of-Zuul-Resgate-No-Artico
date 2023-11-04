import java.util.HashMap;
import java.util.Set;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente 
{
    private String descricao;
    private HashMap<String, Ambiente> saidas;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */

    public void definirSaidas(String direcao, Ambiente vizinho){
        saidas.put(direcao, vizinho);
    }
    /**
     * Retorna a sala que é alcançada se sairmos desta
     * sala na direção "direção". Se não houver nenhuma sala nessa
     * direção, retorna nulo.
     */
    public Ambiente getSaida(String direcao){
        return saidas.get(direcao);
    }

    /**
     * Retorna uma descrição das saídas da sala,
     * por exemplo, "Saidas: norte".
     * @return Uma descrição das saídas disponíveis.
     */
    public String getDescricaoSaida(){
        String saidasString = "Saidas: ";
        Set<String> chaves = saidas.keySet();
        for (String saida : chaves) {
            saidasString += " " + saida;
        }
        return saidasString;
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Retorna uma descrição longa desse quarto, na forma:
     *      Voce esta no cantina
     *      Saidas: leste
     * @return Uma descrição do quarto, incluindo saídas.
     */
    public String getDescricaoLonga(){
        return "Voce esta " + descricao + ".\n" + getDescricaoSaida();
    }
}
