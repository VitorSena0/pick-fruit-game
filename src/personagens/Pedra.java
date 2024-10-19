package personagens;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * A classe {@code Pedra} representa uma rocha no terreno do jogo. 
 * Este é um elemento estático que impede o jogador de passar por ele.
 */
public class Pedra extends ElementosEstaticos{
	    /**
     * A imagem que representa a rocha no terreno.
     */
	private static ImageIcon referencia= new ImageIcon("res" + System.getProperty("file.separator") + "rochaPixelArt.png");
    
    /**
     * Construtor da classe {@code Pedra}. Inicializa a pedra com uma imagem
     * e posiciona-a no terreno.
     *
     * @param posicaoX A posição x da pedra no terreno.
     * @param posicaoY A posição y da pedra no terreno.
     * @param dimensao O tamanho da célula do terreno.
     */
    public Pedra(int posicaoX, int posicaoY, int dimensao) {
        super(posicaoX,posicaoY,dimensao,referencia);
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

        g.drawImage(getImage(), posicaoX - offset, posicaoY - offset, size, size, null);
    }

    public int getDimensao() {
        return dimensao;
    }
	@Override
	public void acao() {
		// decrementa o número de jogadas do jogador
		
	}


    
    
}
