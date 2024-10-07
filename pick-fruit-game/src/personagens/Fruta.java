package personagens;

import java.awt.Graphics;
import java.util.Random;
import java.util.Set;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Fruta {
    protected int x;
    protected int y;
    protected boolean visivel = true; // Novo campo para visibilidade
    protected Random aleatorio = new Random();
    protected Image imagem;
    protected int dimensaoGrid;

    public Fruta(int dimensao, int dimensaoGrid, Set<String> posicoesOcupadas) {
        this.dimensaoGrid = dimensaoGrid;
        String key;

        // Gera a posição em múltiplos do tamanho da célula
        do {
            this.x = aleatorio.nextInt(dimensao) * dimensaoGrid;
            this.y = aleatorio.nextInt(dimensao) * dimensaoGrid;

            // Evita gerar a fruta fora dos limites do mapa
            this.x = Math.min(this.x, (dimensao - 1) * dimensaoGrid);
            this.y = Math.min(this.y, (dimensao - 1) * dimensaoGrid);

            key = "(" + x + "," + y + ")"; // Cria a chave da posição
        } while (posicoesOcupadas.contains(key)); // Verifica se a posição está ocupada

        posicoesOcupadas.add(key); // Adiciona a posição da fruta às posições ocupadas
        ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "frutaPixelart.png");
        this.imagem = referencia.getImage();
    }

    public abstract void load(Graphics g);

    public void setVisivel(boolean visive, Set<String> posicoesUsadas) {
        this.visivel = visivel;
        posicoesUsadas.remove("(" + x + "," + y + ")");
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getNomeFruta(){
        return this.getClass().getSimpleName();
    }
}