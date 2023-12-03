package src;

import java.io.IOException;
import java.util.Scanner;

import src.ambientes.Banheiro;
import src.ambientes.Corredor;
import src.ambientes.Cozinha;
import src.ambientes.Garagem;
import src.ambientes.Laboratorio;
import src.ambientes.Oficina;
import src.ambientes.SalaComando;
import src.ambientes.quarto.QuartoBanheiro;
import src.ambientes.quarto.QuartoCozinha;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull - Desafio no artico".
 *  Modificada do jogo "World of Zuul": um jogo de aventura muito simples, baseado em texto.
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes com seus moveis e itens, cria o analisador e comeca o jogo. Ela tambem avalia e
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Davi Pedro - Base: Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2023.12.03 - 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
    /**
     * Define qual ambiente tera a bancada.
     */
    private static final String ambienteComBancada = "laboratorio";
    private static final String ambienteComSaida = "garagem";
    private static final String DirecaoSaidaTorre = "oeste";
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Ambiente ambienteLocal;
    private Scanner scanner;

    /**
     * Cria o jogo e incializa seu mapa interno, ambiente local, ambiente com a saida da base, scanner e analisador.
     */
    public Jogo() 
    {
        criarAmbientes();
        ambienteLocal = ambienteAtual;
        analisador = new Analisador();
        scanner = new Scanner(System.in);
    }

    /**
     * Cria tudo relacionado aos ambientes: ambientes, saidas, moveis e itens basicos
     * @author Davi Pedro
     */
    private void criarAmbientes()
    {
        Ambiente cozinha, quartoLadoCozinha, quartoLadoBanheiro, banheiro, corredorOeste,corredorCentroOeste,corredorCentroLeste, corredorLeste, laboratorio, salaDeComando, oficina, garagem, torre;

        // cria os ambientes
        laboratorio = new Laboratorio("laboratorio","no laboratório da estacao");
        cozinha = new Cozinha("cozinha","na cozinha da estacao");
        quartoLadoCozinha = new QuartoCozinha("quartoLadoCozinha","no quarto ao lado da cozinha");
        quartoLadoBanheiro = new QuartoBanheiro("quartoLadoBanheiro","no quarto ao lado do banheiro");
        banheiro = new Banheiro("banheiro","no banheiro da estacao");
        corredorOeste = new Corredor("corredorOeste","no lado oeste do corredor");
        corredorCentroOeste = new Corredor("corredorCentroOeste","no centro-oeste do corredor");
        corredorCentroLeste = new Corredor("corredorCentroLeste","no centro-leste do corredor");
        corredorLeste = new Corredor("corredorLeste","no lado leste do corredor");
        salaDeComando = new SalaComando("salaDeComando","na sala de comando da estacao");
        oficina = new Oficina("oficina","na oficina da estacao");
        garagem = new Garagem("garagem","na garagem da estacao");
        torre = new Ambiente("torre","na torre da estacao");
        
        // inicializa as saidas dos ambientes
        cozinha.definirSaidas("sul", corredorOeste);
        quartoLadoCozinha.definirSaidas("sul", corredorCentroOeste);
        quartoLadoBanheiro.definirSaidas("sul", corredorCentroLeste);
        banheiro.definirSaidas("sul", corredorLeste);
        laboratorio.definirSaidas("norte", corredorOeste);
        salaDeComando.definirSaidas("norte", corredorCentroOeste);
        oficina.definirSaidas("norte", corredorLeste);
        oficina.definirSaidas("sul", garagem);
        garagem.definirSaidas("norte", oficina);

        garagem.definirSaidas(DirecaoSaidaTorre, torre);

        corredorOeste.definirSaidas("norte", cozinha);
        corredorOeste.definirSaidas("sul", laboratorio);
        corredorOeste.definirSaidas("leste", corredorCentroOeste);

        corredorCentroOeste.definirSaidas("norte", quartoLadoCozinha);
        corredorCentroOeste.definirSaidas("sul", salaDeComando);
        corredorCentroOeste.definirSaidas("oeste", corredorOeste);
        corredorCentroOeste.definirSaidas("leste", corredorCentroLeste);

        corredorCentroLeste.definirSaidas("norte", quartoLadoBanheiro);
        corredorCentroLeste.definirSaidas("oeste", corredorCentroOeste);
        corredorCentroLeste.definirSaidas("leste", corredorLeste);

        corredorLeste.definirSaidas("norte", banheiro);
        corredorLeste.definirSaidas("sul", oficina);
        corredorLeste.definirSaidas("oeste", corredorCentroLeste);

        //define aleatoriamente quais itens vao estar em quais moveis do ambiente
        cozinha.definirItensMoveis();
        quartoLadoCozinha.definirItensMoveis();
        quartoLadoBanheiro.definirItensMoveis();
        banheiro.definirItensMoveis();
        laboratorio.definirItensMoveis();
        oficina.definirItensMoveis();
        garagem.definirItensMoveis();

        ambienteAtual = salaDeComando;
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() throws IOException {
        imprimirBoasVindas();
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando(ambienteLocal);
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador, com a historia inicial do jogo,
     * além de gerar o bilhete que o jogador utilizada para acompanhar a receita dos itens compostos.
     * @author Davi Pedro
     */
    private void imprimirBoasVindas() throws IOException {
        TextoHistoria textoHistoria = new TextoHistoria();
        textoHistoria.gerarBilhete();
        System.out.println();
        System.out.println(textoHistoria.getBoasVindas());
        System.out.println(textoHistoria.getHistoria());
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimirInfoLocalizacao();
        System.out.println(analisador.mostrarComandos(ambienteAtual));
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o).
     * As opcoes de comando variam se o ambiente possui bancada ou nao.
     * @param comando O src.Comando a ser processado.
     * @return Boolean que indica se o jogo deve ser finalizado ou nao.
     * @author Davi Pedro
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando().toLowerCase();
        Ambiente saidaAtual = ambienteLocal.getSaida(comando.getSegundaPalavra());
        if (!(saidaAtual == null)){
            ambienteLocal = ambienteLocal.getSaida(comando.getSegundaPalavra());
        }

        if (ambienteLocal.getMoveis().get("BANCADA") != null){
            querSair = menuAmbienteComBancada(palavraDeComando,comando,querSair);
        } else {
            querSair = menuAmbienteSemBancada(palavraDeComando,comando,querSair);
        }

        return querSair;
    }

    /**
     * Menu disponivel no ambiente com a bancada
     * @param palavraDeComando Primeira palavra do comando fornecido pelo usuario
     * @param comando O comando completo
     * @param querSair Retorno que indica se eh um comando para sair do jogo
     * @return Boolean que indica se o jogo deve ser finalizado ou nao.
     * @author Davi Pedro
     */
    private boolean menuAmbienteComBancada(String palavraDeComando, Comando comando, boolean querSair){
        querSair = false;
        switch (palavraDeComando){
            case "bancada":
                menuBancada();
                break;
            case "ajuda":
                imprimirAjuda();
                break;
            case "ir":
                querSair = irParaAmbiente(comando);
                break;
            case "olhar":
                olhar();
                break;
            case "vasculhar":
                vasculhar(comando.getSegundaPalavra());
                break;
            case "inventario":
                Inventario.olharInventario();
                break;
            case "sair":
                querSair = sair(comando);
                break;
        }
        return querSair;
    }

    /**
     * Menu disponivel no ambiente sem a bancada
     * @param palavraDeComando Primeira palavra do comando fornecido pelo usuario
     * @param comando O comando completo fornecido pelo usuario
     * @param querSair Retorno que indica se eh um comando para sair do jogo
     * @return Boolean que indica se o jogo deve ser finalizado ou nao.
     * @author Davi Pedro
     */
    private boolean menuAmbienteSemBancada(String palavraDeComando, Comando comando, boolean querSair){
        querSair = false;
        switch (palavraDeComando){
            case "ajuda":
                imprimirAjuda();
                break;
            case "ir":
                querSair = irParaAmbiente(comando);
                break;
            case "olhar":
                olhar();
                break;
            case "vasculhar":
                vasculhar(comando.getSegundaPalavra());
                break;
            case "inventario":
                Inventario.olharInventario();
                break;
            case "sair":
                querSair = sair(comando);
                break;
        }
        return querSair;
    }

    /**
     * Menu da bancada, nela eh possivel compor os itens basicos em itens compostos,
     * imprimir ajuda e voltar para o menu do ambiente
     * @author Davi Pedro
     */
    private void menuBancada(){
        Bancada bancada = new Bancada();

        System.out.println(bancada.getDescricao());
        System.out.println(bancada.mostrarComandos());

        String comando = "";
        while (!comando.equalsIgnoreCase("voltar")){

            System.out.print("> ");
            comando = scanner.nextLine();

            if (bancada.validarEntrada(comando)){
                switch (comando.toLowerCase()){
                    case "compor":
                        System.out.println(bancada.ListaItensCompostos());
                        System.out.println("Digite o nome do item que deseja compor ou 'cancelar'");
                        System.out.print("> ");
                        System.out.println(bancada.comporItem(scanner.nextLine()));
                        break;
                    case "ajuda":
                        System.out.println(bancada.getDescricao());
                        System.out.println(bancada.mostrarComandos());
                        break;
                    case "voltar":
                        System.out.println("Voce saiu de perto da bancada");
                        System.out.println(analisador.mostrarComandos(ambienteAtual));
                        break;
                }
            } else {
                System.out.println("Eu nao entendi o que voce disse...");
            }
        }
    }

    /**
     * Printa informacoes de ajuda.
     * Imprime a lista de
     * palavras de comando
     * @author Davi Pedro
     */
    private void imprimirAjuda() 
    {
        System.out.println(analisador.mostrarComandos(ambienteAtual));
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro. <br/>
     *
     * <br/> Caso o ambiente seja o ambiente com saida e a saida seja a saida da base,
     * e o jogador opte por sair, ele
     *
     * @param comando O comando completo fornecido pelo usuario
     * @author Davi Pedro
     */
    private boolean irParaAmbiente(Comando comando)
    {
        boolean querSair = false;
        if(!comando.temSegundaPalavra()) {
            System.out.println("Ir pra onde?");
            return false;
        }

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);
        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        } else if (ambienteAtual.getNome().equalsIgnoreCase(ambienteComSaida) && proximoAmbiente.getNome().equalsIgnoreCase("torre")) {
            querSair = sairDaBase();
        } else {
            ambienteAtual = proximoAmbiente;
            ambienteLocal = ambienteAtual;

            imprimirInfoLocalizacao();
            System.out.println(analisador.mostrarComandos(ambienteAtual));
        }
        return querSair;
    }

    /**
     * Imprime um pedido de confirmacao da saida da base
     * @return Boolean que indica se o jogador quer ou nao sair da base
     * @author Davi Pedro
     */
    private boolean sairDaBase(){
        String escolha = "";
        System.out.println("Tem certeza que deseja sair da base? Nao podera voltar");
        System.out.println("Saia apenas quando estiver pronto! É sua decisao..");
        System.out.println("Digite SIM ou NAO");
        System.out.print("> ");
        escolha = scanner.nextLine().toUpperCase();

        while (!(escolha.equalsIgnoreCase("SIM")) && !(escolha.equalsIgnoreCase("NAO"))) {
            System.out.println("Nao entendi sua resposta..");
            escolha = scanner.nextLine().toUpperCase();
        }
        if (escolha.equalsIgnoreCase("sim")){
            System.out.println(TextoHistoria.getFinal());
            return true;
        } else {
            imprimirAjuda();
        }
        return false;
    }

    /**
     * @return Nome do ambiente que possui a bancada
     * @author Davi Pedro
     */
    public static String getAmbienteComBancada(){
        return ambienteComBancada;
    }

    /**
     * Imprime a descricao do local
     * @author Davi Pedro
     */
    public void imprimirInfoLocalizacao(){
        System.out.println(ambienteAtual.getDescricaoLonga());
        System.out.println();
    }

    /**
     * Imprime a descricao do local e os comandos disponiveis no ambiente atual
     * @author Davi Pedro
     */
    private void olhar(){
        System.out.println(ambienteAtual.getDescricaoLonga());
        System.out.println(analisador.mostrarComandos(ambienteAtual));
    }

    /**
     * Acessa o movel do ambiente, pega os itens do movel e transfere para o inventario,
     * caso o movel nao possua itens exibe uma mensagem informando o jogador.
     * @param nomeMovel Nome do movel ter os itens vasculhados
     * @author Davi Pedro
     */
    private void vasculhar(String nomeMovel){
        if (nomeMovel != null){
            if (ambienteAtual.validaMovel(nomeMovel)){
                String itensMovel = ambienteAtual.getDescricaoItensMovel(nomeMovel);

                if (itensMovel != null){
                    System.out.println("Voce procura por itens interessantes:");
                    System.out.println(itensMovel);
                    Inventario.adicionarArrayItens(ambienteAtual.transferirItensMovel(nomeMovel));
                    System.out.println("Voce leva todos os itens");
                } else {
                    System.out.println("Nao ha nada util aqui");
                }
            } else {
                System.out.println("Nao existe esse movel aqui");
            }
        } else {
            System.out.println("Escreva vasculhar + (nome do movel)");
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return Boolean que indica se o jogo deve ser finalizado ou nao
     * @author Davi Pedro
     */
    private boolean sair(Comando comando)
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Deseja sair do jogo? Caso sim, digite somente 'sair'\n Ao sair ira perder todo o progresso!");
            return false;
        }
        else {
            return true;
        }
    }
}
