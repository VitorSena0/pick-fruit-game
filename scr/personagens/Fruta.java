import java.awt.Graphics;

public class Fruta {
    private int x;
    private int y;
    private String nome = "maracuja";
    private boolean visivel = true; // Novo campo para visibilidade

    public Fruta() {
        this.x = (int)(Math.random()*800);
        this.y = (int)(Math.random()*800);
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
