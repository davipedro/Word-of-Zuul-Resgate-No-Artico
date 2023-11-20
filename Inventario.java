import java.util.ArrayList;

public class Inventario {
    private static ArrayList<Item> itensColetados = new ArrayList<>();

    public static void adicionarItens(ArrayList<Item> itensTransferidos){
        itensColetados.addAll(itensTransferidos);
    }

    public static void olharInventario(){
        if (itensColetados.isEmpty()){
            System.out.println("Seu inventário está vazio");
        } else{
            System.out.println("Voce pegou:");
            for (Item item : itensColetados){
                System.out.println(item.getNome());
            }
        }
    }

}
