public enum ComandosBancada {

    BANCADA("bancada"),
    COMPOR("compor"),
    AJUDA("ajuda"),
    VOLTAR("voltar");

    private final String comando;

    ComandosBancada(String comando){
        this.comando = comando;
    }

    public static boolean ehComandoBancada(String comando){
        for (ComandosBancada c : ComandosBancada.values()){
            if (comando.equalsIgnoreCase(c.comando)){
                return true;
            }
        }
        return false;
    }
}
