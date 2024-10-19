package personagens;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class ElementosEstaticos extends Elementos{
	
	protected final int posicaoX;
	protected final int posicaoY;
	
	private final Image image;
	
	public ElementosEstaticos(int posicaoX, int posicaoY, int dimensao, ImageIcon referencia) {
		super(dimensao);
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.image = referencia.getImage();
	}

	public Image getImage() {
		return image;
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

}
