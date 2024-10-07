package modelo_jogo;
public abstract class ElementoEstatico extends Elemento {
	public abstract void interagir(Jogador jogador, Terreno terreno);
	ElementoEstatico(int x, int y) {
		super(x, y);
	}
}
