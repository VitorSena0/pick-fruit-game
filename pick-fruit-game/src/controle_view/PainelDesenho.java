package controle_view;
import java.awt.*;
import javax.swing.JPanel;

/**
 * A classe PainelDesenho é uma extensão de {@link JPanel} e é utilizada para exibir uma imagem em uma área específica da interface.
 * Ela permite definir atributos como coordenadas, dimensões e o caminho da imagem que será exibida no painel.
 * 
 * @version 1.0
 */
public class PainelDesenho extends JPanel {

    /** Imagem que será desenhada no painel. */
    private Image imagem;

    /** Coordenada X do painel. */
    private int coordenadaX;

    /** Coordenada Y do painel. */
    private int coordenadaY;

    /** Largura do painel. */
    private int largura;

    /** Altura do painel. */
    private int altura;

    /**
     * Construtor padrão que inicializa o painel com valores padrão e sem imagem.
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
     * Construtor que inicializa o painel com coordenadas, dimensões e caminho da imagem especificados.
     * 
     * @param x Coordenada X do painel.
     * @param y Coordenada Y do painel.
     * @param largura Largura do painel.
     * @param altura Altura do painel.
     * @param caminho Caminho da imagem a ser exibida.
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
     * Define os atributos do painel, incluindo coordenadas, dimensões e caminho da imagem.
     * 
     * @param x Coordenada X do painel.
     * @param y Coordenada Y do painel.
     * @param largura Largura do painel.
     * @param altura Altura do painel.
     * @param caminho Caminho da imagem a ser exibida.
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
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (imagem != null) {
            g.drawImage(imagem, 0, 0, largura, altura, this);
        }
    }
}
