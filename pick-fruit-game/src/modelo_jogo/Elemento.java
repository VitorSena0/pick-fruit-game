package modelo_jogo;

/**
 * Representa um elemento genérico no jogo, com coordenadas X e Y para indicar sua posição no espaço do jogo.
 */
public abstract class Elemento {
	protected int coordenadaX;
	protected int coordenadaY;

	public int getX() {
		return coordenadaX;
	}
	public int getY() {
		return coordenadaY;
	}

	/**
	 * Construtor da classe Elemento.
	 *
	 * @param x A posição X inicial do elemento. Se for menor que 0, a posição X será definida como 0.
	 * @param y A posição Y inicial do elemento. Se for menor que 0, a posição Y será definida como 0.
	 */
	Elemento(int x, int y) {
		if (x >= 0)
			coordenadaX = x;
		else
			coordenadaX = 0;
		if (y >= 0)
			coordenadaY = y;
		else
			coordenadaY = 0;
	}
}