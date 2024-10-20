package modelo_jogo;
public class Abacate extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	Abacate(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}