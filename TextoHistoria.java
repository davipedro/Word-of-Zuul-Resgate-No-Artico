import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe TextoHistoria - Essa classe define os textos que aparecerao no jogo e da geracao do bilhete.<br/>
 *
 * <br/> O bilhete eh um item externo do jogo, um arquivo de texto, que o jogador deve abrir para consultar
 * a receita dos itens compostos, a receita apresentada esta de acordo com os itens definidos no Enum de
 * itensCompostos.
 *
 * @author  Davi Pedro
 * @version 2023.12.03
 */

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

    /**
     * @return Uma string com a historia introdutoria do jogo.
     * @author Davi Pedro
     */
    public String getHistoria(){
        return HISTORIA;
    }

    /**
     * @return As boas vindas ao jogo, como o nome do jogo.
     * @author Davi Pedro
     */
    public String getBoasVindas(){
        return BOAS_VINDAS;
    }

    /**
     * @return Uma string com o texto inicial e padrao do bilhete
     * @author Davi Pedro
     */
    public String getBilheteInicio(){
        return BILHETE_INICIO;
    }

    /**
     * Busca os itens que estao definidos no jogo, em ItensCompostos
     * @return Uma string com o nome do item composto e sua receita para cada
     * item composto descrito no jogo
     * @author Davi Pedro
     */
    private String itensBilhete(){
        StringBuilder itens = new StringBuilder("\n\n");
        for (ItensCompostos itemComposto : ItensCompostos.values()){
            itens.append("Para: ").append(itemComposto.getNome()).append("\n").append("Voce precisa de\n").append(itemComposto.getReceita()).append("\n\n");
        }
        return itens.toString();
    }

    /**
     * Gera o bilhete que sera usado pelo jogador para consultar as receitas.
     * @throws IOException Caso nao seja possivel criar ou escrever no arquivo de texto
     * @author Davi Pedro
     */
    public void gerarBilhete() throws IOException {
        try (FileWriter arq = new FileWriter("Bilhete")){
            arq.write(getBilheteInicio() + itensBilhete());
        }
        catch (IOException e){
            System.out.println(getBilheteInicio() + itensBilhete());
            throw new IOException("Nao foi possivel gerar o bilhete");
        }
    }
}
