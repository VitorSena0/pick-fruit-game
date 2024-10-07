package modelo_jogo;
public abstract class Elemento {
	protected int coordenadaX;
	protected int coordenadaY;
	public int getX() {
		return coordenadaX;
	}
	public int getY() {
		return coordenadaY;
	}
	public abstract String[] desenho();
	Elemento(int x, int y) {
		if (x >= 0)
			coordenadaX = x;
		else
			coordenadaX = 0;
		if (y >= 0)
			coordenadaY = y;
		else
			coordenadaY = 0;
	}
}