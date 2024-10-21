package modelo_jogo;
public class Pedra extends ElementoEstatico {
	
	private Jogador jogadorEmCima;
	
	public String[] desenho() {
		return null;
	}
	
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}
	
	public Jogador getJogador() {
		return jogadorEmCima;
	}
	
	public void setJogador(Jogador jogador) {
		jogadorEmCima = jogador;
	}
	
	Pedra(int x, int y) {
		super(x, y);
	}
}
