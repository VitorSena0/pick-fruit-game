package personagens;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * A classe {@code Arvore} representa uma árvore no jogo, que pode ser de um tipo específico de fruta.
 * Esta classe herda da classe {@link ElementosStatics}, que define a posição estática de elementos no terreno.
 * 
 * <p>A árvore é desenhada na tela usando uma imagem específica e suas dimensões são definidas pelo
 * tamanho da célula em que está inserida.</p>
 */

public class Arvore extends ElementosEstaticos{
    private String peDeFruta; // Tipo de fruta que a árvore produz
    private static ImageIcon imagem = new ImageIcon("res" + System.getProperty("file.separator") + "arvorePixelart.png");      // Imagem da árvore

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
        super(posicaoX, posicaoY,dimensao,imagem);
        this.peDeFruta = peDeFruta;
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

        g.drawImage(getImage(), posicaoX - offset, posicaoY - offset, tamanho, tamanho, null);
    }


	@Override
	public void acao() {
		// TODO Auto-generated method stub
		
	}
    
    
}