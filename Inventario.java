import java.util.ArrayList;

public class Inventario {
    private static ArrayList<Item> inventario = new ArrayList<>();

    public static void adicionarArrayItens(ArrayList<Item> itensTransferidos){
        inventario.addAll(itensTransferidos);
    }

    public static void adicionarItem(Item item){
        inventario.add(item);
    }

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

    public static ArrayList<Item> getInventario(){
        return inventario;
    }

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

    public static ArrayList<String> getInventarioString(){
        ArrayList<String> itens = new ArrayList<>();
        for (Item item : inventario){
            itens.add(item.getNome());
        }
        return itens;
    }

}
