package modelo_jogo;

/**
 * Representa uma fruta no jogo, que pode estar bichada e que possui movimentos e interações com o jogador.
 */
public abstract class Fruta extends ElementoDinamico {
	private boolean bichada;
	/**
	* Define a ação de ser consumida pelo jogador.
	*
	* @param jogador Jogador que tenta consumir a fruta.
	* @return {@code true} se a fruta foi consumida com sucesso; {@code false} caso contrário.
	*/
	public abstract boolean serConsumida(Jogador jogador);


	/**
	 * Verifica se a fruta está bichada.
	 *
	 * @return {@code true} se a fruta está bichada; {@code false} caso contrário.
	 */
	public boolean temBicho() {
		return bichada;
	}

	/**
	 * Move a fruta em uma das direções indicadas.
	 *
	 * @param direcao Direção para mover a fruta:
	 *                <ul>
	 *                <li>1 - para cima</li>
	 *                <li>2 - para esquerda</li>
	 *                <li>3 - para direita</li>
	 *                <li>4 - para baixo</li>
	 *                <li>5 - diagonal superior esquerda</li>
	 *                <li>6 - diagonal superior direita</li>
	 *                <li>7 - diagonal inferior esquerda</li>
	 *                <li>8 - diagonal inferior direita</li>
	 *                </ul>
	 */
	@Override
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

	/**
	 * Construtor da classe Fruta.
	 *
	 * @param x       A posição X inicial da fruta.
	 * @param y       A posição Y inicial da fruta.
	 * @param bichada Indica se a fruta está bichada (contém bichos).
	 */
	Fruta(int x, int y, boolean bichada) {
		super(x, y);
		this.bichada = bichada;
	}
}
