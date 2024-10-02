import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Janela extends JPanel implements KeyListener {
    private Jogador1 player;
    private Fruta fruta1;
    private Pedra[] pedras = new Pedra[10];
    private Arvore[] arvores = new Arvore[15];
    private Set<String> PosicoesUsadas = new HashSet<>();
    private boolean podeMover = true;
    private int dimensao = 15;
    private int proporcaoTelaJogo = 600;
    private Image gramaImage;
    private int cellSize;
    
    public Janela() {
    	this.cellSize = ( proporcaoTelaJogo/ dimensao);
        setFocusable(true);
        addKeyListener(this);
        player = new Jogador1(this.dimensao, this.cellSize);
        fruta1 = new Fruta(this.dimensao, this.cellSize, PosicoesUsadas);

        // Carrega a imagem da grama
        ImageIcon gramaIcon = new ImageIcon("res"+ System.getProperty("file.separator") +"gramaPixelart(1).png");
        if (gramaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erro ao carregar a imagem da grama.");
        }
        gramaImage = gramaIcon.getImage();


        // Inicializa as pedras e árvores com posições aleatórias e ajusta o tamanho delas
        for (int i = 0; i < pedras.length; i++) {
            int[] posicao = generateRandomPosition();
            pedras[i] = new Pedra(posicao[0], posicao[1], cellSize);
        }
        for (int i = 0; i < arvores.length; i++) {
            int[] posicao = generateRandomPosition();
            arvores[i] = new Arvore(posicao[0], posicao[1], "", cellSize);
        }
    }

    public int[] generateRandomPosition() {
        Random random = new Random();
        int x, y;
        String key;

        // Gera coordenadas em múltiplos do tamanho da célula
        do {
            x = random.nextInt(dimensao) * cellSize;
            y = random.nextInt(dimensao) * cellSize;
            key = x + "," + y;
        } while (PosicoesUsadas.contains(key)); // Verifica se a posição já foi usada

        PosicoesUsadas.add(key);

        return new int[]{x, y};
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_UP && podeMover) {
            player.moveUp();
            podeMover = false;
        }
        if (key == KeyEvent.VK_DOWN && podeMover) {
            player.moveDown();
            podeMover = false;
        }
        if (key == KeyEvent.VK_LEFT && podeMover) {
            player.moveLeft();
            podeMover = false;
        }
        if (key == KeyEvent.VK_RIGHT && podeMover) {
            player.moveRight();
            podeMover = false;
        }
        colision();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        podeMover = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = this.proporcaoTelaJogo / dimensao;
    
        // Desenha a grama no fundo
        for (int i = 0; i < this.proporcaoTelaJogo; i += cellSize) {
            for (int a = 0; a < this.proporcaoTelaJogo; a += cellSize) {
                g.drawImage(gramaImage, a+ cellSize, i + cellSize, cellSize, cellSize, this);
            }
        }
    
        // Desenha as pedras (primeiro)
        for (Pedra pedra : pedras) {
            pedra.load(g);
        }
    
        // Desenha o jogador (depois das pedras)
        player.load(g);
    
        // Desenha as árvores (por último, para que fiquem sobre o jogador)
        for (Arvore arvore : arvores) {
            arvore.load(g);
        }
    
        // Desenha a fruta (não precisa alterar)
        fruta1.load(g);
    }
    
    

    public void colision() {
        int playerWidth = player.getWidth();
        int playerHeight = player.getHeigth();
        int frutaSize = (int) (cellSize * 0.8); // Tamanho da fruta proporcional à célula

        // Calcula o centro da fruta para garantir que a verificação de colisão seja precisa
        int frutaCenterX = fruta1.getX() + (cellSize - frutaSize) / 2;
        int frutaCenterY = fruta1.getY() + (cellSize - frutaSize) / 2;

        // Verifica se há sobreposição nas coordenadas X e Y
        boolean xOverlap = (player.getX() + cellSize < frutaCenterX + frutaSize + cellSize) && (player.getX() + playerWidth + cellSize > frutaCenterX + cellSize);
        boolean yOverlap = (player.getY() + cellSize < frutaCenterY + frutaSize + cellSize) && (player.getY() + cellSize + playerHeight > frutaCenterY + cellSize);

        // Se houver sobreposição em ambos os eixos, o jogador coletou a fruta
        if (xOverlap && yOverlap) {
            player.catouFruta(fruta1);
            fruta1.setVisivel(false);
            fruta1 = new Fruta(this.dimensao, this.cellSize, PosicoesUsadas); // Gera uma nova fruta em uma posição válida
            System.out.println(player.frutasColetadas());
        }
    }



}
