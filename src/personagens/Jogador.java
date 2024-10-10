package personagens;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * A classe {@code Jogador} representa o personagem controlado pelo jogador no jogo "Cata Fruta".
 * Ela mantém a posição do jogador no mapa e a quantidade de frutas coletadas. Cada jogador
 * pode se mover pelo terreno e coletar frutas.
 */
public class Jogador{

	/**
     * O tipo de jogador, que pode ser "player1" ou "player2".
     */
    private String tipoJogador;

    /**
     * A posição x do jogador no terreno.
     */
    private int x;

    /**
     * A posição y do jogador no terreno.
     */
    private int y;

    /**
     * A largura do jogador.
     */
    private int width;

    /**
     * A altura do jogador.
     */
    private int heigth;

    /**
     * O array de frutas coletadas pelo jogador, com um limite de até 99 frutas.
     */
    private Fruta[] frutas = new Fruta[99];

    /**
     * O número de frutas coletadas pelo jogador.
     */
    protected int qtdFrutasColetadas = 0;

    /**
     * Gerador de números aleatórios usado para determinar a posição inicial do jogador.
     */
    private Random aleatorio = new Random();

    /**
     * A imagem que representa o jogador.
     */
    private Image imagem;

    /**
     * A dimensão do terreno.
     */
    private int dimensao;

    /**
     * A dimensão da célula (grid) no terreno.
     */
    private int dimensaoGrid;

    /**
     * Construtor da classe {@code Jogador}. Inicializa o jogador com uma posição aleatória no mapa e carrega
     * a imagem correspondente ao tipo de jogador.
     *
     * @param dimensao O tamanho do terreno.
     * @param dimensaoGrid O tamanho da célula do terreno.
     * @param tipoJogador O tipo de jogador, 1 para "player1" e 2 para "player2".
     */
	
	public Jogador(int dimensao,int dimensaoGrid, int tipoJogador) {
		this.tipoJogador = (tipoJogador == 1)? "player1" : "player2";
		this.dimensao = dimensao;
		this.dimensaoGrid = dimensaoGrid;
		this.x = aleatorio.nextInt(this.dimensao) * this.dimensaoGrid;
		this.y = aleatorio.nextInt(this.dimensao) * this.dimensaoGrid;
        ImageIcon referencia = (tipoJogador == 1)? new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png") : new ImageIcon("res" + System.getProperty("file.separator") + "player2Pixelart.png");
        this.imagem = referencia.getImage();
		this.width = dimensao + 1;
		this.heigth = dimensao + 1;
	}

	 /**
     * Desenha o jogador na tela no tamanho proporcional à célula do terreno.
     * 
     * @param g O objeto {@link Graphics} usado para desenhar o jogador.
     */

	public void load(Graphics g) {
	    int tamanho = (int) (this.dimensaoGrid * 0.8); // Ajusta o tamanho para 80% da célula
	    int tamanhoX = tamanho + (int)(tamanho*0.3); 
	    int offsetX = (dimensaoGrid - tamanho) / 2;   // Calcula o offset para centralizar
	    int offsetY = (dimensaoGrid - tamanho) / 2;

	    g.drawImage(imagem, x + offsetX + dimensaoGrid, y + offsetY + dimensaoGrid, tamanhoX, tamanho, null);
	}

	/**
     * Move o jogador para cima, se ele não ultrapassar os limites do mapa.
     */

    public void moveUp() {
        if (y - dimensaoGrid >= 0) { // Verifica se não ultrapassa o limite superior
            y -= dimensaoGrid;
        }
    }

	 /**
     * Move o jogador para baixo, se ele não ultrapassar os limites do mapa.
     */

    public void moveDown() {
        if (y + dimensaoGrid < dimensao * dimensaoGrid) { // Verifica se não ultrapassa o limite inferior
            y += dimensaoGrid;
        }
    }

	/**
     * Move o jogador para a esquerda, se ele não ultrapassar os limites do mapa.
     */

    public void moveLeft() {
        if (x - dimensaoGrid >= 0) { // Verifica se não ultrapassa o limite esquerdo
            x -= dimensaoGrid;
        }
    }

	 /**
     * Move o jogador para a direita, se ele não ultrapassar os limites do mapa.
     */

    public void moveRight() {
        if (x + dimensaoGrid < dimensao * dimensaoGrid) { // Verifica se não ultrapassa o limite direito
            x += dimensaoGrid;
        }
    }
	
	 /**
     * Retorna a posição x do jogador.
     *
     * @return A coordenada x do jogador.
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna a posição y do jogador.
     *
     * @return A coordenada y do jogador.
     */
    public int getY() {
        return y;
    }

    /**
     * Retorna a largura do jogador.
     *
     * @return A largura do jogador.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retorna a altura do jogador.
     *
     * @return A altura do jogador.
     */
    public int getHeigth() {
        return heigth;
    }

    /**
     * Retorna as frutas coletadas pelo jogador.
     *
     * @return Um array de frutas coletadas.
     */
    public Fruta[] getFrutas() {
        return this.frutas;
    }

    /**
     * Retorna o tipo de jogador, "player1" ou "player2".
     *
     * @return O tipo de jogador.
     */

	public String getTipoJogador(){
		return this.tipoJogador;
	}
	/**
     * Registra que o jogador coletou uma fruta. A fruta é removida do terreno e adicionada ao inventário do jogador.
     *
     * @param a A fruta que foi coletada.
     * @param posicoesOcupadas Conjunto de posições ocupadas no terreno.
     */
    public void catouFruta(Fruta a, Set<String> posicoesOcupadas) {
        frutas[qtdFrutasColetadas] = a;
        a.setVisivel(false, posicoesOcupadas); // Marca a fruta como invisível
        qtdFrutasColetadas++;
    }

    /**
     * Retorna a quantidade de frutas coletadas pelo jogador.
     *
     * @return O número de frutas coletadas.
     */
    public int frutasColetadas() {
        return qtdFrutasColetadas;
    }

}
