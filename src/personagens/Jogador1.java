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
	private int tamanhoMapa;
	
	public Jogador1(int dimensao,int tamanhoMapa) {
		this.dimensao = dimensao;
		this.tamanhoMapa = tamanhoMapa;
		this.x = aleatorio.nextInt(this.dimensao) * this.tamanhoMapa;
		this.y = aleatorio.nextInt(this.dimensao) * this.tamanhoMapa;
		//x+= (this.tamanhoMapa)/2 - 25;
		//y+= (this.tamanhoMapa)/2 -25;
        ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png");
        this.imagem = referencia.getImage();
		this.width = dimensao + 10;
		this.heigth = dimensao + 10;
	}
	public void load(Graphics g) {
	    int tamanho = (int) (this.tamanhoMapa * 0.8); // Ajusta o tamanho para 80% da célula
	    int offsetX = (tamanhoMapa - tamanho) / 2;   // Calcula o offset para centralizar
	    int offsetY = (tamanhoMapa - tamanho) / 2;

	    g.drawImage(imagem, x + offsetX + tamanhoMapa, y + offsetY + tamanhoMapa, tamanho, tamanho, null);
	}

	
	public void moveUp() {
	    y -= tamanhoMapa; // Substitua 800/this.dimensao por tamanhoMapa
	}
	public void moveDown() {
	    y += tamanhoMapa;
	}
	public void moveRight() {
	    x += tamanhoMapa;
	}
	public void moveLeft() {
	    x -= tamanhoMapa;
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
