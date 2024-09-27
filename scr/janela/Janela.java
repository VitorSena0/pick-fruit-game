import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

public class Janela extends JPanel implements KeyListener {
	private Jogador1 player;
	private Fruta fruta1;
	private Pedra[] pedras = new Pedra[10];
	private Arvore[] arvores = new Arvore[15];
	private Set<String> PosicoesUsadas = new HashSet<>();
	private boolean podeMover = true;
	private int dimensao = 6;
	
	public Janela() {
		setFocusable(true);
		addKeyListener(this);
		player = new Jogador1();
		fruta1 = new Fruta();

		for (int i = 0; i < pedras.length; i++) {
           int[] posicao = generateRandomPosition();
            pedras[i] = new Pedra(posicao[0], posicao[1]);
        }
        for (int i = 0; i < arvores.length; i++) {
           int[] posicao = generateRandomPosition();
            arvores[i] = new Arvore(posicao[0], posicao[1], "");
       }
	}

	public int[] generateRandomPosition() {
        Random random = new Random();
		int celula = 800 / dimensao; // Tamanho da célula
		int x, y;
		String key;
	
		do {
			// Gera uma posição que seja um múltiplo do tamanho da célula
			x = random.nextInt(dimensao) * celula;
			y = random.nextInt(dimensao) * celula;
	
			// Ajusta para o centro da célula
			x += celula / 2 - 15;
			y += celula / 2 - 15;
	
            key = x + "," + y;  // Cria uma chave única para a posição 
        } while (PosicoesUsadas.contains(key)); // Verifica se a posição já foi usada

        // Adiciona a nova posição ao conjunto
        PosicoesUsadas.add(key);

        // Retorna a posição
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
		g.setColor(Color.BLUE);
		for(int i = 0; i < 800 ; i+= 800/dimensao ){
			for(int a = 0; a <800;a += 800/dimensao){
				g.fillRect(a, i, 800/dimensao, 800/dimensao);
				if (g.getColor() == Color.BLUE) {
					g.setColor(Color.RED);
				}
				else{
					g.setColor(Color.BLUE);
				}
			}
			
		}
		g.setColor(Color.BLACK);
		player.load(g);
		// Desenha as pedras
		g.setColor(Color.BLACK);
		
		// Desenha as pedras
		for (Pedra pedra : pedras) {
			pedra.load(g);
		}
	
		// Desenha as árvores
		for (Arvore arvore : arvores) {
			arvore.load(g);
		}
		g.setColor(Color.PINK);
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
	        fruta1 = new Fruta();
	        System.out.println(player.frutasColetadas());
	    }
	}
	}
	