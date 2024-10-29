package modelo_jogo;

/**
 * Representa uma árvore no jogo que possui um cronômetro interno e a capacidade de gerar frutas.
 * Esta classe herda as características de posição de {@link Elemento}.
 */
public abstract class Arvore extends Elemento {
	private int cronometro;

	/**
	 * Retorna o valor atual do cronômetro da árvore.
	 *
	 * @return O valor do cronômetro.
	 */
	
	public int getCronometro() {
		return cronometro;
	}

	/**
	 * Atualiza o cronômetro, reduzindo-o em uma unidade caso seja maior que zero.
	 */
	public void atualizarCronometro() {
		if (cronometro > 0)
			cronometro -= 1;
	}

	/**
	 * Inicia o cronômetro da árvore com um valor inicial de 5.
	 */
	public void iniciarCronometro() {
		cronometro = 5;
	}

	/**
	 * Interage com o jogador e o terreno. Este método pode ser sobrescrito para definir interações específicas.
	 *
	 * @param jogador O jogador que interage com a árvore.
	 * @param terreno O terreno onde ocorre a interação.
	 */
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}

	/**
	 * Gera uma fruta associada à árvore, com a possibilidade de ser bichada ou de ouro.
	 * Este método deve ser implementado por subclasses para definir a fruta específica gerada.
	 *
	 * @param bichada Indica se a fruta gerada possui um bicho.
	 * @param ouro Indica se a fruta gerada é de ouro.
	 * @return Uma instância de {@link Fruta} gerada pela árvore.
	 */
	public abstract Fruta gerarFruta(boolean bichada, boolean ouro);
	
	/**
	 * Construtor da classe Arvore que inicializa a posição da árvore e define o cronômetro em zero.
	 *
	 * @param x A posição X inicial da árvore.
	 * @param y A posição Y inicial da árvore.
	 */
	Arvore(int x, int y) {
		super(x, y);
		cronometro = 0;
	}
}
