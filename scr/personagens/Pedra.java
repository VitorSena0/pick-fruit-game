import java.awt.Graphics;

public class Pedra extends ElementosStatics{
    
    public Pedra(int posicaoX, int posicaoY){
        super(posicaoX, posicaoY);
    }

    @Override
    public void load(Graphics g){
        g.fillOval(posicaoX, posicaoY, 30, 30);
    }
}
