import java.util.ArrayList;

public class Bancada {
    //lista mostrando quais itens o jogador pode fazer no jogo - V
    //verificar se o jogador tem TODOS os itens da receita do item composto
    //Se sim: -Remover do inventario -Adicionar o item composto no inventario
    //Se não: -Retornar uma mensagem dizendo que o jogador não possui todos os itens necessários para compor
    private ItensCompostos itensCompostos;
    private BancadaComandos bancadaComandos;
    private static final String descricao = "Eh uma bancada grande, aqui eh possivel compor seus itens";

    public String ListaItensCompostos(){
        return "Os possiveis itens sao: " + ItensCompostos.getItensCompostos();
    }


    //INCOMPLETO INCOMPLETO INCOMPLETO INCOMPLETO
    private void verificaItens(String nomeItem){
        ArrayList<String> receita;
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                receita = itemComposto.getReceita();
            }
        }
    }

    public void comporItem(String nomeItem){
        if (!nomeItem.equalsIgnoreCase("cancelar")){
            verificaItens(nomeItem);
        }
    }

    public boolean validarEntrada(String entrada){
        PalavrasComando palavrasComando = new PalavrasComando();
        Boolean comandosPrincipais = palavrasComando.ehComando(entrada);
        Boolean comandosBancada = bancadaComandos.ehComando(entrada);

        return comandosBancada || comandosPrincipais;
    }

    public String getDescricao(){
        return descricao;
    }

    public String comandos(){
        Analisador analisador = new Analisador();
        return analisador.mostrarComandos() + " " + bancadaComandos.comandos();
    }

}
