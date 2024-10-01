import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pedra extends ElementosStatics{
	
	private Image imagem;
    private int dimensao;
    
    public Pedra(int posicaoX, int posicaoY, int dimensao) {
        super(posicaoX, posicaoY);
        this.dimensao = dimensao; // Atribui o tamanho da célula
        ImageIcon referencia = new ImageIcon("res\\rochaPixelArt.png");
        this.imagem = referencia.getImage();
    }

    @Override
    public void load(Graphics g) {
        int size = (int) (dimensao * 2); // Ajusta o tamanho da pedra para 200% da célula
        int offset = (dimensao - size) / 2; // Ajusta o offset para centralizar a pedra

        g.drawImage(imagem, posicaoX - offset, posicaoY - offset, size, size, null);
    }
}
