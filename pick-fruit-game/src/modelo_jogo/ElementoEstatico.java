package modelo_jogo;

/**
 * Representa um elemento estático no jogo, que herda as características básicas de posição de {@link Elemento}
 * e adiciona um comportamento de interação com o jogador e o terreno.
 */
public abstract class ElementoEstatico extends Elemento {
	
	public abstract void interagir(Jogador jogador, Terreno terreno);

	/**
	 * Construtor da classe ElementoEstatico.
	 *
	 * @param x A posição X inicial do elemento estático.
	 * @param y A posição Y inicial do elemento estático.
	 */
	ElementoEstatico(int x, int y) {
		super(x, y);
	}
}
