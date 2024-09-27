import java.awt.Graphics;
import java.util.Random;

public class Fruta {
    private int x;
    private int y;
    private String nome = "maracuja";
    private boolean visivel = true; // Novo campo para visibilidade
    private Random aleatorio = new Random();
    private int cellSize = 800 / 6; // Tamanho da célula

    public Fruta() {
        // Gera posição em múltiplos do tamanho da célula
        this.x = aleatorio.nextInt(6) * cellSize;
        this.y = aleatorio.nextInt(6) * cellSize;

        // Ajusta a posição para o centro da célula
        this.x += cellSize / 2 - 5; // -5 para centralizar o círculo de tamanho 10
        this.y += cellSize / 2 - 5; // -5 para centralizar o círculo de tamanho 10
    }
    
    public void load(Graphics g) {
        if (visivel) { // Verifica a visibilidade antes de desenhar
            g.fillOval(x, y, 10, 10);
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
