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
    private int dimensao = 5;
    private Image gramaImage;
    
    public Janela() {
        setFocusable(true);
        addKeyListener(this);
        player = new Jogador1(this.dimensao);
        fruta1 = new Fruta(this.dimensao);

        // Carrega a imagem da grama
        ImageIcon gramaIcon = new ImageIcon("res\\gramaPixelart(1).png");
        if (gramaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erro ao carregar a imagem da grama.");
        }
        gramaImage = gramaIcon.getImage();

        int cellSize = 800 / dimensao;

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
        int cellSize = 800 / dimensao;
        int x, y;
        String key;
    
        do {
            x = random.nextInt(dimensao) * cellSize;
            y = random.nextInt(dimensao) * cellSize;
    
            // Ajusta para centralizar o objeto, considerando o novo tamanho
            x += cellSize / 2 - (cellSize / 2);
            y += cellSize / 2 - (cellSize / 2);
    
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
        int cellSize = 800 / dimensao;
    
        // Desenha a grama no fundo
        for (int i = 0; i < 800; i += cellSize) {
            for (int a = 0; a < 800; a += cellSize) {
                g.drawImage(gramaImage, a, i, cellSize, cellSize, this);
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
        int playerWidth = 50; // Largura do jogador
        int playerHeight = 50; // Altura do jogador
        int frutaSize = 10; // Tamanho da fruta

        boolean xOverlap = (player.getX() < fruta1.getX() + frutaSize) && (player.getX() + playerWidth > fruta1.getX());
        boolean yOverlap = (player.getY() < fruta1.getY() + frutaSize) && (player.getY() + playerHeight > fruta1.getY());

        if (xOverlap && yOverlap) {
            player.catouFruta(fruta1);
            fruta1.setVisivel(false);
            fruta1 = new Fruta(dimensao);
            System.out.println(player.frutasColetadas());
        }
    }
}
