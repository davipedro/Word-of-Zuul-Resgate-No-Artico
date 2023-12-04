package src;

public enum BancadaComandos {
    COMPOR("compor"),
    VOLTAR("voltar");

    private String comando;

    BancadaComandos(String comando) {
        this.comando = comando;
    }

    public String comandos() {
        StringBuilder comandos = new StringBuilder();
        for (BancadaComandos comando : BancadaComandos.values()) {
            comandos.append(comando).append(" ");
        }
        return comandos.toString();
    }

    public boolean ehComando(String comando) {
        for (BancadaComandos c : BancadaComandos.values()) {
            if (comando.equalsIgnoreCase(c.comando)) {
                return true;
            }
        }
        return false;
    }
}
