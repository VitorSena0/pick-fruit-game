package personagens;
import java.awt.Graphics;

/**
 * A classe abstrata {@code ElementosStatics} define a estrutura básica para todos os elementos
 * estáticos do jogo, como árvores, pedras e outros objetos que permanecem em posições fixas no terreno.
 * 
 * <p>Subclasses desta classe devem implementar o método {@link #load(Graphics)} para desenhar o elemento
 * na tela.</p>
 */
public abstract class ElementosStatics {
    /**
     * A coordenada x da posição do elemento no terreno.
     */
    protected int posicaoX;

    /**
     * A coordenada y da posição do elemento no terreno.
     */
    protected int posicaoY;

    /**
     * Construtor da classe {@code ElementosStatics}. Inicializa o elemento com sua posição x e y no terreno.
     * 
     * @param posicaoX A coordenada x da posição do elemento.
     * @param posicaoY A coordenada y da posição do elemento.
     */
    public ElementosStatics(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    /**
     * Método abstrato para carregar o elemento na tela. Cada elemento específico, como árvores ou pedras,
     * deve fornecer sua própria implementação para este método.
     * 
     * @param g O objeto {@link Graphics} usado para desenhar o elemento.
     */
    public abstract void load(Graphics g);  // Tornar o método abstrato para que as subclasses implementem
}
