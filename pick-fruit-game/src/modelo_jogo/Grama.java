package modelo_jogo;

/**
 * A classe Grama representa um elemento estático no terreno do jogo.
 * Cada instância de Grama pode conter uma árvore, uma fruta e um jogador em cima.
 * Esta classe também permite a interação do jogador com o elemento.
 */
public class Grama extends ElementoEstatico {
	private Arvore arvore;
	private Fruta fruta;
	private Jogador jogadorEmCima;
	
	 /**
     * Interage com um jogador que está sobre a grama.
     * O comportamento da interação deve ser definido na implementação.
     *
     * @param jogador O jogador que está interagindo com a grama.
     * @param terreno O terreno onde a grama está localizada.
     */
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}
	public Fruta getFruta() {
		return fruta;
	}
	public Jogador getJogador() {
		return jogadorEmCima;
	}
	public Arvore getArvore() {
		return arvore;
	}
	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}
	public void setJogador(Jogador jogador) {
		jogadorEmCima = jogador;
	}
	
	  
    /**
     * Constrói uma instância de Grama sem árvore ou fruta.
     *
     * @param x A coordenada x da grama no terreno.
     * @param y A coordenada y da grama no terreno.
     */
	Grama(int x, int y) {
		super(x, y);
		this.arvore = null;
		this.fruta = null;
	}

	  /**
     * Constrói uma instância de Grama com uma árvore.
     *
     * @param x A coordenada x da grama no terreno.
     * @param y A coordenada y da grama no terreno.
     * @param arvore A árvore a ser associada a esta grama.
     */
	Grama(int x, int y, Arvore arvore) {
		super(x, y);
		this.arvore = arvore;
		fruta = null;
	}
}
