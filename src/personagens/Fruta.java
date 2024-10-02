import java.awt.Graphics;
import java.util.Random;
import java.util.Set;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Fruta {
    private int x;
    private int y;
    private String nome = "maracuja";
    private boolean visivel = true; // Novo campo para visibilidade
    private Random aleatorio = new Random();
    private int dimensao;
    private Image imagem;
    private int tamanhoMapa;

    public Fruta(int dimensao, int tamanhoMapa, Set<String> posicoesOcupadas) {
        this.tamanhoMapa = tamanhoMapa;
        Random aleatorio = new Random();
        String key;

        // Gera a posição em múltiplos do tamanho da célula
      //  do {
            this.x = aleatorio.nextInt(dimensao) * tamanhoMapa;
            this.y = aleatorio.nextInt(dimensao) * tamanhoMapa;

            // Evita gerar a fruta fora dos limites do mapa
            this.x = Math.min(this.x, (dimensao - 1) * tamanhoMapa);
            this.y = Math.min(this.y, (dimensao - 1) * tamanhoMapa);

          //  key = x + "," + y;
        //} while (posicoesOcupadas.contains(key)); // Verifica se a posição já foi usada

        //posicoesOcupadas.add(key); // Adiciona a posição da fruta às posições ocupadas
        ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "frutaPixelart.png");
        this.imagem = referencia.getImage();
    }

    
    public void load(Graphics g) {
        if (visivel) {
            int tamanho = (int) (this.tamanhoMapa * 0.8); // Ajusta o tamanho da fruta para 50% da célula
            int offsetX = (tamanhoMapa - tamanho) / 2;   // Calcula o offset para centralizar
            int offsetY = (tamanhoMapa - tamanho) / 2;

            g.drawImage(imagem, x + offsetX + tamanhoMapa, y + offsetY + tamanhoMapa, tamanho, tamanho, null);
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
