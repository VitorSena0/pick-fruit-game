package controle_view;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.*;

/**
 * A classe PainelDesenho é um painel personalizado que permite desenhar uma imagem
 * na tela com coordenadas e dimensões especificadas. Fornece métodos para alterar as propriedades 
 * da imagem, como posição, tamanho e visibilidade.
 */
public class PainelDesenho extends JPanel{
	private Image imagem;
	private int coordenadaX;
	private int coordenadaY;
	private int largura;
	private int altura;
	
	/**
 * Construtor padrão da classe PainelDesenho.
 * Inicializa o painel com valores padrão para coordenadas e dimensões.
 */
	public PainelDesenho() {
		imagem = null;
		coordenadaX = 0;
		coordenadaY = 0;
		this.largura = 100;
		this.altura = 100;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		setVisible(true);
	}
	

	     /**
     * Construtor que permite definir as coordenadas, dimensões e o caminho da imagem.
     * 
     * @param x A coordenada X do painel.
     * @param y A coordenada Y do painel.
     * @param largura A largura do painel.
     * @param altura A altura do painel.
     * @param caminho O caminho para a imagem a ser carregada.
     */
	public PainelDesenho(int x, int y, int largura, int altura, String caminho) {
		coordenadaX = (x >= 0) ? x : coordenadaX;
		coordenadaY = (y >= 0) ? y : coordenadaY;
		this.largura = (largura >= 0) ? largura : this.largura;
		this.altura = (altura >= 0) ? altura : this.altura;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		setImagem(caminho);
		setVisible(true);
	}
	
	public void setX(int x) {
		coordenadaX = (x >= 0) ? x : coordenadaX;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		repaint();
	}
	public void setY(int y) {
		coordenadaY = (y >= 0) ? y : coordenadaY;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		repaint();
	}
	public void setLargura(int largura) {
		this.largura = (largura >= 0) ? largura : this.largura;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		repaint();
	}
	public void setAltura(int altura) {
		this.altura = (altura >= 0) ? altura : this.altura;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		repaint();
	}
	public void setImagem(String caminho) {
		try {
            imagem = Toolkit.getDefaultToolkit().getImage(caminho);
        } catch (Exception e) {
            imagem = null;
        }
		repaint();
	}

	    /**
     * Define os atributos do painel, como coordenadas, dimensões e imagem.
     * 
     * @param x A coordenada X do painel.
     * @param y A coordenada Y do painel.
     * @param largura A largura do painel.
     * @param altura A altura do painel.
     * @param caminho O caminho da imagem a ser carregada.
     */
	public void setAtributos(int x, int y, int largura, int altura, String caminho) {
		coordenadaX = (x >= 0) ? x : coordenadaX;
		coordenadaY = (y >= 0) ? y : coordenadaY;
		this.largura = (largura >= 0) ? largura : this.largura;
		this.altura = (altura >= 0) ? altura : this.altura;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		setImagem(caminho);
	}
	public void mudarVisibilidade(boolean visivel) {
		setVisible(visivel);
	}

	    /**
     * Método que sobrescreve o paintComponent para desenhar a imagem no painel.
     * 
     * @param g O contexto gráfico onde a imagem será desenhada.
     */
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (imagem != null) {
            g.drawImage(imagem, 0, 0, largura, altura, this);
        }
    }
}
