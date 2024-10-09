package controle_view;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.*;
public class PainelDesenho extends JPanel{
	private Image imagem;
	private int coordenadaX;
	private int coordenadaY;
	private int largura;
	private int altura;
	
	public PainelDesenho() {
		imagem = null;
		coordenadaX = 0;
		coordenadaY = 0;
		this.largura = 100;
		this.altura = 100;
		setBounds(coordenadaX, coordenadaY, this.largura, this.altura);
		setVisible(true);
	}
	
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
