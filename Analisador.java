import java.util.Scanner;

/**
 * Classe Analisador - analisa as entradas do jogador.<br/>
 *
 * <br/>Modificada do jogo "World of Zuul": um jogo de aventura muito simples, baseado em texto.<br/>
 * 
 * <br/> Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando. <br/>
 *
 * <br/> O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 *
 * @author  Davi Pedro - Base: Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2023.12.03 - 2011.07.31 (2016.02.01)
 */
public class Analisador 
{
    private Scanner entrada;

    /**
     * Cria um analisador para ler do terminal.
     * @author Davi Pedro
     */
    public Analisador() 
    {
        entrada = new Scanner(System.in);
    }

    /**
     * Recebe e analisa a entrada do usuario, guardando a primeira e
     * a segunda palavra, verifica se o ambiente
     * eh um ambiente com bancada e entao gera um comando normal ou um
     * comando nulo
     * @param ambienteLocal O ambiente atual que o jogador esta
     * @param ambienteComBancada O ambiente que possui bancada no jogo
     * @return O comando do usuario
     * @author Davi Pedro
     */
    public Comando pegarComando(String ambienteLocal, String ambienteComBancada)
    {
        String linha;
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");

        linha = entrada.nextLine();

        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();
            }
        }
        tokenizer.close();

        boolean bancada = ambienteLocal.equalsIgnoreCase(ambienteComBancada);

        if (bancada){
            if(ComandosBancada.ehComandoBancada(palavra1) || ComandosGerais.ehComandoGeral(palavra1)){
                return new Comando(palavra1, palavra2);
            }
            else {
                return new Comando(null, palavra2);
            }
        } else {
            if(ComandosGerais.ehComandoGeral(palavra1)) {
                return new Comando(palavra1, palavra2);
            }
            else {
                return new Comando(null, palavra2);
            }
        }
    }

    /**
     * Imprime uma lista de palavras de comando válidos.
     * @author Davi Pedro
     */
    public String mostrarComandos(Ambiente ambienteAtual){

        if (ambienteAtual.getNome().equalsIgnoreCase(Jogo.getAmbienteComBancada())){
            return ComandosGerais.mostrarComandos() + "BANCADA";
        }
        return ComandosGerais.mostrarComandos();
    }
}
