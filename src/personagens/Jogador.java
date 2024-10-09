package personagens;
import java.awt.Graphics;
import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * A classe Jogador representa o personagem controlado pelo jogador no jogo "Cata Fruta".
 * Ela é responsável por manter a posição do jogador, a quantidade de frutas coletadas
*/
public class Jogador{
	private String tipoJogador;
	private int x,y;
	private int width, heigth;
	private Fruta frutas[] = new Fruta[99];
	protected int qtdFrutasColetadas = 0;
	private Random aleatorio = new Random();
	private Image imagem;
	private int dimensao;
	private int dimensaoGrid;
	
	public Jogador(int dimensao,int dimensaoGrid, int tipoJogador) {
		this.tipoJogador = (tipoJogador == 1)? "player1" : "player2";
		this.dimensao = dimensao;
		this.dimensaoGrid = dimensaoGrid;
		this.x = aleatorio.nextInt(this.dimensao) * this.dimensaoGrid;
		this.y = aleatorio.nextInt(this.dimensao) * this.dimensaoGrid;
        ImageIcon referencia = (tipoJogador == 1)? new ImageIcon("res" + System.getProperty("file.separator") + "player1Pixelart.png") : new ImageIcon("res" + System.getProperty("file.separator") + "player2Pixelart.png");
        this.imagem = referencia.getImage();
		this.width = dimensao + 1;
		this.heigth = dimensao + 1;
	}
	public void load(Graphics g) {
	    int tamanho = (int) (this.dimensaoGrid * 0.8); // Ajusta o tamanho para 80% da célula
	    int tamanhoX = tamanho + (int)(tamanho*0.3); 
	    int offsetX = (dimensaoGrid - tamanho) / 2;   // Calcula o offset para centralizar
	    int offsetY = (dimensaoGrid - tamanho) / 2;

	    g.drawImage(imagem, x + offsetX + dimensaoGrid, y + offsetY + dimensaoGrid, tamanhoX, tamanho, null);
	}

	
    public void moveUp() {
        if (y - dimensaoGrid >= 0) { // Verifica se não ultrapassa o limite superior
            y -= dimensaoGrid;
        }
    }

    public void moveDown() {
        if (y + dimensaoGrid < dimensao * dimensaoGrid) { // Verifica se não ultrapassa o limite inferior
            y += dimensaoGrid;
        }
    }

    public void moveLeft() {
        if (x - dimensaoGrid >= 0) { // Verifica se não ultrapassa o limite esquerdo
            x -= dimensaoGrid;
        }
    }

    public void moveRight() {
        if (x + dimensaoGrid < dimensao * dimensaoGrid) { // Verifica se não ultrapassa o limite direito
            x += dimensaoGrid;
        }
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
	public Fruta[] getFrutas(){
		return this.frutas;
	}
	public String getTipoJogador(){
		return this.tipoJogador;
	}
	
	public void catouFruta(Fruta a, Set<String> posicoesOcupadas) {
		frutas[qtdFrutasColetadas] = a;
		a.setVisivel(false, posicoesOcupadas); // Marcar a fruta como invisível
		qtdFrutasColetadas++;
	}
	
	
	public int frutasColetadas() {
		return qtdFrutasColetadas;
	}

}
