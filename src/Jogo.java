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
import src.ambientes.Torre;
import src.ambientes.quarto.QuartoBanheiro;
import src.ambientes.quarto.QuartoCozinha;

/**
 * Essa eh a classe principal da aplicacao "World of Zull - Desafio no artico".
 * Modificada do jogo "World of Zuul": um jogo de aventura muito simples,
 * baseado em texto.
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 * "jogar".
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes com seus moveis e itens, cria o analisador e comeca o jogo. Ela
 * tambem avalia e
 * executa os comandos que o analisador retorna.
 * 
 * @author Davi Pedro - Base: Michael Kölling and David J. Barnes (traduzido por
 *         Julio Cesar Alves)
 * @version 2023.12.03 - 2011.07.31 (2016.02.01)
 */

public class Jogo {
    /**
     * Define qual ambiente tera a bancada.
     */
    private static final String direcaoSaidaTorre = "oeste";
    private Analisador analisador;
    private Ambiente ambienteAtual;

    /**
     * Cria o jogo e incializa seu mapa interno, ambiente local, ambiente com a
     * saida da base, scanner e analisador.
     */
    public Jogo() {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria tudo relacionado aos ambientes: ambientes, saidas, moveis e itens
     * basicos
     * 
     * @author Davi Pedro
     */
    private void criarAmbientes() {
        Ambiente cozinha, quartoLadoCozinha, quartoLadoBanheiro, banheiro, corredorOeste, corredorCentroOeste,
                corredorCentroLeste, corredorLeste, laboratorio, salaDeComando, oficina, garagem, torre;

        // cria os ambientes
        laboratorio = new Laboratorio("laboratorio", "no laboratório da estacao");
        cozinha = new Cozinha("cozinha", "na cozinha da estacao");
        quartoLadoCozinha = new QuartoCozinha("quartoLadoCozinha", "no quarto ao lado da cozinha");
        quartoLadoBanheiro = new QuartoBanheiro("quartoLadoBanheiro", "no quarto ao lado do banheiro");
        banheiro = new Banheiro("banheiro", "no banheiro da estacao");
        corredorOeste = new Corredor("corredorOeste", "no lado oeste do corredor");
        corredorCentroOeste = new Corredor("corredorCentroOeste", "no centro-oeste do corredor");
        corredorCentroLeste = new Corredor("corredorCentroLeste", "no centro-leste do corredor");
        corredorLeste = new Corredor("corredorLeste", "no lado leste do corredor");
        salaDeComando = new SalaComando("salaDeComando", "na sala de comando da estacao");
        oficina = new Oficina("oficina", "na oficina da estacao");
        garagem = new Garagem("garagem", "na garagem da estacao");
        torre = new Torre("torre", "na torre da estacao");

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

        garagem.definirSaidas(direcaoSaidaTorre, torre);

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

        // define aleatoriamente quais itens vao estar em quais moveis do ambiente
        cozinha.definirItensMoveis();
        quartoLadoCozinha.definirItensMoveis();
        quartoLadoBanheiro.definirItensMoveis();
        banheiro.definirItensMoveis();
        laboratorio.definirItensMoveis();
        oficina.definirItensMoveis();
        garagem.definirItensMoveis();

        ambienteAtual = oficina;
    }

    /**
     * Rotina principal do jogo.
     */
    public String[] jogar(String jogada) {
        Comando comando = analisador.pegarComando(ambienteAtual, jogada);

        return processarComando(comando);
    }

    /**
     * Imprime uma lista de palavras de comando válidos.
     */
    public String mostrarComandos() {

        String comandos = ComandosGerais.mostrarComandos();

        if (ambienteAtual.getMoveis().get("BANCADA") != null) {
            return comandos + "BANCADA";
        }
        return "<html>" + comandos + "</html>";
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o).
     * As opcoes de comando variam se o ambiente possui bancada ou nao.
     * 
     * @param comando O Comando a ser processado.
     * @return Boolean que indica se o jogo deve ser finalizado ou nao.
     * @author Davi Pedro
     */
    public String[] processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            return new String[] { "ERRO", "Eu nao entendi o que voce disse..." };
        }

        String palavraDeComando = comando.getPalavraDeComando().toLowerCase();

        return menuAmbiente(palavraDeComando, comando);
    }

    /**
     * Menu disponivel no ambiente sem a bancada
     * 
     * @param palavraDeComando Primeira palavra do comando fornecido pelo usuario
     * @param comando          O comando completo fornecido pelo usuario
     * @param querSair         Retorno que indica se eh um comando para sair do jogo
     * @return Boolean que indica se o jogo deve ser finalizado ou nao.
     * @author Davi Pedro
     */
    private String[] menuAmbiente(String palavraDeComando, Comando comando) {
        switch (palavraDeComando) {
            case "bancada":
                return new String[] { "BANCADA" };
            case "ajuda":
                return new String[] { "AJUDA" };
            case "ir":
                return irParaAmbiente(comando);
            case "vasculhar":
                return new String[] { "VASCULHAR", comando.getSegundaPalavra() };
            case "sair":
                return new String[] { "SAIR" };
        }

        return new String[] {};
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro. <br/>
     *
     * <br/>
     * Caso o ambiente seja o ambiente com saida e a saida seja a saida da base,
     * e o jogador opte por sair, ele
     *
     * @param comando O comando completo fornecido pelo usuario
     * @author Davi Pedro
     */
    private String[] irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            return new String[] { "ERRO", "Ir pra onde?" };
        }

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);
        if (proximoAmbiente == null) {
            return new String[] { "ERRO", "Nao ha passagem!" };
        } else if (proximoAmbiente.getClass().equals(Torre.class)) {
            return new String[] { "SAIR_BASE" };
        }

        ambienteAtual = proximoAmbiente;

        return new String[] {};
    }

    public Ambiente getAmbienteAtual() {
        return this.ambienteAtual;
    }
}
