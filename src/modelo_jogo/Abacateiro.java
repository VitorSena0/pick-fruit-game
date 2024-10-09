package modelo_jogo;
import personagens.Arvore;
import personagens.Fruta;
public class Abacateiro extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(bichada);
		else
			fruto = new Abacate(coordenadaX, coordenadaY, bichada);
		return fruto;
	}
	Abacateiro(int x, int y) {
		super(x, y);
	}
}