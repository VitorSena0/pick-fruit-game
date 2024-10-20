package modelo_jogo;
public class Amora extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}

	Amora(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}