import java.awt.Graphics;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Fruta {
    private int x;
    private int y;
    private String nome = "maracuja";
    private boolean visivel = true; // Novo campo para visibilidade
    private Random aleatorio = new Random();
    private int cellSize; // Tamanho da celula
    private int dimensao;
    private Image imagem;

    public Fruta(int dimensao) {
        // Gera posicaoo em multiplos do tamanho da celula
        this.x = aleatorio.nextInt(6) * cellSize;
        this.y = aleatorio.nextInt(6) * cellSize;
        ImageIcon referencia = new ImageIcon("res\\frutaPixelart.png");
        this.dimensao = dimensao;
        this.imagem = referencia.getImage();
        this.cellSize = 800 / dimensao; // 800 é o tamanho da janela
        // Ajusta a posição para o centro da célula
        this.x += cellSize / 2 - 50; // -5 para centralizar o círculo de tamanho 10
        this.y += cellSize / 2 - 50; // -5 para centralizar o círculo de tamanho 10
    }
    
    public void load(Graphics g) {
        if (visivel) {
            g.drawImage(imagem, x, y, 100, 100, null);
        }
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
