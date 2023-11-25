import java.util.ArrayList;

public class Bancada {
    //lista mostrando quais itens o jogador pode fazer no jogo - V
    //verificar se o jogador tem TODOS os itens da receita do item composto
    //Se sim: -Remover do inventario -Adicionar o item composto no inventario
    //Se não: -Retornar uma mensagem dizendo que o jogador não possui todos os itens necessários para compor
    private ItensCompostos itensCompostos;
    private ComandosBancada comandosBancada;
    private static final String descricao = "Voce se aproxima de uma bancada.\nEh uma bancada grande, aqui eh um bom lugar para compor seus itens";

    public String ListaItensCompostos(){
        return "Os possiveis itens sao:\n" + ItensCompostos.getItensCompostosString();
    }


    //INCOMPLETO INCOMPLETO INCOMPLETO INCOMPLETO

    /**
     * Verifica se o jogador possui os itens (no inventario) necessarios para a receita
     * @param nomeItem
     */
    private boolean checaIngredientes(String nomeItem){
        ArrayList<String> inventario = Inventario.getInventarioString();
        ArrayList<String> receita = buscarReceita(nomeItem);
        inventario.retainAll(receita);
        return inventario.containsAll(receita);
    }

    private ArrayList<String> buscarReceita(String nomeItem){
        ArrayList<String> receita = new ArrayList<>();
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                 receita = itemComposto.getReceita();
            }
        }
        return receita;
    }

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
     * Recebe o nome referente ao item composto(enum) e retorna um item com as caracteristicas do item composto,
     * caso nao seja possivel obter o item composto eh retornado null
     * @param nomeItem
     * @return Item
     */
    public Item enumParaItem(String nomeItem){
        ItensCompostos itemComposto = ItensCompostos.getItemComposto(nomeItem);
        if (itemComposto == null){
            return null;
        }
        return new Item(itemComposto.getNome(),itemComposto.getDescricao());
    }

    /**
     * Verifica se o item escolhido existe no jogo (enum ItensCompostos)
     * @param nomeItem
     * @return boolean
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
     * Verifica se a palavra que foi
     * @param entrada
     * @return
     */
    public boolean validarEntrada(String entrada){
        return ComandosBancada.ehComandoBancada(entrada);
    }

    public String getDescricao(){
        return descricao;
    }

    /**
     * Mostra todos os comandos validos
     * (Bancada nao eh um comando valido quando ja esta na bancada)
     * @return
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
