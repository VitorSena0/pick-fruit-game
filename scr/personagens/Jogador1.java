import java.awt.Graphics;
import java.util.Iterator;


public class Jogador1{
	private int x,y;
	private int width, heigth;
	private Fruta frutas[] = new Fruta[10];
	protected int i = 0;
	
	public Jogador1() {
		this.x = 10;
		this.y = 10;
		this.width = 50;
		this.heigth = 50;
	}
	public void load(Graphics g) {
		g.fillRect(x, y, 50, 50);
	}
	
	public void moveUp() {
		y -= 10;
	}
	public void moveDown() {
		y += 10;
	}
	public void moveRight() {
		x += 10;
	}
	public void moveLeft() {
		x -= 10;
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
