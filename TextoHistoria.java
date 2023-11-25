import java.io.FileWriter;
import java.io.IOException;

public class TextoHistoria {

    private static final String BOAS_VINDAS = "Bem-vindo ao Word of Zuul - Resgate No Artico";
    private static final String HISTORIA = """
            Voce eh Alex, um analista de dados climaticos que trabalha em uma base cientifica no Artico. 
            Voce faz parte de uma equipe de quatro pessoas que esta estudando os efeitos das mudancas climaticas na regiao. 
            Seus companheiros sao Gabriel, um meteorologista, Davi, um geologo, e Cesar, uma biologo marinho. 
            Voces estao no ultimo dia da sua missao, e seus companheiros decidiram fazer uma ultima exploracao no gelo, 
            enquanto voce ficou na sala de controle, monitorando os dados e mantendo contato com eles pelo radio. 
            Tudo estava indo bem, ate que uma forte nevasca atingiu a area, reduzindo a visibilidade e a temperatura. 
            Voce tentou avisar seus companheiros, mas o radio comecou a falhar. 
            Voce olhou pela janela e viu que a torre de comunicacao estava danificada pela neve e pelo vento. 
            Voce percebeu que tinha que agir rapido, antes que a situacao piorasse. 
            Voce pegou seu casaco, suas luvas, e saiu da sala de controle, em direcao a torre. 
            Voce esperava encontrar alguma ferramenta para consertar a torre, mas lembrou que seus companheiros tinham levado tudo com eles. 
            Com o desespero, voce procura por algo que te ajudasse, por sorte, voce encontrou um bilhete em meio aos papeis da sala de controle.
            \n(Esse bilhete esta em uma das pastas do jogo, procure por ‘Bilhete’)
            """;

    private static final String BILHETE_INICIO = "Se voce esta lendo isso, eh porque voce precisa consertar a torre de comunicacao " +
            "que foi danificada pela nevasca. Não ha tempo para explicacoes, apenas siga estas instrucoes:\n" +
            "Voce vai precisar improvisar seus itens, entao procure por esses itens por toda a base:";

    public String getHistoria(){
        return HISTORIA;
    }

    public String getBoasVindas(){
        return BOAS_VINDAS;
    }

    public String getBilheteInicio(){
        return BILHETE_INICIO;
    }

    private String itensBilhete(){
        StringBuilder itens = new StringBuilder("\n\n");
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            itens.append("Para: ").append(itemComposto.getNome()).append("\n").append("Voce precisa de\n").append(itemComposto.getReceita()).append("\n\n");
        }
        return itens.toString();
    }

    public void gerarBilhete(){
        try (FileWriter arq = new FileWriter("Bilhete")){
            arq.write(getBilheteInicio() + itensBilhete());
        }
        catch (IOException e){
            System.out.println(getBilheteInicio() + itensBilhete());
        }
    }
}
