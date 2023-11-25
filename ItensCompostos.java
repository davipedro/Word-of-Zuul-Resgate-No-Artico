import java.util.ArrayList;
import java.util.Arrays;

public enum ItensCompostos {
    PICARETA_ESCALADA("PICARETA DE ESCALADA","Uma picareta de escalada improvisada, nao eh das melhores mas parece ser bem resistente",new ArrayList<>(Arrays.asList("FACA DE CHEF","CANO DE METAL"
            ,"CORDA"))),
    LANTERNA("LANTERNA","Uma lanterna improvisada, sera muito util para enxergar em meio a nevasca",new ArrayList<>(Arrays.asList("LAMPADA","BATERIA"
            ,"FITA ISOLANTE PEQUENA"))),
    AQUECEDOR_PORTATIL("AQUECEDOR PORTATIL","A temperatura do lado de fora eh congelante, isso ira ajudar a se manter aquecido",new ArrayList<>(Arrays.asList("FIO DE COBRE", "BATERIA DE CARRO",
            "FITA ISOLANTE GRANDE"))),
    TOUCA("TOUCA","Uma touca de retalhos, a costura esta bem feita, voce parece ter habilidade com isso",new ArrayList<>(Arrays.asList("CASACO RASGADO", "LINHA", "AGULHA", "TESOURA")));

    private ArrayList<String> receita;
    private String descricao;
    private String nome;

    ItensCompostos(String nome, String descricao, ArrayList<String> receita){
        this.receita = receita;
        this.descricao = descricao;
        this.nome = nome;
    }

    public ArrayList<String> getReceita(){
        return receita;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getNome(){
        return nome;
    }

    public static ItensCompostos getItemComposto(String nomeItem){
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            if (itemComposto.getNome().equalsIgnoreCase(nomeItem)){
                return itemComposto;
            }
        }
        return null;
    }

    public static String getItensCompostosString(){
        StringBuilder itens = new StringBuilder(" - ");
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            itens.append(itemComposto.getNome()).append(" - ");
        }
        return itens.toString();
    }
}
