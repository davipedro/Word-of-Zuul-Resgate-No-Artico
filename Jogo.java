/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente cozinha, quartoLadoCozinha, quartoLadoBanheiro, banheiro, corredorOeste,corredorCentroOeste,corredorCentroLeste, corredorLeste, laboratorio, salaDeComando, oficina, garagem, torre;
      
        // cria os ambientes
        cozinha = new Ambiente("na cozinha da estacao");
        quartoLadoCozinha = new Ambiente("no quarto ao lado da cozinha");
        quartoLadoBanheiro = new Ambiente("no quarto ao lado do banheiro");
        banheiro = new Ambiente("no banheiro da estacao");
        corredorOeste = new Ambiente("no lado oeste do corredor");
        corredorCentroOeste = new Ambiente("no centro mais a oeste do corredor");
        corredorCentroLeste = new Ambiente("no centro mais a leste do corredor");
        corredorLeste = new Ambiente("no lado leste do corredor");
        laboratorio = new Ambiente("no laboratório da estacao");
        salaDeComando = new Ambiente("na sala de comando da estacao");
        oficina = new Ambiente("na oficina da estacao");
        garagem = new Ambiente("na garagem da estacao");
        torre = new Ambiente("na torre da estacao");
        
        // inicializa as saidas dos ambientes
        cozinha.definirSaidas("sul", corredorOeste);
        quartoLadoCozinha.definirSaidas("sul", corredorCentroOeste);
        quartoLadoBanheiro.definirSaidas("sul", corredorCentroLeste);
        banheiro.definirSaidas("sul", corredorLeste);
        laboratorio.definirSaidas("norte", corredorLeste);
        salaDeComando.definirSaidas("norte", corredorCentroOeste);
        oficina.definirSaidas("norte", corredorLeste);
        garagem.definirSaidas("norte", oficina);
        garagem.definirSaidas("oeste", torre);
        //corredor
        //Oeste
        corredorOeste.definirSaidas("norte", cozinha);
        corredorOeste.definirSaidas("sul", laboratorio);
        corredorOeste.definirSaidas("leste", corredorCentroOeste);
        //CentroOeste
        corredorCentroOeste.definirSaidas("norte", quartoLadoCozinha);
        corredorCentroOeste.definirSaidas("sul", salaDeComando);
        corredorCentroOeste.definirSaidas("oeste", corredorOeste);
        corredorCentroOeste.definirSaidas("leste", corredorCentroLeste);
        //CentroLeste
        corredorCentroLeste.definirSaidas("norte", quartoLadoBanheiro);
        corredorCentroLeste.definirSaidas("oeste", corredorCentroOeste);
        corredorCentroLeste.definirSaidas("leste", corredorLeste);
        //Leste
        corredorLeste.definirSaidas("norte", banheiro);
        corredorLeste.definirSaidas("sul", oficina);
        corredorLeste.definirSaidas("oeste", corredorCentroOeste);

        ambienteAtual = quartoLadoCozinha;  // o jogo comeca no quarto ao lado da cozinha
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimirInfoLocalizacao();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("olhar")){
            olhar();
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Pelo nervosismo da situacao voce fica confuso para onde ir");
        System.out.println();
        System.out.println("Seus comandos sao:");
        analisador.mostrarComandos();
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            
            imprimirInfoLocalizacao();
        }
    }

    public void imprimirInfoLocalizacao(){
        System.out.println(ambienteAtual.getDescricaoLonga());
        System.out.println();
    }

    public void olhar(){
        System.out.println(ambienteAtual.getDescricaoLonga());
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
}
