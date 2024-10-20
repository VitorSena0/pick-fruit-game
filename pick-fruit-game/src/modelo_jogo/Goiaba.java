package modelo_jogo;
public class Goiaba extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}

	Goiaba(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}