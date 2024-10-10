package personagens;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A classe {@code Arvore} representa uma árvore no jogo, que pode ser de um tipo específico de fruta.
 * Esta classe herda da classe {@link ElementosStatics}, que define a posição estática de elementos no terreno.
 * 
 * <p>A árvore é desenhada na tela usando uma imagem específica e suas dimensões são definidas pelo
 * tamanho da célula em que está inserida.</p>
 */

public class Arvore extends ElementosStatics{
    private String peDeFruta; // Tipo de fruta que a árvore produz
    private Image imagem;      // Imagem da árvore
    private int dimensao;      // Dimensão da célula da árvore
    private int x;            // Posição x da árvore
    private int y;            // Posição y da árvore

    /**
     * Construtor da classe {@code Arvore}. Inicializa uma nova instância de árvore com uma posição,
     * tipo de fruta e dimensões especificadas.
     * 
     * @param posicaoX   A coordenada x da posição da árvore.
     * @param posicaoY   A coordenada y da posição da árvore.
     * @param peDeFruta  O tipo de fruta que a árvore produz.
     * @param dimensao   A dimensão da célula da árvore.
     */

    public Arvore(int posicaoX, int posicaoY, String peDeFruta, int dimensao) {
        super(posicaoX, posicaoY);
        this.peDeFruta = peDeFruta;
        this.dimensao = dimensao; // Atribui o tamanho da célula
        ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "arvorePixelart.png");
        this.imagem = referencia.getImage();
    }
    
    /**
     * Desenha a árvore na tela usando as coordenadas e dimensões especificadas.
     * O tamanho da imagem é ajustado para 200% da célula e centralizado na posição dada.
     * 
     * @param g O objeto {@link Graphics} usado para desenhar a imagem da árvore.
     */

    @Override
    public void load(Graphics g) {
        int tamanho = (int) (dimensao * 2); // Ajusta o tamanho da árvore para 200% da célula
        int offset = (dimensao - tamanho) / 2; // Ajusta o offset para centralizar a árvore

        g.drawImage(imagem, posicaoX - offset, posicaoY - offset, tamanho, tamanho, null);
    }

     /**
     * Retorna a coordenada x da árvore.
     * 
     * @return A coordenada x da árvore.
     */

	public int getX() {
		return x;
	}

      /**
     * Retorna a coordenada y da árvore.
     * 
     * @return A coordenada y da árvore.
     */

	public int getY() {
		return y;
	}
    
    
}