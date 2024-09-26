import java.awt.Graphics;

public class Arvore extends ElementosStatics{
    private String peDeFruta;

    public Arvore(int posicaoX, int posicaoY, String peDeFruta){
        super(posicaoX,posicaoY);
        this.peDeFruta = peDeFruta;
    }

    @Override
    public void load(Graphics g){
        g.fillRect(posicaoX, posicaoY, 30, 30);
    }

}
