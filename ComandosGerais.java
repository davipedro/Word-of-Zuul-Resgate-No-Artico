/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves) - Modificada por Davi Pedro
 * @version 2011.07.31 (2016.02.01)
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

    public static boolean ehComandoGeral(String umaString) {
        for (ComandosGerais comandoValido : ComandosGerais.values()) {
            if (umaString.equalsIgnoreCase(comandoValido.comando))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }

    public static String mostrarComandos(){
        StringBuilder comandosString = new StringBuilder("Seus comandos sao:\n");
        for (ComandosGerais comando : ComandosGerais.values()) {
            comandosString.append(comando).append(" ");
        }
        return comandosString.toString();
    }
}
