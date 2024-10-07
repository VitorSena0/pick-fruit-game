package personagens;

import java.awt.Graphics;

public abstract class ElementosStatics {
    protected int posicaoX;  // Corrigir para protected para acesso em subclasses
    protected int posicaoY;  // Corrigir para protected para acesso em subclasses

    public ElementosStatics(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public abstract void load(Graphics g);  // Tornar o método abstrato para que as subclasses implementem
}
