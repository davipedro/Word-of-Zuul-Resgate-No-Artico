import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
    private Random aleatorio;
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private ArrayList<Movel> moveis;
    private ArrayList<Item> itensDisponiveisAmbiente;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha"
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
        moveis = new ArrayList<>();
        itensDisponiveisAmbiente = new ArrayList<>();
        aleatorio = new Random();
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
     * Define os moveis presentes no ambiente, cria e
     * e adiciona o movel na lista de itens do ambiente
     * @param nome Nome do movel
     * @param descricao Descricao do movel
     */
    public void definirMoveis(String nome, String descricao){
        Movel movel = new Movel(nome, descricao);
        moveis.add(movel);
    }

    /**
     * Retorna um inteiro aleatorio entre 0
     * e o tamanho do array de itens disponiveis
     * @return 
     */
    public int gerarIndiceAleatorio(){
        if (itensDisponiveisAmbiente.size() > 1) {
            return aleatorio.nextInt(itensDisponiveisAmbiente.size());
        }
        return 0;
    }

    /**
     * Distribui os itens para os móveis
     * aleatoriamente, cada movel recebe um item por vez ate
     * que nao tenha mais itens para distribuir, por fim remove o
     * item dos itens itens disponiveis
     * OBS: DEVE SER USADO APOS INSERIR TODOS OS ITENS DO QUARTO
     */
    public void definirItensMoveis(){
        int indiceItem;
        int indiceMovel=0;
        Item itemAleatorio;
        Movel movelAtual;
            while (!(itensDisponiveisAmbiente.isEmpty())) {
                movelAtual = moveis.get(indiceMovel);
                indiceItem = gerarIndiceAleatorio();
                itemAleatorio = itensDisponiveisAmbiente.get(indiceItem); 
                movelAtual.adicionarItem(itemAleatorio);
                itensDisponiveisAmbiente.remove(indiceItem);

                indiceMovel++;
                if (indiceMovel == moveis.size()) {
                    indiceMovel = 0;
                }
            }
    }
    
    /**
     * Adiciona um item no array de itens disponiveis
     * para os moveis
     * @param nome
     * @param descricao
     */
    public void definirItensAmbiente(String nome, String descricao){
        Item item = new Item(nome, descricao);
        itensDisponiveisAmbiente.add(item);
    }

    /**
     * Retorna os moveis presentes no ambiente
     * @return os moveis em String
     */
    public String getMoveisAmbiente(){
        String moveisString = "Voce avista os seguintes moveis:\n";
        for (Movel movel : moveis) {
            moveisString += movel.getNome() + " " + movel.getDescricao() + "\n";
        }
        return moveisString;
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
