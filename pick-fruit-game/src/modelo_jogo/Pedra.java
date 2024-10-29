package modelo_jogo;
/**
 * A classe Pedra representa um elemento estático do tipo pedra em um terreno de jogo.
 * As pedras podem interagir com jogadores que se posicionam sobre elas.
 */
public class Pedra extends ElementoEstatico {

    private Jogador jogadorEmCima;

    /**
     * Retorna a representação gráfica da pedra.
     *
     * @return Um array de strings representando o desenho da pedra.
     */
	public String[] desenho() {
		return null;
	}

	/**
     * Permite que um jogador interaja com a pedra.
     * Esta interação é definida de acordo com as regras do jogo.
     *
     * @param jogador O jogador que está interagindo com a pedra.
     * @param terreno O terreno onde a pedra está localizada.
     */
	public void interagir(Jogador jogador, Terreno terreno) {
		
	}
	
	 /**
     * Retorna o jogador que está atualmente em cima da pedra.
     *
     * @return O jogador em cima da pedra, ou null se não houver jogador.
     */
	public Jogador getJogador() {
		return jogadorEmCima;
	}
	
	/**
     * Define o jogador que está em cima da pedra.
     *
     * @param jogador O jogador a ser posicionado em cima da pedra.
     */
	public void setJogador(Jogador jogador) {
		jogadorEmCima = jogador;
	}
	
	/**
     * Construtor para criar uma nova pedra em uma posição específica.
     *
     * @param x A coordenada x da pedra no terreno.
     * @param y A coordenada y da pedra no terreno.
     */
	Pedra(int x, int y) {
		super(x, y);
	}
}
