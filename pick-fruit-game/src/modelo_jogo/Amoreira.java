package modelo_jogo;
public class Amoreira extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(coordenadaX, coordenadaY, bichada);
		else
			fruto = new Amora(coordenadaX, coordenadaY, bichada);
		return fruto;
	}
	Amoreira(int x, int y) {
		super(x, y);
	}
}