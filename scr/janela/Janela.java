import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Janela extends JPanel implements KeyListener {
	private Jogador1 player;
	private Fruta fruta1;
	private boolean podeMover = true;
	
	public Janela() {
		setFocusable(true);
		addKeyListener(this);
		player = new Jogador1();
		fruta1 = new Fruta();
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
		player.load(g);
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
	