package controle_view;
import dados.DadosInterface;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
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
    private DadosInterface dados; // Interface de dados
    private boolean podeMoverJogador1 = true; // Flag para controlar a movimentação
    private boolean podeMoverJogador2 = true; // Flag para controlar a movimentação
    private int dimensao; // Dimensão do jogo
    private int proporcaoTelaJogo; // Proporção da tela do jogo
    private int cellSize; // Tamanho da célula
    private Set<String> PosicoesUsadas = new HashSet<>(); // Posições usadas
    private Set<String> posicoesPedras = new HashSet<>(); // Posições das pedras
    private Set<String> posicoesArvores = new HashSet<>(); // Posições das árvores
    private Image gramaImage; // Imagem da grama
    
    /**
     * Construtor da classe Janela.
     * Inicializa o painel de jogo com a dimensão e o tamanho da célula.
     * Também cria e configura o jogador, a fruta, as pedras e as árvores.
     */
    public Janela(int proporcaoTelaJogo,int dimensao, int quatidadePedras, int maracujas, int maracujas_chao, int laranjeiras, int laranjas, int abacateiros, int abacates, int coqueiros, int cocos, int pesDeAcerola, int acerolas, int amoeiras, int amoras, int goiabeiras, int goiabas, int probabidade_bichadas, DadosInterface dados) {
        this.dimensao = dimensao; // Define a dimensão do jogo
        this.pedras = new Pedra[quatidadePedras]; // Inicializa o array de pedras
        this.proporcaoTelaJogo = proporcaoTelaJogo; // Define a proporção da tela do jogo
        this.arvores = new Arvore[laranjeiras + abacateiros + coqueiros + pesDeAcerola + amoeiras + goiabeiras]; // Inicializa o array de árvores
        this.frutasChao = new Fruta[maracujas_chao + laranjas + abacates + cocos + acerolas + amoras + goiabas]; // Inicializa o array de frutas
        this.cellSize = (proporcaoTelaJogo / dimensao); // Calcula o tamanho da célula
        this.dados = dados; // Define a interface de dados
        
        setFocusable(true); // Permite que o painel tenha foco para eventos de teclado
        addKeyListener(this); // Adiciona o KeyListener ao painel
        requestFocusInWindow(); // Solicita foco para o JPanel
        player = new Jogador(this.dimensao, this.cellSize, 1); // Cria um novo jogador
        player2 = new Jogador(this.dimensao, this.cellSize, 2); // Cria um novo jogador
        dados.setJogadores(player,player2);
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
        int listaKeysPlayer1[] = {37, 38, 40, 39, 32}; // Setas do teclado e enter do teclado numerico
        int listaKeysPlayer2[] = {65, 87, 83, 68, 32}; // WASD e espaço
        if ((!podeMoverJogador1 || !podeMoverJogador2) && !dados.isPrimeiraJogada()) {
            return; // Se não pode mover, sai imediatamente
        }
        
        if(player.getQtdMovimentos() > 0 || player2.getQtdMovimentos() > 0){
            dados.setBotaoEnabled(false);
        }else{
            dados.setBotaoEnabled(true);
        }
        // Movimentação do Jogador 1
        /*Falando um pouco das movimentações e novos comandos, modifiquei um pouco a movimentação anteiror para que quando ele for se mover
         * ele verificar se teve alguma pedra no meio do caminho, se tiver e ele tiver com menos de dois pontos ele volta para seu lugar
         * e seus pontos antigos são recuperados, se ele tiver ele sobe na pedra e gasta mais um para poder escalar.
         */
        if (podeMoverJogador1 && player.getQtdMovimentos() > 0 && Arrays.stream(listaKeysPlayer1).anyMatch(i -> i == key)) {
            if(verificarColisaoComPedra(player, "Player 1", listaKeysPlayer1, key) == 2){
                System.out.println("Player 1: " + player.getQtdMovimentos());
                return;
            }
                
                switch (key) {
                    case KeyEvent.VK_UP -> player.moveUp();
                    case KeyEvent.VK_DOWN -> player.moveDown();
                    case KeyEvent.VK_LEFT -> player.moveLeft();
                    case KeyEvent.VK_RIGHT -> player.moveRight();
                    case KeyEvent.VK_SPACE -> verificarColisaoComFruta(player, "Player 1");
                }
                podeMoverJogador1 = false; // Desabilita a movimentação até a liberação da tecla
            podeMoverJogador2 = true;
            repaint();
        }
    
        // Movimentação do Jogador 2
        if (podeMoverJogador2 && player2.getQtdMovimentos() > 0 && (Arrays.stream(listaKeysPlayer2).anyMatch(i -> i == key))) {
            if(verificarColisaoComPedra(player2, "Player 2", listaKeysPlayer2, key) == 2 ){
                return;
            }
                switch (key) {
                    case KeyEvent.VK_W -> player2.moveUp();
                    case KeyEvent.VK_S -> player2.moveDown();
                    case KeyEvent.VK_A -> player2.moveLeft();
                    case KeyEvent.VK_D -> player2.moveRight();
                    case KeyEvent.VK_SPACE -> verificarColisaoComFruta(player2, "Player 2");
                }
                podeMoverJogador2 = false; // Desabilita a movimentação até a liberação da tecla
                podeMoverJogador1 = true;
                repaint();
        }

        // Reativa o botão quando os jogadores não tiverem mais movimentos
        if (player.getQtdMovimentos() == 0 && player2.getQtdMovimentos() == 0) {
            dados.setBotaoEnabled(true);
        }
        
        repaint();
    }

    /*
     * Método chamado quando uma tecla é liberada.
     * Ele avalia se o jogador ainda tem movimentos disponíveis e reativa a movimentação.
     * @param e O evento de teclado gerado
     * @see KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        int listaKeysPlayer1[] = {37, 38, 40, 39, 32}; // Setas do teclado e espaç
        int listaKeysPlayer2[] = {65, 87, 83, 68, 32}; // WASD e espaço
    
        // Reativa o movimento quando a tecla do Jogador 1 é liberada
        if (Arrays.stream(listaKeysPlayer1).anyMatch(i -> i == key) && player.getQtdMovimentos() > 0) {
            podeMoverJogador1 = true;
            podeMoverJogador2 = false;
        }
    
        // Reativa o movimento quando a tecla do Jogador 2 é liberada
        if (Arrays.stream(listaKeysPlayer2).anyMatch(i -> i == key) && player2.getQtdMovimentos() > 0) {
            podeMoverJogador2 = true;
            podeMoverJogador1 = false;
        }
        
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
     * Método chamado para verificar se o jogador colidiu com a fruta.
     * @param jogador O jogador a ser verificado
     * @param nomeJogador O nome do jogador
     */
    private void verificarColisaoComFruta(Jogador jogador, String nomeJogador) {
        for (int i = 0; i < frutasChao.length; i++) {
            Fruta coco = frutasChao[i];
            if (coco != null && coco.ehVisivel()) {
                //System.out.println("x: " +coco.getX() + " y: " + coco.getY() );
                int jogadorWidth = jogador.getWidth();
                int jogadorHeight = jogador.getHeigth();
                int frutaSize = (int) (cellSize * 0.8); // Tamanho da fruta proporcional à célula
    
                // Calcula o centro da fruta
                int frutaCenterX = coco.getX();
                int frutaCenterY = coco.getY();
    
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
                    jogador.decrementarMovimento(1);
                    System.out.println(nomeJogador + " agora esta com: " + jogador.getQtdMovimentos() + " movimentos");
                    repaint(); // Redesenha o painel para refletir a mudança
                }
            }
        }
    }

    private int verificarColisaoComPedra(Jogador jogador, String nomeJogador, int[] teclas, int teclaDigitada) {
        int movimentoOposto = -1; // Initialize with a default value
        int passouDoLimite = 0;
        // Verifica se o jogador colidiu com a pedra, faz a posição do jogador mudar para a tecla digitada e depois verifica se o jogador tem movimentos o suficiente se sim retorna 1 se não retorna 0, aí no final volta para a posição normal do jogador anteriormente
        for(int i = 0; i < teclas.length - 1; i++){
            if(teclas[i] == teclaDigitada){
                    passouDoLimite = jogador.move(i); //Não mexer na ordem dos elemetos do array de movimentos
                    movimentoOposto = i == 0 ? 3 : i == 1 ? 2 : i == 2 ? 1 : 0;
                }
            }

            if(movimentoOposto == -1){ //Se não houve movimento válido
                return 0;
            }
            if(passouDoLimite == 0){// Se o jogador passou do limite
                return 0;
            }

        for (int i = 0; i < pedras.length; i++) {
            Pedra pedraNaMinhaBota = pedras[i];
            if (pedraNaMinhaBota != null) {
                int jogadorWidth = jogador.getWidth();
                int jogadorHeight = jogador.getHeigth();
    
                // Calcula o centro da fruta
                int xDaPedra = pedraNaMinhaBota.getX();
                int yDaPedra = pedraNaMinhaBota.getY();
    
                // Verifica se há sobreposição nas coordenadas X e Y
                boolean xOverlap = (jogador.getX() + jogadorWidth > xDaPedra) &&
                                   (jogador.getX() < xDaPedra + (int)pedraNaMinhaBota.getDimensao());
                boolean yOverlap = (jogador.getY() + jogadorHeight > yDaPedra) &&
                                   (jogador.getY() < yDaPedra + (int)pedraNaMinhaBota.getDimensao());
    
                // Se houver sobreposição em ambos os eixos, o jogador ficou preso na pedra
                if (xOverlap && yOverlap) {
                    if(jogador.getQtdMovimentos() >= 1){

                        System.out.println(nomeJogador + ": perdeu um movimento");
                        System.out.println("e agora ta com: " + jogador.getQtdMovimentos() + " movimentos por ter escalado a pedra");
                        jogador.move(movimentoOposto);
                        jogador.setQtdMovimentos(jogador.getQtdMovimentos() + 1);
                        return  1;
                    }else{
                        jogador.move(movimentoOposto);
                        jogador.setQtdMovimentos(jogador.getQtdMovimentos() + 2);
                        return  2;
                    }
                }
        }
    }
    jogador.move(movimentoOposto);
    jogador.setQtdMovimentos(jogador.getQtdMovimentos() + 2);
    return  0;
    }
}