package controle_view;
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

import modelo_jogo.Coco;
import personagens.Arvore;
import personagens.Fruta;
import personagens.Jogador;
import personagens.Pedra;

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
    private Fruta[] frutasChao; // Fruta
    private Pedra[] pedras; // Pedras
    private Arvore[] arvores; // Árvores
    private Set<String> PosicoesUsadas = new HashSet<>(); // Posições usadas
    private boolean podeMover = true; // Flag para controlar a movimentação
    private int dimensao; // Dimensão do jogo
    private int proporcaoTelaJogo; // Proporção da tela do jogo
    private Image gramaImage; // Imagem da grama
    private int cellSize; // Tamanho da célula
    private Set<String> posicoesPedras = new HashSet<>(); // Posições das pedras
    private Set<String> posicoesArvores = new HashSet<>(); // Posições das árvores
    
    
    /**
     * Construtor da classe Janela.
     * Inicializa o painel de jogo com a dimensão e o tamanho da célula.
     * Também cria e configura o jogador, a fruta, as pedras e as árvores.
     */
    public Janela(int proporcaoTelaJogo,int dimensao, int pedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas) {
        this.dimensao = dimensao; // Define a dimensão do jogo
        this.pedras = new Pedra[pedras]; // Inicializa o array de pedras
        this.proporcaoTelaJogo = proporcaoTelaJogo; // Define a proporção da tela do jogo
        this.arvores = new Arvore[laranjeiras + abacateiros + coqueiros + pesDeAcerola + amoeiras + goiabeiras]; // Inicializa o array de árvores
        this.frutasChao = new Fruta[maracujas_chao + laranjas + abacates + cocos + acerolas + amoras + goiabas]; // Inicializa o array de frutas
        this.cellSize = (proporcaoTelaJogo / dimensao); // Calcula o tamanho da célula
        setFocusable(true); // Permite que o painel tenha foco para eventos de teclado
        addKeyListener(this); // Adiciona o KeyListener ao painel
        requestFocusInWindow(); // Solicita foco para o JPanel
        player = new Jogador(this.dimensao, this.cellSize, 1); // Cria um novo jogador
        player2 = new Jogador(this.dimensao, this.cellSize, 2); // Cria um novo jogador
        //coco = new Coco(this.dimensao, this.cellSize, PosicoesUsadas); // Gera uma nova fruta em uma posição válida
        for (int i = 0; i < frutasChao.length; i++) {
            frutasChao[i] = new Coco(this.dimensao, this.cellSize, PosicoesUsadas, probabidade_bichadas); // Gera uma nova fruta em uma posição válida
        }
        // Carrega a imagem da grama
        ImageIcon gramaIcon = new ImageIcon("res" + System.getProperty("file.separator") + "gramaPixelart(1).png");
        if (gramaIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Erro ao carregar a imagem da grama.");
        }
        gramaImage = gramaIcon.getImage();

        // Inicializa as pedras e árvores com posições aleatórias e ajusta o tamanho delas
        for (int i = 0; i < this.pedras.length; i++) {
            int[] posicao = generateRandomPosition();
            this.pedras[i] = new Pedra(posicao[0], posicao[1], cellSize);
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
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
            switch (key) {
                case KeyEvent.VK_UP -> player.moveUp();
                case KeyEvent.VK_DOWN -> player.moveDown();
                case KeyEvent.VK_LEFT -> player.moveLeft();
                case KeyEvent.VK_RIGHT -> player.moveRight();
            }
         // Depuração para verificar as frutas coletadas do jogador 1
        for (Fruta coco : player.getFrutas()) {
            if (coco != null) {
                System.out.println(coco.getNomeFruta());
            } else {
                System.out.println("Fruta é nula.");
                break;
            }
        }   
        repaint();
        }
            // Movimentação do Jogador 2
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_A || key == KeyEvent.VK_D){
        switch (key) {
            case KeyEvent.VK_W -> player2.moveUp();
            case KeyEvent.VK_S -> player2.moveDown();
            case KeyEvent.VK_A -> player2.moveLeft();
            case KeyEvent.VK_D -> player2.moveRight();
        }
        // Depuração para verificar as frutas coletadas do jogador 2
        for (Fruta coco : player2.getFrutas()) {
            if (coco != null) {
                System.out.println(coco.getNomeFruta());
            } else {
                System.out.println("Fruta é nula.");
                break;
            }
        }
        repaint();
    }

       // System.out.println(PosicoesUsadas);
       // System.out.println(PosicoesUsadas.size());


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
        // Garante que o painel está com o foco
        requestFocusInWindow();
        // Desenha a grama no fundo
        for (int i = 0; i < this.proporcaoTelaJogo; i += cellSize) { 
            for (int a = 0; a < this.proporcaoTelaJogo; a += cellSize) {
                g.drawImage(gramaImage, a + cellSize, i + cellSize, cellSize, cellSize, this);
            }
        }
    
        // Desenha as pedras
        for (Pedra pedra : pedras) {
            pedra.load(g);
        }
    
        // Desenha o jogador
        player.load(g);
        player2.load(g);
    
        // Desenha as árvores
        for (Arvore arvore : arvores) {
            arvore.load(g);
        }
    
        // Desenha as frutas somente se estiverem visíveis
        for (Fruta fruta : frutasChao) {
            if (fruta != null && fruta.ehVisivel()) {
                fruta.load(g);
            }
        }
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
        for (int i = 0; i < frutasChao.length; i++) {
            Fruta coco = frutasChao[i];
            if (coco != null && coco.ehVisivel()) {
                int jogadorWidth = jogador.getWidth();
                int jogadorHeight = jogador.getHeigth();
                int frutaSize = (int) (cellSize * 0.8); // Tamanho da fruta proporcional à célula
    
                // Calcula o centro da fruta
                int frutaCenterX = coco.getX() + (cellSize - frutaSize) / 2;
                int frutaCenterY = coco.getY() + (cellSize - frutaSize) / 2;
    
                // Verifica se há sobreposição nas coordenadas X e Y
                boolean xOverlap = (jogador.getX() + jogadorWidth > frutaCenterX) &&
                                   (jogador.getX() < frutaCenterX + frutaSize);
                boolean yOverlap = (jogador.getY() + jogadorHeight > frutaCenterY) &&
                                   (jogador.getY() < frutaCenterY + frutaSize);
    
                // Se houver sobreposição em ambos os eixos, o jogador coletou a fruta
                if (xOverlap && yOverlap) {
                    jogador.catouFruta(coco, PosicoesUsadas); // Adiciona a fruta ao jogador
                    coco.setVisivel(false, PosicoesUsadas); // Torna a fruta invisível e remove a posição
                    frutasChao[i] = null; // Remove a fruta do array
                    System.out.println(nomeJogador + ": " + jogador.frutasColetadas());
                    repaint(); // Redesenha o painel para refletir a mudança
                }
            }
        }
        // int jogadorWidth = jogador.getWidth();
        // int jogadorHeight = jogador.getHeigth();
        // int frutaSize = (int) (cellSize * 0.8); // Tamanho da fruta proporcional à célula

        // // Calcula o centro da fruta
        // int frutaCenterX = coco.getX() + (cellSize - frutaSize) / 2;
        // int frutaCenterY = coco.getY() + (cellSize - frutaSize) / 2;

        // // Verifica se há sobreposição nas coordenadas X e Y
        // boolean xOverlap = (jogador.getX() + cellSize < frutaCenterX + frutaSize + cellSize) && 
        //                    (jogador.getX() + jogadorWidth + cellSize > frutaCenterX + cellSize);
        // boolean yOverlap = (jogador.getY() + cellSize < frutaCenterY + frutaSize + cellSize) && 
        //                    (jogador.getY() + cellSize + jogadorHeight > frutaCenterY + cellSize);

        // // Se houver sobreposição em ambos os eixos, o jogador coletou a fruta
        // if (xOverlap && yOverlap) {
        //     jogador.catouFruta(coco);
        //     coco.setVisivel(false, this.PosicoesUsadas);
        //     coco = new Coco(this.dimensao, this.cellSize, PosicoesUsadas); // Gera uma nova fruta em uma posição válida
        //     System.out.println(nomeJogador + ": " + jogador.frutasColetadas());
        // }
    }

}