import java.util.ArrayList;
import java.util.stream.Collectors;

public class Inventario {
    private static ArrayList<Item> inventario = new ArrayList<>();

    public static void adicionarItens(ArrayList<Item> itensTransferidos){
        inventario.addAll(itensTransferidos);
    }

    public static void olharInventario(){
        ArrayList<String> itens = getInventario();

        if (itens.isEmpty()){
            System.out.println("Seu inventário está vazio");
        } else{
            System.out.println("Voce pegou:");
            for (String item : itens){
                System.out.println(item);
            }
        }
    }

    public static ArrayList<String> getInventario(){
        return inventario.stream().map(Item::getNome).collect(Collectors.toCollection(ArrayList::new));
    }

}
