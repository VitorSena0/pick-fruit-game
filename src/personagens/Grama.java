package personagens;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Grama extends ElementosEstaticos {

    protected Elementos[] objetos = new Elementos[2];
    protected int tamanhoCelula;
    public static ImageIcon referencia = new ImageIcon("res" + System.getProperty("file.separator") + "gramaPixelart(1).png");
    

    public Grama(int posicaoX, int posicaoY, int dimensao, int proporcao) {
        super(posicaoX, posicaoY, dimensao, referencia); // Passa a imagem para a classe-mãe
        this.tamanhoCelula = proporcao/dimensao;
    }

    @Override
    public void load(Graphics g) {
        int size = (int) (tamanhoCelula); // Ajusta o tamanho da pedra para 200% da célula
        g.drawImage(getImage(), posicaoX + tamanhoCelula , posicaoY + tamanhoCelula, size, size, null); // Usa o método getImage()
    }
	@Override
	public void acao() {
		System.out.println("pisando em grama vazia");
		
	}
	
	public void preencherComObjeto(Elementos PAF) {
		objetos[0] = PAF;
	}
	
	public void retirarObjeto(Elementos PAF) {
		objetos[0] = null;
	}
	
	public void jogadorEntrouNaCelula(Elementos jogador) {
		objetos[1] = jogador;
	}
	
	public void jogadorSaiu(Elementos jogador) {
		objetos[1] = null;
	}

	public static ImageIcon getReferencia() {
		return referencia;
	}

	public static void setReferencia(ImageIcon referencia) {
		Grama.referencia = referencia;
	}
	
}
