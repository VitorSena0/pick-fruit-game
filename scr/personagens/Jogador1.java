import java.awt.Graphics;
import java.util.*;


public class Jogador1{
	private int x,y;
	private int width, heigth;
	private Fruta frutas[] = new Fruta[10];
	protected int i = 0;
	private Random aleatorio = new Random();
	
	public Jogador1() {
		this.x = aleatorio.nextInt(6) * 800/6;
		this.y = aleatorio.nextInt(6) * 800/6;
		x+= (800/6)/2 -25;
		y+= (800/6)/2 -25;
		this.width = 50;
		this.heigth = 50;
	}
	public void load(Graphics g) {
		g.fillRect(x, y, 50, 50);
	}
	
	public void moveUp() {
		y -= 800/6;
	}
	public void moveDown() {
		y += 800/6;
	}
	public void moveRight() {
		x += 800/6;
	}
	public void moveLeft() {
		x -= 800/6;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeigth() {
		return heigth;
	}
	
	public void catouFruta(Fruta a) {
		frutas[i] = a;
		i++;
	}
	
	public int frutasColetadas() {
		return i;
	}

}
