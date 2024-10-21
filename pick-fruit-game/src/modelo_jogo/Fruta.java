package modelo_jogo;
public abstract class Fruta extends ElementoDinamico {
	private boolean bichada;
	public abstract boolean serConsumida(Jogador jogador);
	public boolean temBicho() {
		return bichada;
	}
	public void mover(int direcao) {
		int dX = 0;
		int dY = 0;
		if (direcao == 1) {
			dY -= 1;
		}
		else if (direcao == 2) {
			dX -= 1;
		}
		else if (direcao == 3) {
			dX += 1;
		}
		else if (direcao == 4) {
			dY += 1;
		}
		else if (direcao == 5) {
			dX -= 1;
			dY -= 1;
		}
		else if (direcao == 6) {
			dX += 1;
			dY -= 1;
		}
		else if (direcao == 7) {
			dX -= 1;
			dY += 1;
		}
		else if (direcao == 8) {
			dX += 1;
			dY += 1;
		}
		coordenadaX += dX;
		coordenadaY += dY;
	}
	Fruta(int x, int y, boolean bichada) {
		super(x, y);
		this.bichada = bichada;
	}
}
