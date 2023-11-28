import java.util.ArrayList;

/**
 * Classe Inventario - Essa classe define e fornece os comandos do inventario do jogador.<br/>
 *
 * <br/>Essa classe fornece um ArrayList que representa o inventario do jogador, tambem fornece
 * metodos para inserir, remover e visualizar os itens do inventario.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */
public class Inventario {
    private static ArrayList<Item> inventario = new ArrayList<>();

    /**
     * Adiciona um Array de itens no inventario
     * @param itensTransferidos Itens para serem tranferidos para o Array do inventario.
     * @author Davi Pedro
     */
    public static void adicionarArrayItens(ArrayList<Item> itensTransferidos){
        inventario.addAll(itensTransferidos);
    }

    /**
     * Adiciona um unico item ao inventario.
     * @param item Item a ser adicionado no inventario
     * @author Davi Pedro
     */
    public static void adicionarItem(Item item){
        inventario.add(item);
    }

    /**
     * Imprime os itens que estao no inventario do jogador, caso
     * o inventario esteja vazio ele imprime uma mensagem informando-o
     * @author Davi Pedro
     */
    public static void olharInventario(){
        ArrayList<String> itens = getInventarioString();

        if (itens.isEmpty()){
            System.out.println("Seu inventário está vazio");
        } else{
            System.out.println("Voce pegou:");
            for (String item : itens){
                System.out.println(item);
            }
        }
    }

    /**
     * @return o ArrayList do inventario o jogador
     * @author Davi Pedro
     */
    public static ArrayList<Item> getInventario(){
        return inventario;
    }

    /**
     * Remove os itens que o inventario possui em comum com
     * Array de itens para remocao. Ele cria um array de Item onde
     * todo item que inventario possui em comum com itensRemocao eh
     * adicionado nele. Por fim remove-se todos os itens que itensParaRemover
     * possui em comum no inventario.
     * @param itensRemocao Itens para serem removidos com inventario.
     * @author Davi Pedro
     */
    public static void removerItens(ArrayList<String> itensRemocao){
        ArrayList<Item> inventario = getInventario();
        ArrayList<Item> itensParaRemover = new ArrayList<>();

        for (Item item : inventario) {
            if (itensRemocao.contains(item.getNome())) {
                itensParaRemover.add(item);
            }
        }

        inventario.removeAll(itensParaRemover);
    }

    /**
     * @return Um array de string com os itens do inventario.
     * @author Davi Pedro
     */
    public static ArrayList<String> getInventarioString(){
        ArrayList<String> itens = new ArrayList<>();
        for (Item item : inventario){
            itens.add(item.getNome());
        }
        return itens;
    }

    public static ArrayList<String> compostosNaoCriados(){
        ArrayList<ItensCompostos> itensCompostos = ItensCompostos.getItensCompostos();

        for (ItensCompostos itemComposto : itensCompostos){
            for (Item item : inventario){
                if (itemComposto.getNome().equalsIgnoreCase(item.getNome())){
                    itensCompostos.remove(itemComposto);
                }
            }
        }
        ArrayList<String> compostosNaoCriados = new ArrayList<>();

        for (ItensCompostos item : itensCompostos){
            compostosNaoCriados.add(item.getNome());
        }

        return compostosNaoCriados;
    }

}
