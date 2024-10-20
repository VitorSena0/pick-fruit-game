package modelo_jogo;
public class Acerola extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	
	Acerola(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}