package personagens;

import java.util.Set;
import java.awt.Graphics;

public class Coco extends Fruta{

    public Coco(int dimensao, int dimensaoGrid, Set<String> posicoesOcupadas) {
        super(dimensao, dimensaoGrid, posicoesOcupadas);
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
