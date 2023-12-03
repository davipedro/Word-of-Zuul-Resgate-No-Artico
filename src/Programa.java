package src;

import java.io.IOException;

public class Programa {

	public static void main(String[] args) throws IOException {
		// Tela interfaceGrafica = Tela.getInstance();
		// interfaceGrafica.exibirTela();

		Jogo jogo = new Jogo();
		
		jogo.jogar();
	}

}
