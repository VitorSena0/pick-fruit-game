package modelo_jogo;
public class Pe_de_Acerola extends Arvore {
	public Fruta gerarFruta(boolean bichada, boolean ouro) {
		if (getCronometro() != 0) {
			return null;
		}
		Fruta fruto = null;
		if(ouro)
			fruto = new Maracuja(coordenadaX, coordenadaY, bichada);
		else
			fruto = new Acerola(coordenadaX, coordenadaY, bichada);
		iniciarCronometro();
		return fruto;
	}
	Pe_de_Acerola(int x, int y) {
		super(x, y);
	}
}