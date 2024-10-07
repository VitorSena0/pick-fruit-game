package modelo_jogo;
public class Maracuja extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return false;
	}
	public String[] desenho() {
		return null;
	}
	Maracuja(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}