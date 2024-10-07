package modelo_jogo;
public class Goiabeira extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(coordenadaX, coordenadaY, bichada);
		else
			fruto = new Goiaba(coordenadaX, coordenadaY, bichada);
		return fruto;
	}
	Goiabeira(int x, int y) {
		super(x, y);
	}
}