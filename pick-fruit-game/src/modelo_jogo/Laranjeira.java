package modelo_jogo;
public class Laranjeira extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		if (getCronometro() != 0) {
			return null;
		}
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(coordenadaX, coordenadaY, bichada);
		else
			fruto = new Laranja(coordenadaX, coordenadaY, bichada);
		iniciarCronometro();
		return fruto;
	}
	Laranjeira(int x, int y) {
		super(x, y);
	}
}