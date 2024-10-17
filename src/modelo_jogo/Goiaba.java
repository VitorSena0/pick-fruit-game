package modelo_jogo;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Set;

import personagens.*;

public class Goiaba extends Fruta{
	
    private Image imagem;

   public Goiaba(int dimensao, int dimensaoGrid, Set<String> posicoesOcupadas, int bichada) {
    super(dimensao, dimensaoGrid, posicoesOcupadas, bichada); // Passa bichada para a superclasse
    ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "goiaba.png");
    this.imagem = referencia.getImage();
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
}