package modelo_jogo;

public class Jogo {
	private Terreno terreno;
	private Jogador[] jogadores;
	private int rodada;
	
	Jogo(Terreno terreno, int numeroJogadores, int capacidadeMochila) {
		this.terreno = terreno;
		jogadores = new Jogador[numeroJogadores];
	}
}
