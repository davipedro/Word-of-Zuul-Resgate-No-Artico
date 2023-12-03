package src;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 * 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class PalavrasComando
{
    // um vetor constante que guarda todas as palavras de comandos validas
    private static final String[] comandosValidos = {
        "ir", "olhar", "vasculhar", "inventario", "ajuda", "sair", "compor"
    };

    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehComando(String umaString)
    {
        for (String comandosValido : comandosValidos) {
            if (comandosValido.equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }

    /**
     * Imprimir todos os comandos válidos para System. 
    */
    public String mostrarTodos(){
        StringBuilder comandosString = new StringBuilder("Seus comandos sao:\n");
        for (String comando : comandosValidos) {
            comandosString.append(comando).append(" ");
        }
        return comandosString.toString();
    }
}
