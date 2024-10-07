package modelo_jogo;
public abstract class Fruta extends ElementoDinamico {
	private boolean bichada;
	public abstract boolean serConsumida(Jogador jogador);
	public boolean temBicho() {
		return bichada;
	}
	public void mover(int direcao) {
		if (direcao < 1 || direcao > 8)
			return;
		int dx = 0;
		int dy = 0;
		if (direcao == 1 || direcao == 2 || direcao == 8)
			dy = 1;
		else if (direcao >= 4 && direcao <= 6)
			dy = -1;
		if (direcao >= 2 && direcao <= 4)
			dx = 1;
		else if(direcao >= 6 && direcao <= 8)
			dx = -1;
		coordenadaX += dx;
		coordenadaY += dy;
	}
	Fruta(int x, int y, boolean bichada) {
		super(x, y);
		this.bichada = bichada;
	}
}
