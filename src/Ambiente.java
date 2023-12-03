package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Classe src.Ambiente - representa um ambiente do jogo.<br/>
 *
 * <br/>Modificada do jogo "World of Zuul": um jogo de aventura muito simples, baseado em texto.<br/>
 *
 * <br/>Um "src.Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao. O ambiente tambem possui moveis e armazena os itens disponiveis
 * a serem distribuidos aleatoriamente, dentro do ambiente entre os moveis, ao iniciar a aplicacao.
 *
 * @author  Davi Pedro - Base: Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2023.12.03 - 2011.07.31 (2016.02.01)
 */
public class Ambiente 
{
    private Random aleatorio;
    private String nome;
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private ArrayList<Movel> moveis;
    private ArrayList<Item> itensDisponiveisAmbiente;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha"
     * @param descricao A descricao do ambiente.
     * @author Davi Pedro
     */
    public Ambiente(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        saidas = new HashMap<>();
        moveis = new ArrayList<>();
        itensDisponiveisAmbiente = new ArrayList<>();
        aleatorio = new Random();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param direcao A direcao da saida
     * @param vizinho src.Ambiente vizinho, no qual sera acessado pela saida
     * @author Davi Pedro
     */
    public void definirSaidas(String direcao, Ambiente vizinho){
        saidas.put(direcao, vizinho);
    }

    /**
     * Define os moveis presentes no ambiente, cria e
     * e adiciona o movel na lista de itens do ambiente
     * @param nome Nome do movel
     * @param descricao Descricao do movel
     * @author Davi Pedro
     */
    public void definirMoveis(String nome, String descricao){
        Movel movel = new Movel(nome, descricao);
        moveis.add(movel);
    }

    /**
     * Retorna um inteiro aleatorio entre 0
     * e o tamanho do array de itens disponiveis
     * @return Numero aleatorio ou 0 se o ambiente possui apenas um item.
     * @author Davi Pedro
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
     * que nao ha mais itens para distribuir, por fim remove o
     * item dos itens disponiveis <br/><br/>
     * Nota: deve ser usado apos inserir todos os itens do quarto
     * @author Davi Pedro
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
     * @param nome Nome do item
     * @param descricao Descricao do item
     * @author Davi Pedro
     */
    public void definirItensAmbiente(String nome, String descricao){
        Item item = new Item(nome, descricao);
        itensDisponiveisAmbiente.add(item);
    }

    /**
     * @return Moveis presentes no ambiente
     * @author Davi Pedro
     */
    public String getMoveisAmbiente(){
        if (moveis.isEmpty()){
            return """ 
                    =============================================
                    (Nao ha moveis nesse ambiente para vasculhar)
                    =============================================
                    """;
        } else {
            StringBuilder moveisString = new StringBuilder("""
                    ========================================
                    Voce avista os seguintes moveis:
                    ========================================
                    """);
            for (Movel movel : moveis) {
                moveisString.append(movel.getNome()).append(": ").append(movel.getDescricao()).append("\n");
            }
            moveisString.append("========================================");
            return moveisString.toString();
        }
    }

    /**
     * @return O nome do ambiente
     * @author Davi Pedro
     */
    public String getNome(){
        return nome;
    }

    /**
     * Transfere os itens do movel
     * @param movel Nome do movel
     * @return Array de itens transferidos
     * @author Davi Pedro
     */
    public ArrayList<Item> transferirItensMovel(String movel){
        ArrayList<Item> itensTransferidos = new ArrayList<>();

        for (Movel m : moveis){
            if (movel.equalsIgnoreCase(m.getNome().toLowerCase())){
                itensTransferidos = m.transferirItens();
            }
        }
        return itensTransferidos;
    }

    /**
     * @param movel Nome do movel
     * @return Descricao dos itens que estao no movel
     * @author Davi Pedro
     */
    public String getDescricaoItensMovel(String movel){
        for (Movel m : moveis){
            if (movel.equalsIgnoreCase(m.getNome().toLowerCase())){
                return m.getItensDescricao();
            }
        }
        return null;
    }

    /**
     * Verifica que o movel existe no ambiente
     * @param movel Nome do movel
     * @return Boolean
     * @author Davi Pedro
     */
    public boolean validaMovel(String movel){
        for (Movel m : moveis){
            if (movel.equalsIgnoreCase(m.getNome().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a sala que é alcançada se sairmos desta
     * sala na direção "direção". Se não houver nenhuma sala nessa
     * direção, retorna nulo.
     * @param direcao Direcao que deseja consultar as saidas
     * @return Saida disponivel
     * @author Davi Pedro
     */
    public Ambiente getSaida(String direcao){
        return saidas.get(direcao);
    }

    /**
     * @return Uma descrição das saídas disponíveis.
     * @author Davi Pedro
     */
    public String getDescricaoSaida(){
        StringBuilder saidasString = new StringBuilder("Saidas:");
        Set<String> chaves = saidas.keySet();
        for (String saida : chaves) {
            saidasString.append(" ").append(saida);
        }
        return saidasString.toString();
    }

    /**
     * @return A descricao do ambiente.
     * @author Davi Pedro
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * @return Uma descrição do quarto, incluindo moveis e saidas.
     * @author Davi Pedro
     */
    public String getDescricaoLonga(){
        return "========================================\nVoce esta " + getDescricao() + ".\n" + getMoveisAmbiente() + "\n" + getDescricaoSaida() ;
    }
}
