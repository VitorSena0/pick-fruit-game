package modelo_jogo;
public class Coco extends Fruta{
	public boolean serConsumida(Jogador jogador) {
		return true;
	}

	Coco(int x, int y, boolean bichada) {
		super(x, y, bichada);
	}
}