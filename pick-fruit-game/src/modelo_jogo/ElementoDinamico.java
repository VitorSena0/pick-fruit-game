package modelo_jogo;

/**
 * Representa um elemento dinâmico no jogo, que herda as características básicas de posição de {@link Elemento}
 * e adiciona a capacidade de movimentação.
 */
public abstract class ElementoDinamico extends Elemento{
	
	/**
	 * Move o elemento dinâmico em uma direção especificada.
	 * 
	 * @param direcao O valor inteiro que representa a direção do movimento.
	 */
	public abstract void mover(int direcao);
	/**
	 * Construtor da classe ElementoDinamico.
	 *
	 * @param x A posição X inicial do elemento dinâmico.
	 * @param y A posição Y inicial do elemento dinâmico.
	 */
	ElementoDinamico(int x, int y) {
		super(x, y);
	}
}
