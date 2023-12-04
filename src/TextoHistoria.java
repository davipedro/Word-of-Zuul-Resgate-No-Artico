package src;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe TextoHistoria - Essa classe define os textos que aparecerao no
 * jogo e da geracao do bilhete.<br/>
 *
 * <br/>
 * O bilhete eh um item externo do jogo, um arquivo de texto, que o jogador deve
 * abrir para consultar
 * a receita dos itens compostos, a receita apresentada esta de acordo com os
 * itens definidos no Enum de
 * itensCompostos.
 *
 * @author Davi Pedro
 * @version 2023.12.03
 */

public class TextoHistoria {

    private static final String BOAS_VINDAS = "Bem-vindo ao Word of Zuul - Resgate No Artico";
    public static final String HISTORIA = "<html>" +
            "Voce eh Alex, um analista de dados climaticos que trabalha em uma base cientifica no Artico.<br>" +
            "Voce faz parte de uma equipe de quatro pessoas que esta estudando os efeitos das mudancas climaticas na regiao.<br>"
            +
            "Seus companheiros sao Gabriel, um meteorologista, Davi, um geologo, e Cesar, uma biologo marinho.<br>" +
            "Voces estao no ultimo dia da sua missao, e seus companheiros decidiram fazer uma ultima exploracao no gelo,<br>"
            +
            "enquanto voce ficou na sala de controle, monitorando os dados e mantendo contato com eles pelo radio.<br>"
            +
            "Tudo estava indo bem, ate que uma forte nevasca atingiu a area, reduzindo a visibilidade e a temperatura.<br>"
            +
            "Voce tentou avisar seus companheiros, mas o radio comecou a falhar.<br><br>" +

            "<b>O radio da central de controle nao estava mais funcionando.</b><br><br>" +

            "Voce percebeu que tinha que agir rapido, antes que a situacao piorasse,<br>" +
            "voce lembrou que havia um radio manual no topo da torre no qual voce poderia sintonizar.<br>" +
            "Voce pegou seu casaco, suas luvas, e saiu da sala de controle, para pegar os equipamentos.<br>" +
            "Voce esperava encontrar-los, mas lembrou que seus companheiros tinham levado tudo com eles.<br>" +
            "Com muito desespero, voce procura por algo que te ajudasse<br>" +
            "e<br>" +
            "por sorte,<br>" +
            "voce encontrou um bilhete em meio aos papeis da sala de controle.<br><br>" +

            "</html>";

    private static final String BILHETE_INICIO = "<html> Se voce esta lendo isso, eh porque voce precisa fazer uma saia de emergencia ate a torre de comunicacao."
            +
            "Isso pode te ajudar, (restante do texto)...<br>" +
            "...Se voce precisar improvisar seus itens, procure por esses itens (provavelmente eles estao espalhados por toda a base):";

    private static final String FINAL_RUIM_INICIO = "<html>Voce decide sair da base, voce sente que se equipou com tudo que precisava, mas o desespero nao te deixou perceber, voce esqueceu: ";
    private static final String FINAL_RUIM_FIM = "Nao sabemos ao certo o que aconteceu com voce, mas certamente nao sobreviveu.<br>FIM<br><br>(final ruim) </html>";

    private static final String FINAL_BOM = "<html>" +
            "<center><b>O Último Ato</b></center><br><br>" +
            "O vento soprava forte, e a neve caía cada vez mais forte. Alex colocou sua touca para se proteger do frio.<br>"
            +
            "Cansado e exausto, mas determinado a completar sua missão, ele havia passado dias preparando-se para escalar a torre de comunicação danificada.<br>"
            +
            "Pegou seu aquecedor portátil e o colocou dentro de sua roupa. Precisava se manter aquecido para sobreviver à caminhada.<br>"
            +
            "Ligou sua lanterna.<br>" +
            "A nevasca estava forte, e ele precisava de luz para enxergar.<br>" +
            "A torre estava longe...<br>" +
            "Depois de uma longa caminhada, ele chegou até a torre.<br>" +
            "Devido à tempestade, sua escada havia sido muito danificada.<br>" +
            "Então, com a esperança e o pouco que lhe sobrou de energia, começou a escalar.<br>" +
            "Chegou ao topo da torre, estava exausto, mas feliz por ter chegado. Viu que o topo da torre estava intacto, mas o rádio estava congelado.<br>"
            +
            "Novamente, pegou seu aquecedor portátil e o apontou para o rádio. Depois de alguns minutos de espera e pensamentos, o rádio estava descongelado.<br>"
            +
            "Aflito com o estado do rádio, tentou ligar...<br>" +
            "O rádio ligou.<br>" +
            "Rapidamente começou a sintonizar.<br>" +
            "...<br>" +
            "..<br>" +
            ".<br>" +
            "..<br>" +
            "...<br>" +
            ".<br>" +
            "O resgate atendeu, debilitado pelo frio mal conseguia falar.<br>" +
            "Contou sobre o que havia acontecido e pediu ajuda.<br>" +
            "Era hora de tentar contato com os companheiros.<br>" +
            "...<br>" +
            "..<br>" +
            ".<br>" +
            "..<br>" +
            "...<br>" +
            ".<br>" +
            "Algumas horas depois, recebeu uma resposta no rádio.<br>" +
            "Era Gabriel, um de seus companheiros de equipe.<br>" +
            "Ficou aliviado.<br>" +
            "Sabia que seus companheiros de equipe estavam vivos.<br>" +
            "Conversou com Gabriel e Cesar por alguns minutos e explicou que havia conseguido se comunicar através do rádio manual da torre.<br>"
            +
            "Disse a Gabriel que a equipe estava a caminho e que um helicóptero chegaria em breve.<br>" +
            "Algumas horas depois... o helicóptero chegou.<br>" +
            "Entrou no helicóptero e foi levado de volta à base. Contou tudo o que havia acontecido para os membros da equipe de resgate.<br>"
            +
            "Eles ficaram impressionados com sua coragem e determinação. Partiu com a equipe de resgate para buscar os companheiros de equipe.<br>"
            +
            "Voaram por algumas horas, aflito, procurando por seus amigos.<br>" +
            "Finalmente,<br>" +
            "viram um sinalizador piscando na floresta.<br>" +
            "Pousaram o helicóptero e saíram.<br>" +
            "Encontraram os companheiros de equipe.<br>" +
            "Eles estavam a salvo, mas estavam exaustos e com frio.<br>" +
            "Ajudou os companheiros de equipe a subir no helicóptero.<br>" +
            "Voaram de volta para a base, onde todos foram recebidos com alegria.<br><br>" +
            "<center><b>FIM</b></center>" +
            "</html>";

    /**
     * @return String com o texto do final ruim
     * @author Davi Pedro
     */
    private static String getFinalRuim() {
        return FINAL_RUIM_INICIO + "<br>" + Inventario.compostosNaoCriados() + "<br>" + FINAL_RUIM_FIM;
    }

    /**
     * @return String com o texto do final bom ou ruim
     * @author Davi Pedro
     */
    public static String getFinal() {
        if (!Inventario.temCompostosNaoCriados()) {
            return FINAL_BOM;
        }
        return getFinalRuim();
    }

    /**
     * @return Uma string com a historia introdutoria do jogo.
     * @author Davi Pedro
     */
    public static String getHistoria() {
        return HISTORIA;
    }

    /**
     * @return As boas vindas ao jogo, como o nome do jogo.
     * @author Davi Pedro
     */
    public String getBoasVindas() {
        return BOAS_VINDAS;
    }

    /**
     * @return Uma string com o texto inicial e padrao do bilhete
     * @author Davi Pedro
     */
    public static String getBilheteInicio() {
        return BILHETE_INICIO;
    }

    /**
     * Busca os itens que estao definidos no jogo, em ItensCompostos
     * 
     * @return Uma string com o nome do item composto e sua receita para cada
     *         item composto descrito no jogo
     * @author Davi Pedro
     */
    private static String itensBilhete() {
        StringBuilder itens = new StringBuilder("<br><br>");
        for (ItensCompostos itemComposto : ItensCompostos.values()) {
            itens.append("Para: <br>").append(itemComposto.getNome()).append("<br>").append("Voce precisa de <br>")
                    .append(itemComposto.getReceita()).append("<br><br>");
        }
        return itens.append("</html>").toString();
    }

    /**
     * Gera o bilhete que sera usado pelo jogador para consultar as receitas.
     * 
     * @throws IOException Caso nao seja possivel criar ou escrever no arquivo de
     *                     texto
     * @author Davi Pedro
     */
    public static String gerarBilhete() {
        return getBilheteInicio() + itensBilhete();
    }
}
