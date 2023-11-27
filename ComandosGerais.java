/**
 * Enum ComandosGerais - Esse enum define e fornece os comandos da bancada.<br/>
 *
 *<br/>Modificado do jogo "World of Zuul": um jogo de aventura muito simples, baseado em texto.<br/>
 *
 * <br/>(Originalmente uma classe do codigo do jogo "World of Zuul" chamada PalavrasComando
 * que foi transformada para um Enum por questoes de padronizacao e por se tratar de um conjunto
 * fixo de elementos).<br/>
 * <br/>Esse enum guarda uma enumeracao de todos os comandos gerais do jogo, ou seja, aqueles que sao
 * reconhecidos em qualquer ambiente do jogo. Ele eh usada no reconhecimento de comandos como
 * eles sao digitados.
 *
 * @author  Davi Pedro - Base: Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2023.12.03 - 2011.07.31 (2016.02.01)
 */
public enum ComandosGerais {
    IR("ir"),
    OLHAR("olhar"),
    VASCULHAR("vasculhar"),
    INVENTARIO("inventario"),
    AJUDA("ajuda"),
    SAIR("sair");

    private final String comando;

    ComandosGerais(String comando){
        this.comando = comando;
    }

    /**
     * Verifica se o comando fornecido eh um comando valido
     * @param umaString um comando a ser validado
     * @return Boolean que indica se o comando eh valido ou nao
     * @author Davi Pedro
     */
    public static boolean ehComandoGeral(String umaString) {
        for (ComandosGerais comandoValido : ComandosGerais.values()) {
            if (umaString.equalsIgnoreCase(comandoValido.comando))
                return true;
        }
        return false;
    }

    /**
     * Mostra todos os comandos disponiveis
     * @return String com os comandos
     * @author Davi Pedro
     */
    public static String mostrarComandos(){
        StringBuilder comandosString = new StringBuilder("Seus comandos sao:\n");
        for (ComandosGerais comando : ComandosGerais.values()) {
            comandosString.append(comando).append(" | ");
        }
        return comandosString.toString();
    }
}
