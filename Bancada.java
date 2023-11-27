import java.util.ArrayList;

/**
 * Classe Bancada - Essa classe define e fornece as funcionalidades da bancada.<br/>
 *
 * <br/> A bancada eh um objeto especial, que eh tratado como um comando, no qual
 * permite o jogador a compor seus itens necessarios para avancar no jogo, ela interage
 * com os itens compostos declarados no jogo e os comandos da bancada.
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public class Bancada {
    private ItensCompostos itensCompostos;
    private ComandosBancada comandosBancada;
    private static final String descricao = "Voce se aproxima de uma bancada.\nEh uma bancada grande, aqui eh um bom lugar para compor seus itens";

    /**
     * @return Uma lista com os possiveis itens compostos a serem feitos
     * @author Davi Pedro
     */
    public String ListaItensCompostos(){
        return "Os possiveis itens sao:\n" + ItensCompostos.getItensCompostosString();
    }

    /**
     * Verifica se o jogador possui os itens necessarios para a receita, no inventario.
     * @param nomeItem
     * @return Boolean que indica se o jogador possui os itens necessarios para a compor o item
     * @author Davi Pedro
     */
    private boolean checaIngredientes(String nomeItem){
        ArrayList<String> inventario = Inventario.getInventarioString();
        ArrayList<String> receita = buscarReceita(nomeItem);
        inventario.retainAll(receita);
        return inventario.containsAll(receita);
    }

    /**
     * Busca a receita com o nome do item fornecido
     * @param nomeItem Nome do item que se deseja receber a receita
     * @return A receita do item
     * @author Davi Pedro
     */
    private ArrayList<String> buscarReceita(String nomeItem){
        ArrayList<String> receita = new ArrayList<>();
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                 receita = itemComposto.getReceita();
            }
        }
        return receita;
    }

    /**
     * Classe principal que chama outros metodos para verificar as condicoes
     * da composicao do item
     * @param nomeItem Nome do item que se deseja compor
     * @return Mensagem que informa se o item foi ou nao composto
     * @author Davi Pedro
     */
    public String comporItem(String nomeItem){
        if (nomeItem.equalsIgnoreCase("cancelar")){
            return "Composicao cancelada..";
        } else {
            if (!validarItemEscolhido(nomeItem)){
                return "Esse item nao existe!";
            }
            if (checaIngredientes(nomeItem)){
                ArrayList<String> receita = buscarReceita(nomeItem);
                Inventario.removerItens(receita);
                if ((enumParaItem(nomeItem) != null)){
                    Item itemComposto = enumParaItem(nomeItem);
                    Inventario.adicionarItem(itemComposto);
                    return "Item composto com sucesso!";
                }
            } else {
                return "Voce nao possui os itens necessarios";
            }
        }
        return "ERRO: Nao foi possivel obter esse item dos itens declarados como Itens Compostos";
    }

    /**
     * Recebe o nome referente ao item composto e retorna um item com as caracteristicas do item composto,
     * caso nao seja possivel obter o item composto eh retornado null
     * @param nomeItem Nome do item composto
     * @return Item ou nulo
     * @author Davi Pedro
     */
    public Item enumParaItem(String nomeItem){
        ItensCompostos itemComposto = ItensCompostos.getItemComposto(nomeItem);
        if (itemComposto == null){
            return null;
        }
        return new Item(itemComposto.getNome(),itemComposto.getDescricao());
    }

    /**
     * Verifica se o item escolhido existe no jogo
     * @param nomeItem Nome do item que se deseja verificar
     * @return Boolean que indica se o item existe ou nao no jogo
     * @author Davi Pedro
     */
    public boolean validarItemEscolhido(String nomeItem){
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a palavra eh um comando valido na bancada
     * @param entrada Comando a ser verificado
     * @return Boolean que indica se eh um comando da bancada ou nao
     * @author Davi Pedro
     */
    public boolean validarEntrada(String entrada){
        return ComandosBancada.ehComandoBancada(entrada);
    }

    /**
     * @return Descricao da bancada
     * @author Davi Pedro
     */
    public String getDescricao(){
        return descricao;
    }

    /**
     * Mostra todos os comandos validos <br/>
     * (O comando 'Bancada' nao eh um comando valido quando ja se esta na bancada)
     * @return Uma string com os comandos validos
     * @author Davi Pedro
     */
    public String mostrarComandos(){
        StringBuilder comandos = new StringBuilder("seus comandos sao:\n");
        for (ComandosBancada comando : ComandosBancada.values()){
            if (comando != ComandosBancada.BANCADA){
                comandos.append(comando).append(" ");
            }
        }
        return comandos.toString();
    }

}
