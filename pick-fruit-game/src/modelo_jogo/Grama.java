package modelo_jogo;
public class Grama extends ElementoEstatico {
	private Arvore arvore;
	private Fruta fruta;
	private Jogador jogadorEmCima;
	
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}
	public Fruta getFruta() {
		return fruta;
	}
	public Jogador getJogador() {
		return jogadorEmCima;
	}
	public Arvore getArvore() {
		return arvore;
	}
	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}
	public void setJogador(Jogador jogador) {
		jogadorEmCima = jogador;
	}
	
	Grama(int x, int y) {
		super(x, y);
		this.arvore = null;
		this.fruta = null;
	}
	Grama(int x, int y, Arvore arvore) {
		super(x, y);
		this.arvore = arvore;
		fruta = null;
	}
}
