package modelo_jogo;
public abstract class Arvore extends ElementoEstatico {
	private int cronometro;
	public void atualizarCronometro() {
		if (cronometro > 0)
			cronometro -= 1;
	}
	public String[] desenho() {
		return null;
	}
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}
	public abstract Fruta gerarFruta(boolean bichada, boolean ouro);
	Arvore(int x, int y) {
		super(x, y);
		cronometro = 0;
	}
}
