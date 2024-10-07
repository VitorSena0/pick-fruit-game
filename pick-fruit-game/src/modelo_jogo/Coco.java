package modelo_jogo;
public class Coco extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	public String[] desenho() {
		return null;
	}
	Coco(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}