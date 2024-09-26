import java.awt.Graphics;


public class Jogador1{
	private int x,y;
	private int width, heigth;
	private Fruta frutas[] = new Fruta[10];
	protected int i = 0;
	
	public Jogador1() {
		this.x = (int) (Math.random()*800);
		this.y = (int) (Math.random()*800);
		this.width = 50;
		this.heigth = 50;
	}
	public void load(Graphics g) {
		g.fillRect(x, y, 50, 50);
	}
	
	public void moveUp() {
		y -= 50;
	}
	public void moveDown() {
		y += 50;
	}
	public void moveRight() {
		x += 50;
	}
	public void moveLeft() {
		x -= 50;
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
