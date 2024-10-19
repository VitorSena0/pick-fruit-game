package personagens;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class ElementosDinamicos extends Elementos {
	
	private int posicaoX;
	private int posicaoY;
	private final Image image;

	ElementosDinamicos(int posicaoX, int posicaoY, int dimensao, ImageIcon referencia){
		super(dimensao);
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.image =referencia.getImage();
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	
	
}
