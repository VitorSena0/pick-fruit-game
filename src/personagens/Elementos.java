package personagens;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Elementos {
	
	protected int dimensao;
	
	public Elementos(int dimensao) {
		this.dimensao = dimensao;
		
	}
	
	public abstract void load(Graphics g);
	public abstract void acao();
	

}
