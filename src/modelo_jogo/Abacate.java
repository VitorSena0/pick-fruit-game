package modelo_jogo;
import java.util.Set;
import java.awt.Graphics;

import personagens.*;

public class Abacate extends Fruta{
	private int bichada;
	
	public Abacate(int dimensao, int dimensaoGrid, Set<String> posicoesOcupadas, int bichada) {
		super(dimensao, dimensaoGrid, posicoesOcupadas, bichada); // Passa bichada para a superclasse
    	this.bichada = bichada; // Inicializa a variável de instância
}

@Override
public void load(Graphics g) {
	if (visivel) {
		int tamanho = (int) (this.dimensaoGrid * 0.8); // Ajusta o tamanho da fruta para 50% da célula
		int offsetX = (dimensaoGrid - tamanho) / 2;   // Calcula o offset para centralizar
		int offsetY = (dimensaoGrid - tamanho) / 2;
		
		g.drawImage(imagem, x + offsetX + dimensaoGrid, y + offsetY + dimensaoGrid, tamanho, tamanho, null);
	}
}
	public boolean serConsumida(Jogador jogador) {
		return true;
	}
	public String[] desenho() {
		return null;
	}
}