package personagens;
import java.awt.Graphics;
import java.util.Random;
import java.util.Set;

/**
 * A classe abstrata {@code Fruta} representa uma fruta no jogo, com suas coordenadas e imagem associadas.
 * Frutas são colocadas aleatoriamente no mapa, em posições que não estão ocupadas por outros elementos.
 * 
 * <p>Subclasses desta classe devem implementar o método {@link #load(Graphics)} para desenhar a fruta na tela.</p>
 */

public abstract class Fruta {
    protected int x;
    protected int y;
    protected boolean visivel = true; // Novo campo para visibilidade
    protected Random aleatorio = new Random();
    protected int dimensaoGrid;

    /**
     * Construtor da classe {@code Fruta}. Gera uma posição aleatória para a fruta dentro dos limites
     * do terreno, evitando posições ocupadas por outros elementos.
     * 
     * @param dimensao O tamanho da área disponível para colocar a fruta.
     * @param dimensaoGrid O tamanho da célula no terreno (grid) onde a fruta será posicionada.
     * @param posicoesOcupadas Um conjunto de strings que contém as posições já ocupadas no terreno.
     * @param bichada A probabilidade da fruta ser bichada.
     */

    public Fruta(int dimensao, int dimensaoGrid, Set<String> posicoesOcupadas,int bichada) {
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

    }
  /**
     * Método abstrato para carregar a fruta na tela. As subclasses devem fornecer sua própria implementação.
     * 
     * @param g O objeto {@link Graphics} usado para desenhar a fruta.
     */
    public abstract void load(Graphics g);

    /**
     * Define se a fruta está visível ou não no terreno. Se a fruta ficar invisível, sua posição é removida
     * do conjunto de posições ocupadas.
     * 
     * @param visivel Define se a fruta deve ser visível.
     * @param posicoesUsadas Conjunto de posições usadas para atualização.
     */

    public void setVisivel(boolean visivel, Set<String> posicoesUsadas) {
        this.visivel = visivel;
        if (!visivel) {
            posicoesUsadas.remove("(" + this.x + "," + this.y + ")");
        }   
    }
    /**
     * Verifica se a fruta está visível no terreno.
     * 
     * @return {@code true} se a fruta estiver visível, {@code false} caso contrário.
     */

    public boolean ehVisivel(){
    return this.visivel;
    }
     /**
     * Obtém a coordenada x da fruta no terreno.
     * 
     * @return A coordenada x da fruta.
     */

    public int getX() {
        return this.x;
    }

    /**
     * Obtém a coordenada y da fruta no terreno.
     * 
     * @return A coordenada y da fruta.
     */

    public int getY() {
        return this.y;
    }

    /**
     * Retorna o nome da fruta com base no nome da classe.
     * 
     * @return O nome da fruta.
     */

    public String getNomeFruta(){
        return this.getClass().getSimpleName();
    }
}