import java.awt.Graphics;
import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Jogador1{
	private int x,y;
	private int width, heigth;
	private Fruta frutas[] = new Fruta[10];
	protected int i = 0;
	private Random aleatorio = new Random();
	private Image imagem;
	private int dimensao;
	
	public Jogador1(int dimensao) {
		this.dimensao = dimensao;
		this.x = aleatorio.nextInt(6) * 800/dimensao;
		this.y = aleatorio.nextInt(6) * 800/dimensao;
		x+= (800/dimensao)/2 - 25;
		y+= (800/dimensao)/2 -25;
        ImageIcon referencia = new ImageIcon("res\\player1Pixelart.png");
        this.imagem = referencia.getImage();
		this.width = 50;
		this.heigth = 50;
	}
    public void load(Graphics g) {
        g.drawImage(imagem, x, y, 50, 50, null);
    }
	
	public void moveUp() {
		y -= 800/this.dimensao;
	}
	public void moveDown() {
		y += 800/this.dimensao;
	}
	public void moveRight() {
		x += 800/this.dimensao;
	}
	public void moveLeft() {
		x -= 800/this.dimensao;
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
