package modelo_jogo;
public class Acerola extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	public String[] desenho() {
		return null;
	}
	Acerola(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}