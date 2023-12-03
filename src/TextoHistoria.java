package src;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe src.TextoHistoria - Essa classe define os textos que aparecerao no jogo e da geracao do bilhete.<br/>
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
            
            O radio da central de controle nao estava mais funcionando.
            
            Voce percebeu que tinha que agir rapido, antes que a situacao piorasse,
            voce lembrou que havia um radio manual no topo da torre no qual voce poderia sintonizar.
            Voce pegou seu casaco, suas luvas, e saiu da sala de controle, para pegar os equipamentos.
            Voce esperava encontrar-los, mas lembrou que seus companheiros tinham levado tudo com eles.
            Com muito desespero, voce procura por algo que te ajudasse
            e
            por sorte,
            voce encontrou um bilhete em meio aos papeis da sala de controle.
            
            (Esse bilhete esta em uma das pastas do jogo, procure por ‘Bilhete’)
            """;
    private static final String BILHETE_INICIO = "Se voce esta lendo isso, eh porque voce precisa fazer uma saia de emergencia ate a torre de comunicacao." +
            "Isso pode te ajudar, (restante do texto)...\n" +
            "...Se voce precisar improvisar seus itens, procure por esses itens (provavelmente eles estao espalhados por toda a base):";

    private static final String FINAL_RUIM_INICIO = "Voce decide sair da base, voce sente que se equipou com tudo que precisava, mas o desespero nao te deixou perceber, voce esqueceu: ";
    private static final String FINAL_RUIM_FIM = "Nao sabemos ao certo o que aconteceu com voce, mas certamente nao sobreviveu.\nFIM\n\n(final ruim)";

    private static final String FINAL_BOM = """
                        
            O Último Ato
                        
                        
            O vento soprava forte, e a neve caía cada vez mais forte. Alex colocou sua touca para se proteger do frio.
            Cansado e exausto, mas determinado a completar sua missão, ele havia passado dias preparando-se para escalar a torre de comunicação danificada.
            Pegou seu aquecedor portátil e o colocou dentro de sua roupa. Precisava se manter aquecido para sobreviver à caminhada. 
            Ligou sua lanterna.
            A nevasca estava forte, e ele precisava de luz para enxergar.
            A torre estava longe...
            Depois de uma longa caminhada ele chega ate a torre.
            Devido a tempestade, sua escada havia sido muito danificada.
            Entao com a esperanca e o pouco que lhe sobrou de energia, comecou a escalar.
            Chegou ao topo da torre, estava exausto, mas feliz por ter chegado. Viu que o topo da torre estava intacta, mas o rádio estava congelado.
            Novamente, pegou seu aquecedor portátil e o apontou para o rádio. Depois de alguns minutos de espera e pensamentos, o rádio estava descongelado.
            Aflito com o estado do radio tentou ligar..
            O radio ligou.
            Rapidamente começou a sintonizar.
            ...
            ..
            .
            ..
            ...
            .
            O resgate atendeu, debilitado pelo frio mal conseguia falar.
            Contou sobre o que havia acontecido, e pediu ajuda.
            Era hora de tentar contato com os companheiros.
            ...
            ..
            .
            ..
            ...
            .
            Algumas horas depois, recebeu uma resposta no rádio.
            Era Gabriel, um de seus companheiros de equipe. 
            Ficou aliviado.
            Sabia que seus companheiros de equipe estavam vivos.
            Conversou com Gabriel e Cesar por alguns minutos, e explicou que havia conseguido se comunicar através do rádio manual da torre.
            Disse a Gabriel que a equipe estava a caminho, e que um helicóptero chegaria em breve.
            Algumas horas depois... o helicóptero chegou.
            Entrou no helicóptero e foi levado de volta à base. Contou tudo o que havia acontecido para os membros da equipe de resgate.
            Eles ficaram impressionados com sua coragem e determinação. Partiu com a equipe de resgate para buscar os companheiros de equipe.
            Voaram por algumas horas, aflito, procurando por seus amigos. 
            Finalmente, 
            viram um sinalizador piscando na floresta. 
            Pousaram o helicóptero e saíram.
            Encontraram os companheiros de equipe. 
            Eles estavam a salvo, mas estavam exaustos e com frio.
            Ajudou os companheiros de equipe a subir no helicóptero. 
            Voaram de volta para a base, onde todos foram recebidos com alegria.
            
            FIM
            """;

    /**
     * @return String com o texto do final ruim
     * @author Davi Pedro
     */
    private static String getFinalRuim(){
        return FINAL_RUIM_INICIO + "\n" + Inventario.compostosNaoCriados() + "\n" + FINAL_RUIM_FIM;
    }

    /**
     * @return String com o texto do final bom ou ruim
     * @author Davi Pedro
     */
    public static String getFinal(){
        if (Inventario.compostosNaoCriados().isEmpty()){
            return FINAL_BOM;
        }
        return getFinalRuim();
    }

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
     * Busca os itens que estao definidos no jogo, em src.ItensCompostos
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
