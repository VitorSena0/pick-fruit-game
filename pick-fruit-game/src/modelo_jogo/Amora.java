package modelo_jogo;
public class Amora extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	public String[] desenho() {
		return null;
	}
	Amora(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}