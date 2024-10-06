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

/**
 * A classe Janela representa o painel de jogo do jogo "Cata Fruta".
 * Ela herda de JPanel e implementa a interface KeyListener para lidar com eventos de teclado.
 * 
 * O painel de jogo contém elementos como o jogador, frutas, pedras e árvores.
 * Ele é responsável por renderizar esses elementos na tela e controlar a interação entre eles.
 * 
 * A classe Janela também é responsável por verificar colisões entre o jogador e as frutas,
 * atualizar a pontuação do jogador e gerar novas frutas em posições válidas.
 * 
 * @see JPanel
 * @see KeyListener
 * @see Jogador
 * @see Fruta
 * @see Pedra
 * @see Arvore
 * @see Container
 * @see KeyEvent
 * @see Graphics
 * @see Image
 */
public class Janela extends JPanel implements KeyListener {
    private Jogador player; // Jogador 1
    private Jogador player2; // Jogador 2
    private Coco coco; // Fruta
    private Pedra[] pedras = new Pedra[30]; // Pedras
    private Arvore[] arvores = new Arvore[30]; // Árvores
    private Set<String> PosicoesUsadas = new HashSet<>(); // Posições usadas
    private boolean podeMover = true; // Flag para controlar a movimentação
    private int dimensao = 15; // Dimensão do jogo
    private int proporcaoTelaJogo = 600; // Proporção da tela do jogo
    private Image gramaImage; // Imagem da grama
    private int cellSize; // Tamanho da célula
    private Set<String> posicoesPedras = new HashSet<>(); // Posições das pedras
    private Set<String> posicoesArvores = new HashSet<>(); // Posições das árvores

    /**
     * Construtor da classe Janela.
     * Inicializa o painel de jogo com a dimensão e o tamanho da célula.
     * Também cria e configura o jogador, a fruta, as pedras e as árvores.
     */
    public Janela() {
        this.cellSize = (proporcaoTelaJogo / dimensao); // Calcula o tamanho da célula
        setFocusable(true); // Permite que o painel tenha foco para eventos de teclado
        addKeyListener(this); // Adiciona o KeyListener ao painel
        player = new Jogador(this.dimensao, this.cellSize, 1); // Cria um novo jogador
        player2 = new Jogador(this.dimensao, this.cellSize, 2); // Cria um novo jogador
        coco = new Coco(this.dimensao, this.cellSize, PosicoesUsadas); // Gera uma nova fruta em uma posição válida

        // Carrega a imagem da grama
        ImageIcon gramaIcon = new ImageIcon("res" + System.getProperty("file.separator") + "gramaPixelart(1).png");
        if (gramaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erro ao carregar a imagem da grama.");
        }
        gramaImage = gramaIcon.getImage();

        // Inicializa as pedras e árvores com posições aleatórias e ajusta o tamanho delas
        for (int i = 0; i < pedras.length; i++) {
            int[] posicao = generateRandomPosition();
            pedras[i] = new Pedra(posicao[0], posicao[1], cellSize);
            posicoesPedras.add(posicao[0] + "," + posicao[1]); // Adiciona a posição da pedra
        }
        for (int i = 0; i < arvores.length; i++) {
            int[] posicao = generateRandomPosition();
            arvores[i] = new Arvore(posicao[0], posicao[1], "", cellSize);
            posicoesArvores.add(posicao[0] + "," + posicao[1]); // Adiciona a posição da árvore
        }
    }
    /*
     * Gera uma posição aleatória para os elementos do jogo.
     * @return Um array de inteiros com as coordenadas x e y da posição gerada.
     */
    public int[] generateRandomPosition() {
        Random random = new Random(); // Gera um número aleatório
        int x, y;
        String key;

        // Gera coordenadas em múltiplos do tamanho da célula
        do {
            x = random.nextInt(dimensao) * cellSize; // Gera um número aleatório entre 0 e a dimensão do jogo
            y = random.nextInt(dimensao) * cellSize; // Gera um número aleatório entre 0 e a dimensão do jogo
            key = "(" + x + "," + y + ")"; // Cria a chave da posição
        } while (PosicoesUsadas.contains(key)); // Verifica se a posição já foi usada

        PosicoesUsadas.add(key); // Adiciona a posição à lista de posições usadas

        return new int[]{x, y}; // Retorna as coordenadas geradas
    }

    /*
     * Método chamado quando uma tecla é pressionada.
     * @param e O evento de teclado gerado
     * @see KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (!podeMover) {
            return; // Se não pode mover, sai imediatamente
        }

        // Movimentação do Jogador 1
        switch (key) {
            case KeyEvent.VK_UP -> player.moveUp();
            case KeyEvent.VK_DOWN -> player.moveDown();
            case KeyEvent.VK_LEFT -> player.moveLeft();
            case KeyEvent.VK_RIGHT -> player.moveRight();
        }

        // Movimentação do Jogador 2
        switch (key) {
            case KeyEvent.VK_W -> player2.moveUp();
            case KeyEvent.VK_S -> player2.moveDown();
            case KeyEvent.VK_A -> player2.moveLeft();
            case KeyEvent.VK_D -> player2.moveRight();
        }

        System.out.println(PosicoesUsadas);
        System.out.println(PosicoesUsadas.size());

        for (Fruta coco : player.getFrutas()) {
            if (coco != null) {
                System.out.println(coco.getNomeFruta());
            } else {
                System.out.println("Fruta é nula.");
                break;
            }
        }


        podeMover = false; // Define como falso após qualquer movimento
        colision();
        repaint();
    }

    /*
     * Método chamado quando uma tecla é liberada.
     * @param e O evento de teclado gerado
     * @see KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        podeMover = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /*
     * Método chamado para desenhar os elementos do jogo na tela.
     * @param g O objeto Graphics usado para desenhar
     * @see Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método paintComponent da superclasse
        int cellSize = this.proporcaoTelaJogo / dimensao; // Calcula o tamanho da célula
    
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
        player2.load(g);
    
        // Desenha as árvores (por último, para que fiquem sobre o jogador)
        for (Arvore arvore : arvores) {
            arvore.load(g);
        }
    
        // Desenha a fruta (não precisa alterar)
        coco.load(g);
    }
    
    /*
     * Método chamado para verificar colisões entre o jogador e as frutas.
     */
    public void colision() {
        verificarColisaoComFruta(player, "Player 1");
        verificarColisaoComFruta(player2, "Player 2");
    }
    /*
     * Método chamado para verificar se o jogador colidiu com a fruta.
     * @param jogador O jogador a ser verificado
     * @param nomeJogador O nome do jogador
     */
    private void verificarColisaoComFruta(Jogador jogador, String nomeJogador) {
        int jogadorWidth = jogador.getWidth();
        int jogadorHeight = jogador.getHeigth();
        int frutaSize = (int) (cellSize * 0.8); // Tamanho da fruta proporcional à célula

        // Calcula o centro da fruta
        int frutaCenterX = coco.getX() + (cellSize - frutaSize) / 2;
        int frutaCenterY = coco.getY() + (cellSize - frutaSize) / 2;

        // Verifica se há sobreposição nas coordenadas X e Y
        boolean xOverlap = (jogador.getX() + cellSize < frutaCenterX + frutaSize + cellSize) && 
                           (jogador.getX() + jogadorWidth + cellSize > frutaCenterX + cellSize);
        boolean yOverlap = (jogador.getY() + cellSize < frutaCenterY + frutaSize + cellSize) && 
                           (jogador.getY() + cellSize + jogadorHeight > frutaCenterY + cellSize);

        // Se houver sobreposição em ambos os eixos, o jogador coletou a fruta
        if (xOverlap && yOverlap) {
            jogador.catouFruta(coco);
            coco.setVisivel(false, this.PosicoesUsadas);
            coco = new Coco(this.dimensao, this.cellSize, PosicoesUsadas); // Gera uma nova fruta em uma posição válida
            System.out.println(nomeJogador + ": " + jogador.frutasColetadas());
        }
    }

}