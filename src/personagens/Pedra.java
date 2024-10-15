package personagens;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * A classe {@code Pedra} representa uma rocha no terreno do jogo. 
 * Este é um elemento estático que impede o jogador de passar por ele.
 */
public class Pedra extends ElementosStatics{
	    /**
     * A imagem que representa a rocha no terreno.
     */
	private Image imagem;
        /**
     * A dimensão da célula do terreno onde a rocha está posicionada.
     */
    private int dimensao;
        /**
     * As coordenadas x e y da rocha no terreno.
     */
    private int x, y;
    
    /**
     * Construtor da classe {@code Pedra}. Inicializa a pedra com uma imagem
     * e posiciona-a no terreno.
     *
     * @param posicaoX A posição x da pedra no terreno.
     * @param posicaoY A posição y da pedra no terreno.
     * @param dimensao O tamanho da célula do terreno.
     */
    public Pedra(int posicaoX, int posicaoY, int dimensao) {
        super(posicaoX, posicaoY);
        this.x = posicaoX;
        this.y = posicaoY;
        this.dimensao = dimensao; // Atribui o tamanho da célula
        ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "rochaPixelArt.png");
        this.imagem = referencia.getImage();
    }
    /**
     * Desenha a rocha na tela. O tamanho da rocha é ajustado para 200% do tamanho da célula.
     *
     * @param g O objeto {@link Graphics} usado para desenhar a rocha.
     */
    @Override
    public void load(Graphics g) {
        int size = (int) (dimensao * 2); // Ajusta o tamanho da pedra para 200% da célula
        int offset = (dimensao - size) / 2; // Ajusta o offset para centralizar a pedra

        g.drawImage(imagem, posicaoX - offset, posicaoY - offset, size, size, null);
    }
    /**
     * Retorna a coordenada x da pedra.
     *
     * @return A coordenada x da pedra.
     */
	public int getX() {
		return x;
	}
    /**
     * Retorna a coordenada y da pedra.
     *
     * @return A coordenada y da pedra.
     */
	public int getY() {
		return y;
	}

    public int getDimensao() {
        return dimensao;
    }


    
    
}
