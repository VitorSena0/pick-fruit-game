import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;	

public class Arvore extends ElementosStatics{
    private String peDeFruta;
    private Image imagem;
    private int dimensao;

    public Arvore(int posicaoX, int posicaoY, String peDeFruta, int dimensao) {
        super(posicaoX, posicaoY);
        this.peDeFruta = peDeFruta;
        this.dimensao = dimensao; // Atribui o tamanho da célula
        ImageIcon referencia = new ImageIcon("res\\arvorePixelart.png");
        this.imagem = referencia.getImage();
    }

    @Override
    public void load(Graphics g) {
        int tamanho = (int) (dimensao * 2); // Ajusta o tamanho da árvore para 200% da célula
        int offset = (dimensao - tamanho) / 2; // Ajusta o offset para centralizar a árvore

        g.drawImage(imagem, posicaoX - offset, posicaoY - offset, tamanho, tamanho, null);
    }
}