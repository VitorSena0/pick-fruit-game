package modelo_jogo;
public class Laranja extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		jogador.setDoente(false);
		return true;
	}

	Laranja(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}