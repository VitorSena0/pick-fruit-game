package modelo_jogo;
public class Abacateiro extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		if (getCronometro() != 0) {
			return null;
		}
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(coordenadaX, coordenadaY, bichada);
		else
			fruto = new Abacate(coordenadaX, coordenadaY, bichada);
		iniciarCronometro();
		return fruto;
	}
	Abacateiro(int x, int y) {
		super(x, y);
	}
}