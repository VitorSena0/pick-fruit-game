package modelo_jogo;
public abstract class ElementoDinamico extends Elemento{
	public abstract void mover(int direcao);
	ElementoDinamico(int x, int y) {
		super(x, y);
	}
}
